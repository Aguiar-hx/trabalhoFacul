// frontend/src/app/app.routes.ts
import { Routes } from '@angular/router';
import { AlunoListComponent } from './components/aluno/aluno-list/aluno-list.component';
import { AlunoFormComponent } from './components/aluno/aluno-form/aluno-form.component'; // IMPORTANTE: Adicione esta linha

export const routes: Routes = [
    { path: 'alunos', component: AlunoListComponent },
    { path: 'alunos/novo', component: AlunoFormComponent }, // Rota para criar novo aluno
    { path: 'alunos/editar/:id', component: AlunoFormComponent }, // Rota para editar aluno existente (o :id é um parâmetro)

    // Rota padrão: redireciona para /alunos se o caminho estiver vazio
    { path: '', redirectTo: '/alunos', pathMatch: 'full' }
];