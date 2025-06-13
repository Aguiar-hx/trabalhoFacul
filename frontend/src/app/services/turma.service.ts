// Importa os módulos necessários do Angular e RxJS
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Turma } from '../models/turma.model'; // Importa o modelo da turma

// Define que o serviço estará disponível na raiz da aplicação (singleton)
@Injectable({
  providedIn: 'root'
})
export class TurmaService {
  // Define a URL base da API para turmas
  private apiUrl = '/api/turmas';

  // Injeta o HttpClient para fazer requisições HTTP
  constructor(private http: HttpClient) { }

  // Retorna uma lista de turmas (GET /api/turmas)
  getTurmas(): Observable<Turma[]> {
    return this.http.get<Turma[]>(this.apiUrl);
  }

  // Retorna os dados de uma turma específica pelo ID (GET /api/turmas/:id)
  getTurmaById(id: string): Observable<Turma> {
    return this.http.get<Turma>(`${this.apiUrl}/${id}`);
  }

  // Cria uma nova turma (POST /api/turmas)
  createTurma(turma: Turma): Observable<Turma> {
    return this.http.post<Turma>(this.apiUrl, turma);
  }

  // Atualiza uma turma existente com base no ID (PUT /api/turmas/:id)
  updateTurma(id: string, turma: Turma): Observable<Turma> {
    return this.http.put<Turma>(`${this.apiUrl}/${id}`, turma);
  }

  // Deleta uma turma com base no ID (DELETE /api/turmas/:id)
  deleteTurma(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
