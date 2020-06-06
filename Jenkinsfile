pipeline {
  agent any
  
  stages {
    stage('Initialize') {
      agent { docker { image 'maven:3.6.3-jdk-8' } }
      steps {
        sh '''echo PATH = ${PATH}
              echo M2_HOME = ${M2_HOME}
              mvn -f BusFindPorto/pom.xml clean'''
      }
    }

    stage('Validate') {
      agent { docker { image 'maven:3.6.3-jdk-8' } }
      steps {
        sh 'mvn -f BusFindPorto/pom.xml validate'
      }
    }


    stage('Compile') {
      agent { docker { image 'maven:3.6.3-jdk-8' } }
      steps {
        sh 'mvn -f BusFindPorto/pom.xml compile'
      }
    }

    stage('Test') {
      agent { docker { image 'maven:3.6.3-jdk-8' } }
      steps {
        sh '''mvn -f BusFindPorto/pom.xml test
'''
      }
    }

    stage('Package') {
      agent { docker { image 'maven:3.6.3-jdk-8' } }
      steps {
        sh 'mvn -f BusFindPorto/pom.xml package'
      }
    }

    stage('Integration-test') {
      agent { docker { image 'maven:3.6.3-jdk-8' } }
      steps {
        sh 'mvn -f BusFindPorto/pom.xml integration-test'
      }
    }

    stage('Verify') {
      agent { docker { image 'maven:3.6.3-jdk-8' } }
      steps {
        sh 'mvn -f BusFindPorto/pom.xml verify'
      }
    }

    stage('Install') {
      agent { docker { image 'maven:3.6.3-jdk-8' } }
      steps {
        sh 'mvn -f BusFindPorto/pom.xml install'
      }
    }

    stage('Artifactory Deployment') {
      agent { docker { image 'maven:3.6.3-jdk-8' } }
      steps {
        sh 'mvn deploy -f BusFindPorto/pom.xml -s BusFindPorto/settings.xml'
      }
    }

  stage('Build Docker image'){
      steps{
          sshagent(credentials: ['esp13-sshagent']){
            sh "ssh -o 'StrictHostKeyChecking=no' -l esp13 192.168.160.103 uname -a"
            sh "export DOCKER_HOST='ssh://esp13@192.168.160.103'"
            sh "docker build -t esp13-service-layer ."
            sh "docker tag esp13-service-layer 192.168.160.99:5000/"
            sh "docker push 192.168.160.99:5000/esp13-service-layer"
          }
      }
    }
    stage('Runtime Deployment') { 
      steps {
          sshagent(credentials: ['esp13-sshagent']){
              sh "ssh -o 'StrictHostKeyChecking=no' -l esp13 192.168.160.103 docker rm -f esp13-service-layer"
              sh "ssh -o 'StrictHostKeyChecking=no' -l esp13 192.168.160.103 docker run -d -p 11000:11080 --name esp13-service-layer 192.168.160.99:5000/esp13-service-layer"
          }
      }
    }
  }
}
