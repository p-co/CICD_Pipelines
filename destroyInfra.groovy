pipeline {
   agent any
   

   stages {
     
      stage('Terraform destroy infrastructure') {
        steps {
            dir('../DeployInfra'){
                sh "terraform destroy -auto-approve"
            }
        }
      }
      
   }
}
