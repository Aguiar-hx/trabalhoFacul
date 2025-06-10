// Importa a função usada para iniciar (bootstrap) uma aplicação standalone Angular
import { bootstrapApplication } from '@angular/platform-browser';

// Importa a configuração da aplicação (providers como rotas, HttpClient etc.)
import { appConfig } from './app/app.config';

// Importa o componente raiz da aplicação (standalone)
import { AppComponent } from './app/AppComponent';

// Inicia a aplicação com o componente principal e a configuração definida
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err)); // Caso ocorra um erro no carregamento, ele será exibido no console
