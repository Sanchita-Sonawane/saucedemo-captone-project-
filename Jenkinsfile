pipeline {
    agent any

    environment {
        APP_ENV = 'dev'
        GIT_CREDENTIALS_ID = '2281c81a-a285-496a-a34b-f17650b5be58'
        GIT_REPO = 'https://github.com/Sanchita-Sonawane/saucedemo-captone-project-.git'
        BRANCH = 'main'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning from GitHub...'
                git branch: "${BRANCH}", credentialsId: "${GIT_CREDENTIALS_ID}", url: "${GIT_REPO}"
            }
        }

        stage('Build') {
            steps {
                echo 'Running mvn clean install...'
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Executing mvn test...'
                bat 'mvn test'
            }
        }
        stage('Push Changes to GitHub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "${GIT_CREDENTIALS_ID}", usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
                        bat """
                            git config user.name "Sanchita Sonawane"
                            git config user.email "sanchitasonawane27@gmail.com.com"
                            
                            git add .
                            git status
                            git commit -m "Automated change from Jenkins at ${now}" || echo "No changes to commit"
                            git push https://%GIT_USER%:%GIT_PASS%@github.com/Sanchita-Sonawane/saucedemo-captone-project-.git HEAD:${BRANCH}
                        """
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying to ${env.APP_ENV} environment..."
               
            }
        }
    }

    post {
        success {
            echo 'Pipeline finished successfully!'
        }
        failure {
            echo 'Pipeline failed :('
        }
    }
}
