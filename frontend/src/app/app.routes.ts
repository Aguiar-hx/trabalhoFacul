// frontend/src/app/app.routes.ts
import { Routes } from '@angular/router';
import { AlunoListComponent } from './components/aluno/aluno-list/aluno-list.component';
import { AlunoFormComponent } from './components/aluno/aluno-form/aluno-form.component';
import { CurriculoListComponent } from './components/curriculo/curriculo-list/curriculo-list.component';
import { CurriculoFormComponent } from './components/curriculo/curriculo-form/curriculo-form.component';
import { CursoListComponent } from './components/curso/curso-list/curso-list.component';
import { CursoFormComponent } from './components/curso/curso-form/curso-form.component';
import { DisciplinaListComponent } from './components/disciplina/disciplina-list/disciplina-list.component';
import { DisciplinaFormComponent } from './components/disciplina/disciplina-form/disciplina-form.component';
import { TurmaListComponent } from './components/turma/turma-list/turma-list.component'; // NOVO
import { TurmaFormComponent } from './components/turma/turma-form/turma-form.component'; // NOVO

export const routes: Routes = [
    // ... Rotas de Aluno, Currículo, Curso, Disciplina ...
    { path: 'alunos', component: AlunoListComponent },
    { path: 'alunos/novo', component: AlunoFormComponent },
    { path: 'alunos/editar/:id', component: AlunoFormComponent },

    { path: 'curriculos', component: CurriculoListComponent },
    { path: 'curriculos/novo', component: CurriculoFormComponent },
    { path: 'curriculos/editar/:id', component: CurriculoFormComponent },

    { path: 'cursos', component: CursoListComponent },
    { path: 'cursos/novo', component: CursoFormComponent },
    { path: 'cursos/editar/:id', component: CursoFormComponent },

    { path: 'disciplinas', component: DisciplinaListComponent },
    { path: 'disciplinas/novo', component: DisciplinaFormComponent },
    { path: 'disciplinas/editar/:id', component: DisciplinaFormComponent },

    // Rotas de Turma (NOVAS)
    { path: 'turmas', component: TurmaListComponent },
    { path: 'turmas/novo', component: TurmaFormComponent },
    { path: 'turmas/editar/:id', component: TurmaFormComponent },

    // Rota padrão
    { path: '', redirectTo: '/alunos', pathMatch: 'full' }
];