pipeline {
   agent any
   

   stages {
     
      stage('Terraform destroy RDS') {
        steps {
            dir('../DeployRDS'){
                sh "terraform destroy -auto-approve"
            }
        }
      }
      
   }
}
