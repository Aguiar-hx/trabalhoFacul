// frontend/src/app/services/aluno.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Aluno } from '../models/aluno.model'; // Importa nosso model Aluno

@Injectable({
  providedIn: 'root'
})
export class AlunoService {
  // A URL base da API para Alunos.
  // O Nginx no Docker do frontend vai redirecionar chamadas para /api/...
  // para o seu backend Spring Boot (que está no serviço 'app', porta 8080).
  private apiUrl = '/api/alunos';

  constructor(private http: HttpClient) { }

  // Método para buscar todos os alunos
  getAlunos(): Observable<Aluno[]> {
    return this.http.get<Aluno[]>(this.apiUrl);
  }

  // Método para buscar um aluno pelo ID
  getAlunoById(id: string): Observable<Aluno> {
    return this.http.get<Aluno>(`<span class="math-inline">\{this\.apiUrl\}/</span>{id}`);
  }

  // Método para criar um novo aluno
  createAluno(aluno: Aluno): Observable<Aluno> {
    return this.http.post<Aluno>(this.apiUrl, aluno);
  }

  // Método para atualizar um aluno existente
  updateAluno(id: string, aluno: Aluno): Observable<Aluno> {
    return this.http.put<Aluno>(`<span class="math-inline">\{this\.apiUrl\}/</span>{id}`, aluno);
  }

  // Método para deletar um aluno
  deleteAluno(id: string): Observable<void> {
    return this.http.delete<void>(`<span class="math-inline">\{this\.apiUrl\}/</span>{id}`);
  }
}