# ================================
# 🏗️ Etapa de build do Angular
# ================================
FROM node:20 AS build
WORKDIR /app

# Copia os arquivos de dependência
COPY package*.json ./

# Instala Angular CLI e dependências do projeto
RUN npm install -g @angular/cli@15.2.0
RUN npm install --force

# Copia o restante do código-fonte
COPY . .

# Gera o build de produção
RUN ng build --configuration production

# ================================
# 🚀 Etapa de produção (Nginx)
# ================================
FROM nginx:1.25-alpine
WORKDIR /usr/share/nginx/html

# Remove o site padrão
RUN rm -rf ./*

# Copia o build gerado do Angular
COPY --from=build /app/dist/unifor-frontend ./

# Copia configuração customizada do Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
