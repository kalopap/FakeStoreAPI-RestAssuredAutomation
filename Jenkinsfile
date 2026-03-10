pipeline{
    agent any

    stages{
        stage('Checkout'){
            steps {
                git branch: 'main',
                    url: 'https://github.com/kalopap/FakeStoreAPI-RestAssuredAutomation.git'
            }
        }
        stage('Build Docker Image'){
            steps{
                sh 'docker rmi fakestore-api-tests || true'
                sh 'docker build -t fakestore-api-tests .'
            }
        }
        stage('Run tests in Docker'){
            steps{
                sh 'docker run --rm -v $(pwd)/target:/app/target fakestore-api-tests mvn test'
            }
        }
        stage('Publish Allure Report'){
            steps{
                allure([
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}