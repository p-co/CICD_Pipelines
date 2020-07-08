pipeline {
   agent any
   
   environment {
       branch = "master"
   }

   stages {
      stage('clean workspace'){
          steps {
              cleanWs()
          }
      }
      stage('Git clone') {
         steps {
                sh 'pwd'
                git(
                url: 'https://github.com/p-co/CICD_Deploy_RDS.git',
                credentialsId: '73abe7bf-c9db-442a-8e34-a440591578d8',
                branch: "${branch}"
                )
                sh 'ls -l'
            
         }
      }
      stage('Terraform Init') {
        steps {
            sh "terraform init -input=false"
            
        }
      }
      stage('Deploying RDS'){
        steps{
            sh "terraform apply -input=false -auto-approve"
          
        }
      }
      
   }
}
