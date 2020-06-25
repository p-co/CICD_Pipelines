pipeline {
   agent any
   

   stages {
     
      stage('AMI cleaner destroy AMI') {
        steps {
          sh "amicleaner --mapping-key name --mapping-values Packer-Ansible --keep-previous -1 --ami-min-days -1"
        }
      }
      
   }
}
