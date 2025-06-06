// frontend/src/app/services/aluno.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Aluno } from '../models/aluno.model';

@Injectable({
  providedIn: 'root'
})
export class AlunoService {
  private apiUrl = '/api/alunos';

  constructor(private http: HttpClient) { }

  getAlunos(): Observable<Aluno[]> {
    return this.http.get<Aluno[]>(this.apiUrl);
  }

  // CORRIJA AQUI
  getAlunoById(id: string): Observable<Aluno> {
    // Use o acento grave (crase) aqui
    return this.http.get<Aluno>(`${this.apiUrl}/${id}`);
  }

  createAluno(aluno: Aluno): Observable<Aluno> {
    return this.http.post<Aluno>(this.apiUrl, aluno);
  }

  // CORRIJA AQUI
  updateAluno(id: string, aluno: Aluno): Observable<Aluno> {
    // Use o acento grave (crase) aqui
    return this.http.put<Aluno>(`${this.apiUrl}/${id}`, aluno);
  }

  // CORRIJA AQUI
  deleteAluno(id: string): Observable<void> {
    // Use o acento grave (crase) aqui
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}