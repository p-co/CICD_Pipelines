pipeline {
   agent any
   

   stages {
     
      stage('Terraform destroy website') {
        steps {
            dir('../DeployWebsite'){
                sh "terraform destroy -auto-approve"
            }
        }
      }
      
   }
}
