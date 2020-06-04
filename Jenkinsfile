pipeline {
  agent {
    docker {
      image 'maven:3.6.3-jdk-8'
    }

  }
    
  stages {
    stage('Initialize') {
      steps {
        sh '''echo PATH = ${PATH}
echo M2_HOME = ${M2_HOME}
mvn -f BusFindPorto/pom.xml clean'''
      }
    }

    stage('Validate') {
      steps {
        sh 'mvn -f BusFindPorto/pom.xml validate'
      }
    }


    stage('Compile') {
      steps {
        sh 'mvn -f BusFindPorto/pom.xml compile'
      }
    }

    stage('Test') {
      steps {
        sh '''mvn -f BusFindPorto/pom.xml test
'''
      }
    }

    stage('Package') {
      steps {
        sh 'mvn -f BusFindPorto/pom.xml package'
      }
    }

    stage('Integration-test') {
      steps {
        sh 'mvn -f BusFindPorto/pom.xml integration-test'
      }
    }

    stage('Verify') {
      steps {
        sh 'mvn -f BusFindPorto/pom.xml verify'
      }
    }

    stage('Install') {
      steps {
        sh 'mvn -f BusFindPorto/pom.xml install'
      }
    }

    stage('Artifactory Deployment') {
            steps {
                sh 'mvn deploy -f BusFindPorto/pom.xml -s BusFindPorto/settings.xml'
            }
        }

  stage('Build Docker image'){
      steps{
          sshagent(credentials: ['esp13_ssh_credentials']){
            sh "ssh -o 'StrictHostKeyChecking=no' -l esp13 192.168.160.103 uname -a"
            sh 'export DOCKER_HOST="ssh://esp13@192.168.160.103'
            sh "docker build -t esp13-service-layer ."
            sh "docker tag esp13-service-layer 192.168.160.99:5000/"
            sh "docker push 192.168.160.99:5000/esp13-service-layer"
          }
      }
    }
    stage('Runtime Deployment') { 
            steps {
                sshagent(credentials: ['esp13_ssh_credentials']){
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp13 192.168.160.103 docker rm -f esp13-service-layer"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp13 192.168.160.103 docker run -d -p 11000:11080 --name esp13-service-layer 192.168.160.99:5000/esp13-service-layer"
                }
            }
    }
  }
}
