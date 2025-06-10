// Importa o tipo ApplicationConfig para definir configurações da aplicação
import { ApplicationConfig } from '@angular/core';

// Importa o método para fornecer as rotas da aplicação
import { provideRouter } from '@angular/router';

// Importa as rotas definidas em outro arquivo
import { routes } from './app.routes';

// Importa o método para fornecer suporte ao HttpClient (requisições HTTP) — IMPORTANTE para serviços HTTP funcionarem
import { provideHttpClient } from '@angular/common/http'; // IMPORTANTE

// Exporta a configuração da aplicação Angular
export const appConfig: ApplicationConfig = {
  providers: [
    // Fornece o sistema de rotas com as rotas definidas
    provideRouter(routes),

    // Ativa o suporte ao módulo HttpClient, necessário para chamadas HTTP em serviços — IMPORTANTE
    provideHttpClient() 
  ]
};
