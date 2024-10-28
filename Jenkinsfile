pipeline {
    agent any

    environment {
        GITHUB_REPO = 'https://github.com/Dobre237/devopsapp.git' // Replace with your GitHub repo
        MAVEN_SERVER = 'ubuntu@54.172.88.32'  // Replace with your Maven server's IP
        SONARQUBE_SERVER_URL = 'http://34.224.27.130:9000'  // Replace with your SonarQube server's IP
        SONARQUBE_PROJECT_KEY = 'JenkinsSonarqube'  // Replace with your SonarQube project key
        SONARQUBE_TOKEN = credentials('sonarqube-token')  // Assuming the token is stored in Jenkins credentials
    }

    stages {
        stage('Checkout Code from GitHub') {
            steps {
                // Clone the GitHub repository
                git branch: 'master', url: "${GITHUB_REPO}"
            }
        }

        stage('Build with Maven on Remote Server') {
            steps {
                // SSH into the Maven server and run the Maven build commands
                sshagent (credentials: ['maven-server-ssh-key']) {
                    sh """
                    ssh -o StrictHostKeyChecking=no ${MAVEN_SERVER} << EOF
                    cd /path/to/your/maven/project  # Set the correct path to your project on the Maven server
                    git pull origin main  # Pull the latest changes from GitHub
                    mvn clean package  # Run Maven build
                    EOF
                    """
                }
            }
        }

        stage('SonarQube Code Analysis') {
            steps {
                withSonarQubeEnv('SonarQube-Server') {  // Use the SonarQube server configured in Jenkins
                    sh """
                    ssh -o StrictHostKeyChecking=no ${MAVEN_SERVER} << EOF
                    cd /path/to/your/maven/project  # Go to your Maven project on the Maven server
                    mvn sonar:sonar \\
                        -Dsonar.projectKey=${SONARQUBE_PROJECT_KEY} \\
                        -Dsonar.host.url=${SONARQUBE_SERVER_URL} \\
                        -Dsonar.login=${SONARQUBE_TOKEN}
                    EOF
                    """
                }
            }
        }
    }

    post {
        always {
            script {
                // Optional: Wait for the quality gate result
                waitForQualityGate abortPipeline: true
            }
        }
    }
}