# --- Estágio 1: Build (Construção da Aplicação) ---
# Esta primeira parte do Dockerfile é responsável por compilar o seu código Java e empacotá-lo em um arquivo .jar.
# Usamos uma imagem que já vem com o Maven e o Java (JDK) instalados para fazer isso.
# 'as build' dá um nome a este estágio para que possamos nos referir a ele mais tarde.
FROM maven:3.9.6-eclipse-temurin-21 as build

# Define o diretório de trabalho dentro do contêiner como /app.
# Todos os comandos seguintes serão executados a partir deste diretório.
WORKDIR /app

# Copia todos os arquivos do seu projeto (da pasta 'backend' no seu computador)
# para o diretório de trabalho (/app) dentro do contêiner.
# Isso inclui o pom.xml e a pasta src/.
COPY . .

# Executa o comando do Maven para construir o projeto.
# 'mvn clean package' compila o código e empacota o resultado em um arquivo .jar.
# '-DskipTests' pula a execução dos testes unitários para acelerar o processo de build.
RUN mvn clean package -DskipTests


# --- Estágio 2: Run (Execução da Aplicação) ---
# Esta segunda parte cria a imagem final que será usada para rodar a sua aplicação.
# O objetivo de ter múltiplos estágios é criar uma imagem final muito menor e mais segura,
# contendo apenas o necessário para executar a aplicação, sem as ferramentas de compilação (como o Maven).

# Começa a partir de uma imagem base mais leve que contém apenas o Java (JDK).
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho como /app também na imagem final.
WORKDIR /app

# Este é o passo chave do multi-stage build:
# Copia APENAS o arquivo .jar que foi gerado no estágio 'build' (do diretório /app/target/)
# para o diretório de trabalho da nossa imagem final e o renomeia para 'app.jar' para facilitar.
COPY --from=build /app/target/*.jar app.jar

# Informa ao Docker que o contêiner irá expor a porta 8080 em tempo de execução.
# Esta é a porta padrão que o Spring Boot usa.
EXPOSE 8080

# Define o comando que será executado quando o contêiner iniciar.
# 'ENTRYPOINT' executa o comando "java -jar app.jar", que inicia a sua aplicação Spring Boot.
ENTRYPOINT ["java", "-jar", "app.jar"]