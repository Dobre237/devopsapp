pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Cloning the GitHub repo
                git branch: 'main', url: 'https://github.com/yourusername/yourrepo.git'
            }
        }

        stage('Build with Maven') {
            steps {
                // Building with Maven
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // SonarQube analysis using Maven
                withSonarQubeEnv('SonarQube-Server') {  // 'SonarQube-Server' is the name set in Jenkins
                    sh 'mvn sonar:sonar \
                        -Dsonar.projectKey=github-helloworld-project \
                        -Dsonar.host.url=http://<sonarqube-server-ip>:9000 \
                        -Dsonar.login=<sonarqube-token>'
                }
            }
        }
    }

    post {
        always {
            // Optional: wait for SonarQube quality gate
            script {
                waitForQualityGate abortPipeline: true
            }
        }
    }
}
