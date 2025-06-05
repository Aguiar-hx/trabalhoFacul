import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,         // CORRETO - Mantém como standalone
  imports: [RouterOutlet],    // CORRETO - Importa o RouterOutlet
  templateUrl: './app.html',  // CORRETO - Aponta para o seu app.html
  styleUrls: ['./app.css']    // CORRIGIDO - de styleUrl para styleUrls
})
export class AppComponent {   // CORRIGIDO - nome da classe para AppComponent
  title = 'frontend';       // Esta linha é opcional, pode manter ou remover
}