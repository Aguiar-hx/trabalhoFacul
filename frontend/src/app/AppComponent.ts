// frontend/src/app/AppComponent.ts
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // Importe RouterModule

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterModule // <-- GARANTA QUE ISTO ESTEJA AQUI
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent {
  title = 'frontend';
}