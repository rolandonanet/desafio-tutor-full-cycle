FROM openjdk:17-oracle

WORKDIR /app

# Copie o JAR da sua aplicação para o contêiner
COPY ./desafio-tutor.jar /app/desafio-tutor.jar

# Comando para executar a aplicação
CMD ["java", "-jar", "/app/desafio-tutor.jar"]
