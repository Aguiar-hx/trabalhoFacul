// frontend/src/app/app.routes.ts
import { Routes } from '@angular/router';
import { AlunoListComponent } from './components/aluno/aluno-list/aluno-list.component';
import { AlunoFormComponent } from './components/aluno/aluno-form/aluno-form.component';
import { CurriculoListComponent } from './components/curriculo/curriculo-list/curriculo-list.component';
import { CurriculoFormComponent } from './components/curriculo/curriculo-form/curriculo-form.component';

export const routes: Routes = [
    // Rotas de Aluno
    { path: 'alunos', component: AlunoListComponent },
    { path: 'alunos/novo', component: AlunoFormComponent },
    { path: 'alunos/editar/:id', component: AlunoFormComponent },

    // Rotas de Currículo (NOVAS)
    { path: 'curriculos', component: CurriculoListComponent },
    { path: 'curriculos/novo', component: CurriculoFormComponent },
    { path: 'curriculos/editar/:id', component: CurriculoFormComponent },

    // Rota padrão
    { path: '', redirectTo: '/alunos', pathMatch: 'full' } // Mantém redirecionando para alunos por enquanto
];