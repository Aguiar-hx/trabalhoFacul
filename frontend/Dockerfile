# Estágio 1: Construção da Aplicação Angular
FROM node:20-alpine AS builder

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos de definição de pacotes
COPY package.json package-lock.json ./

# Instala as dependências do projeto
RUN npm install

# Copia todo o código-fonte da aplicação
COPY . .

# Constrói a aplicação para produção
# O output será na pasta /app/dist/frontend/browser
RUN npm run build

# Estágio 2: Configuração do Servidor de Produção (Nginx)
FROM nginx:alpine AS production

# Remove a configuração padrão do Nginx
RUN rm /etc/nginx/conf.d/default.conf

# Copia a nossa configuração personalizada do Nginx para o contêiner
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copia os arquivos da aplicação construídos no estágio anterior
# O caminho de origem é /app/dist/frontend/browser, conforme a configuração do angular.json
COPY --from=builder /app/dist/frontend/browser /usr/share/nginx/html

# Expõe a porta 80, que é a porta padrão do Nginx
EXPOSE 80

# Comando para iniciar o servidor Nginx
CMD ["nginx", "-g", "daemon off;"]