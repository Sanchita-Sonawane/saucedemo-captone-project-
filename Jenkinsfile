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
