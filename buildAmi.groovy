pipeline {
   agent any
   
   environment {
       branch = "master"
       ip = sh(script: "curl http://169.254.169.254/latest/meta-data/public-ipv4", returnStdout: true)
   }

   stages {
      stage('clean workspace'){
          steps {
              cleanWs()
          }
      }
      stage('Git clone') {
         steps {
            dir 'BuildAMI'{
                sh 'pwd'
                git(
                url: 'https://github.com/p-co/CICD_TP_Build_AMI',
                credentialsId: 'afbaa30d-b712-4445-b89c-8dc67c3f8e64',
                branch: "${branch}"
                )
                sh 'ls -l'
            }
         }
      }
      stage('building AMI'){
        steps{
          dir 'BuildAMI'{
             sh "packer build \
		-var env=$ENV \
		-var app_repo=$APP_REPO \
		-var app_name=$APP_NAME \
		-var ip=$ip \
		-var ami=$ami \
		-var subnet=$subnet \
		buildAMI.json"
          }
        }
      }
   }
}
