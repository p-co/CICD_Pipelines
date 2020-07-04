pipeline {
   agent any

   stages {
      stage('clean workspace'){
          steps {
              cleanWs()
          }
      }
      stage('Deploy RDS') {
        steps {
            sh "aws rds create-db-instance \
--engine mysql \
--db-instance-identifier mysqlforsymfony \
--db-instance-class db.t2.micro \
--engine-version 5.7.26 \
--allocated-storage 20 \
--master-username admin \
--master-user-password password \
--backup-retention-period 0 \
--availability-zone eu-west-1a \
--no-auto-minor-version-upgrade \
--no-deletion-protection \
--no-publicly-accessible"
        }
      }
      
   }
}
