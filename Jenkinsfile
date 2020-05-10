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

  }
}