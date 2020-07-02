pipeline {
   agent any
   
   environment {
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
            dir('BuildAMI'){
                sh 'pwd'
                git(
                url: 'https://github.com/p-co/CICD_Build_AMI.git',
                credentialsId: '73abe7bf-c9db-442a-8e34-a440591578d8',
                branch: "master"
                )
                sh 'ls -l'
            }
         }
      }
      stage('building AMI'){
        steps{
          dir('BuildAMI'){
            script {
              if ("${type}" == 'regular') {
                sh "packer build \
            	-var app_repo=$APP_REPO \
            	-var app_name=$APP_NAME \
            	-var ip=$ip \
            	-var port=$port \
            	buildAMI.json"
              }
              else {
                sh "packer build \
            	-var app_repo=$APP_REPO \
            	-var app_name=$APP_NAME \
            	-var ip=$ip \
            	-var port=$port \
            	buildAMISymfony.json"
              }
            }
          }
        }
      }
   }
}
