pipeline {
    agent any
    environment {
        MAVEN_HOME = "/usr/bin/mvn:$PATH"
        JAVA_HOME = "/home/vitalii/jdk1.8.0_261/"
    }

    stages {
        stage('git') {
            steps {
                git branch: 'main', credentialsId: 'Jenkins-Git-Hub', url: 'https://github.com/EricCartman598/aws-shop.git'			     
            }            
        }
        stage('build') {
            steps {
                sh "mvn clean package"
            }            
        }
        stage('containering') {
            steps {
                sh "sudo cp /var/lib/jenkins/workspace/pipelineJob/target/awsShop.war /home/vitalii/Docker/awsShop.war"
                sh "sudo docker build -t ericcartman598/aws-shop:latest /home/vitalii/Docker"
                sh "sudo docker push ericcartman598/aws-shop:latest"
            }
        }
        stage('deploy to kubernetes') {
            steps {
                sh "sudo kubectl rollout restart deployment/aws-shop-deployment"
            }            
        }
    }
}
