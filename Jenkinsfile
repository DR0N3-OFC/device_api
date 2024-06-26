pipeline {
  agent any
  stages {
    stage("setup environment") {
      steps {
        sh '''
          docker version
          docker info
          curl --version
        '''
      }
    }
    stage('Prune Docker data') {
      steps {
        def containerName = 'spring'
          def imageName = 'spring:latest'

          // Check if the container exists
          sh """
          if [ \$(docker ps -a -q -f name=${containerName}) ]; then
              echo "Removing container '${containerName}'..."
              docker rm -f ${containerName}
          else
              echo "Container '${containerName}' does not exist."
          fi
          """
  
          // Check if the image exists
          sh """
          if [ \$(docker images -q ${imageName}) ]; then
              echo "Removing image '${imageName}'..."
              docker rmi -f ${imageName}
          else
              echo "Image '${imageName}' does not exist."
          fi
          """
      }
    }
    stage('Start PostgreSQL container if not exists') {
      steps {
        script {
          def postgresContainerName = 'postgres'
          def postgresImageName = 'postgres:latest'
          def postgresVolume = 'devices-volume'
          
          // Check if the PostgreSQL container exists
          sh """
          if [ ! \$(docker ps -a -q -f name=${postgresContainerName}) ]; then
              echo "Starting new PostgreSQL container '${postgresContainerName}'..."
              docker run -d \
              --name ${postgresContainerName} \
              -e POSTGRES_PASSWORD=postgres \
              -e POSTGRES_USER=postgres \
              -e POSTGRES_DB=device_api \
              -p 5432:5432 \
              -v -v ${postgresVolumeName}:/var/lib/postgresql/data \
              ${postgresImageName}
          else
              echo "PostgreSQL container '${postgresContainerName}' already exists."
          fi
          """
        }
      }
    }
    stage('Start container') {
      steps {
        sh '''
          cd deviceapi
          
          docker build -t spring:latest .
          
          docker run -d \
          -p 8080:8080 \
          --name spring \
          spring:latest
          
          docker ps
        '''
      }
    }
  }
  post {
        success {
            emailext body: "Build ${currentBuild.fullDisplayName} succeeded",
                     subject: "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - Successful",
                     to: 'dronecraftbr10@gmail.com',
                     attachLog: true
        }
        failure {
            emailext body: "Build ${currentBuild.fullDisplayName} failed",
                     subject: "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - Failed",
                     to: 'dronecraftbr10@gmail.com',
                     attachLog: true
        }
        unstable {
            emailext body: "Build ${currentBuild.fullDisplayName} is unstable",
                     subject: "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - Unstable",
                     to: 'dronecraftbr10@gmail.com',
                     attachLog: true
        }
        always {
            emailext body: "Build ${currentBuild.fullDisplayName} has finished with status ${currentBuild.currentResult}",
                     subject: "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - ${currentBuild.currentResult}",
                     to: 'dronecraftbr10@gmail.com',
                     attachLog: true
        }
    }
}
