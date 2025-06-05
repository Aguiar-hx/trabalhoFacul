// frontend/src/app/models/aluno.model.ts
export interface Aluno {
  id?: string; // O '?' significa que o id é opcional (útil ao criar um novo aluno que ainda não tem id)
  nome: string;
  ira: number;
  cursoId: string;
  periodoIngressoId: string;
}