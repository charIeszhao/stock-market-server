node {
    stage('git clone') {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'Github', url: 'https://github.com/demonzoo/stock-market-server.git']]])
    }
    stage('build common module') {
        sh "mvn clean install"
    }
    stage('build services') {
        sh "mvn clean package"
        sh "cd /var/jenkins_home/workspace/stockmarket-server"
        sh "cd stockmarket-eureka-server && docker build -t stockmarket/eureka-server:0.0.1 ."
        sh "cd ../stockmarket-gateway-service && docker build -t stockmarket/gateway-service:0.0.1 ."
        sh "cd ../stockmarket-user-service && docker build -t stockmarket/user-service:0.0.1 ."
    }
    stage('deploy') {
        sh "cd /var/jenkins_home/workspace/stockmarket-server/stockermarket-eureka-server"
        sh "docker run --name eureka-service -d -p 8761:8761 stockmarket/eureka-server:0.0.1"
        sh "cd ../stockmarket-gateway-service"
        sh "docker run --link eureka-service:8761 --name gateway-service -d -p 5555:5555 stockmarket/gateway-service:0.0.1"
        sh "cd ../stockmarket-user-service"
        sh "docker run --link eureka-service:8761 --name user-service -d -p 8081:8081 stockmarket/user-service:0.0.1"
    }
}