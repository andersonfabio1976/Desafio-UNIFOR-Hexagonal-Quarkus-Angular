export const environment = {
  production: false,
  keycloak: {
    url: 'http://localhost:8080',
    realm: 'unifor-realm',
    clientId: 'unifor-frontend',
  },
  // Backend exposto pelo docker-compose: host 8081 -> container 8080
  apiUrl: 'http://localhost:8081',
};
