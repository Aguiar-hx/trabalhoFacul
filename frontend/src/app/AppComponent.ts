// Importa o decorator Component do Angular
import { Component } from '@angular/core';

// Importa o RouterModule, necessário para usar <router-outlet> e diretivas como routerLink
import { RouterModule } from '@angular/router'; // Importe RouterModule

@Component({
  selector: 'app-root',               // Nome da tag usada no HTML para instanciar esse componente
  standalone: true,                   // Indica que o componente é standalone (não faz parte de um módulo)
  imports: [
    RouterModule                      // Importa o módulo de rotas para permitir navegação e uso de <router-outlet>
  ],
  templateUrl: './app.html',          // Caminho para o arquivo HTML com o template do componente
  styleUrls: ['./app.css']            // Caminho para o arquivo CSS com os estilos do componente
})
export class AppComponent {
  title = 'frontend';                 // Propriedade usada internamente ou no template, se necessário
}
