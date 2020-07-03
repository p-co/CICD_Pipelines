pipeline {
   agent any
   
    environment {
        AWS_DEFAULT_REGION='eu-west-1'
    }
   
   stages {
      stage('Destroy AMI') {
         steps {
            sh "amicleaner --mapping-key name --mapping-values Packer-Ansible --keep-previous -1 --ami-min-days -1 -f"
         }
      }
   }
}