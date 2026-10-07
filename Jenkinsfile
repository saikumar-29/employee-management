pipeline {
    agent any

    tools {
        maven 'mymaven'
    }

    environment {
        APP_IMAGE = "employee-app:${BUILD_NUMBER}"
        DB_IMAGE = "employee-db:${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Code Quality') {
            steps {
                // Configure SonarQube server in Jenkins first.
                // The installation name below must match Jenkins Global Tool Configuration.
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn verify sonar:sonar -Dsonar.projectKey=employee-management'
                }
            }
        }

        stage('Package Artifact') {
            steps {
                sh 'mvn clean package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Build Docker Images') {
            steps {
                sh "docker build -t ${APP_IMAGE} ."
                sh "docker build -t ${DB_IMAGE} ./db"
            }
        }

        stage('Security Scan') {
            steps {
                sh "trivy image --exit-code 0 --severity HIGH,CRITICAL ${APP_IMAGE}"
                sh "trivy image --exit-code 0 --severity HIGH,CRITICAL ${DB_IMAGE}"
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker compose down || true'
                sh 'docker compose up -d --build'
                sh 'docker compose ps'
            }
        }
    }

    post {
        success {
            echo 'CI/CD pipeline completed successfully.'
        }
        failure {
            echo 'CI/CD pipeline failed. Check the Jenkins console output.'
        }
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
    }
}
