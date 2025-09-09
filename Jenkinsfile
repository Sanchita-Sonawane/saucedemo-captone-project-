pipeline {
   agent any
   
   triggers {
      //githubPush()   
      pollSCM('H/5 * * * *')
   }
   
   environment {
      APP_ENV = 'dev'
        GIT_CREDENTIALS_ID = '2281c81a-a285-496a-a34b-f17650b5be58'
        GIT_REPO = 'https://github.com/Sanchita-Sonawane/saucedemo-captone-project-.git'
        BRANCH = 'main'
   }
   
   stages {
      stage('Clone') {
         steps {
            git branch: "${BRANCH}", credentialsId: "${GIT_CREDENTIALS_ID}", url: "${GIT_REPO}"
         }
      }
      
      stage('Build') {
         steps {
            echo 'Building the project...'
            bat 'mvn clean install'
         }
      }
      
      stage('Test') {
         steps {
            echo 'Running tests...'
            bat 'mvn test'
         }
      }
      
      stage('Push changes to GitHub') {
         steps {
            echo 'push and commit changes to GitHub...'
            
            withCredentials([usernamePassword(credentialsId: '2281c81a-a285-496a-a34b-f17650b5be58',usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS')]) {
            
            bat """
            git config --global user.email "sanchitasonawane27@gmail.com"'
            git config --global user.name "Sanchita sonawane"
            git add . 
            git commit -m "Automated commit from Jenkins pipeline" || echo "No changes to commit"
            git push https://${GIT_USER}:{GIT_PASS}@github.com/Sanchita-Sonawane/saucedemo-captone-project-.git HEAD:main
            """
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
         echo 'Pipeline completed successfully.'
      }
      failure {
         echo 'Pipeline failed.'
      }
   }
}

pipeline {

    agent any
 
    environment {

        GIT_CREDENTIALS_ID = 'PAT_Jenkins' // Replace with your credentials ID

        BRANCH_NAME = 'main' // or 'master' or any other branch

        ECLIPSE_WORKSPACE = 'C:\Users\HP\eclipse-workspace\SauceDemoAutomation' // Change to your path

        COMMIT_MESSAGE = 'Automated commit from Jenkins'

    }
 
    stages {

        stage('Checkout from Git') {

            steps {

                checkout([$class: 'GitSCM',

                    branches: [[name: "*/${env.BRANCH_NAME}"]],

                    userRemoteConfigs: [[

                        url: 'https://github.com/Sanchita-Sonawane/saucedemo-captone-project-.git', // Replace with your repo

                        credentialsId: "${env.GIT_CREDENTIALS_ID}"

                    ]]

                ])

            }

        }
 
        stage('Copy Files from Eclipse Workspace') {

            steps {

                bat """

                echo Copying files from Eclipse workspace...

                xcopy /E /Y /I "${ECLIPSE_WORKSPACE}\\*" "."

                """

            }

        }
 
        stage('Configure Git') {

            steps {

                bat """

                git config user.email "sanchitasonawane27@gmail.com.com"

                git config user.name "Sanchita-Sonawane"

                """

            }

        }
 
        stage('Check Git Status') {

            steps {

                bat 'git status'

            }

        }
 
        stage('Commit & Push Changes') {

            steps {

                bat """

                git add .
 
                REM Check if there are changes before committing

                git diff --cached --quiet

                IF %ERRORLEVEL% NEQ 0 (

                    echo Changes detected, committing...

                    git commit -m "${COMMIT_MESSAGE}"

                    git push origin ${BRANCH_NAME}

                ) ELSE (

                    echo No changes to commit.

                )

                """

            }

        }

    }
 
    post {

        success {

            echo 'Push to Git completed (if there were changes).'

        }

        failure {

            echo 'Build failed. Check console output.'

        }

    }

}
 
