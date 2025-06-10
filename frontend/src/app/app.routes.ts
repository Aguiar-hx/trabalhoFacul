// Importa o tipo Routes do Angular Router
import { Routes } from '@angular/router';

// Importações dos componentes da funcionalidade Aluno
import { AlunoListComponent } from './components/aluno/aluno-list/aluno-list.component';
import { AlunoFormComponent } from './components/aluno/aluno-form/aluno-form.component';

// Importações dos componentes de Currículo
import { CurriculoListComponent } from './components/curriculo/curriculo-list/curriculo-list.component';
import { CurriculoFormComponent } from './components/curriculo/curriculo-form/curriculo-form.component';

// Importações dos componentes de Curso
import { CursoListComponent } from './components/curso/curso-list/curso-list.component';
import { CursoFormComponent } from './components/curso/curso-form/curso-form.component';

// Importações dos componentes de Disciplina
import { DisciplinaListComponent } from './components/disciplina/disciplina-list/disciplina-list.component';
import { DisciplinaFormComponent } from './components/disciplina/disciplina-form/disciplina-form.component';

// Importações dos componentes de Turma (NOVOS)
import { TurmaListComponent } from './components/turma/turma-list/turma-list.component'; // NOVO
import { TurmaFormComponent } from './components/turma/turma-form/turma-form.component'; // NOVO

// Declaração do array de rotas da aplicação
export const routes: Routes = [
  // Rotas de Alunos
  { path: 'alunos', component: AlunoListComponent },            // Lista de alunos
  { path: 'alunos/novo', component: AlunoFormComponent },       // Formulário para novo aluno
  { path: 'alunos/editar/:id', component: AlunoFormComponent }, // Edição de aluno

  // Rotas de Currículos
  { path: 'curriculos', component: CurriculoListComponent },
  { path: 'curriculos/novo', component: CurriculoFormComponent },
  { path: 'curriculos/editar/:id', component: CurriculoFormComponent },

  // Rotas de Cursos
  { path: 'cursos', component: CursoListComponent },
  { path: 'cursos/novo', component: CursoFormComponent },
  { path: 'cursos/editar/:id', component: CursoFormComponent },

  // Rotas de Disciplinas
  { path: 'disciplinas', component: DisciplinaListComponent },
  { path: 'disciplinas/novo', component: DisciplinaFormComponent },
  { path: 'disciplinas/editar/:id', component: DisciplinaFormComponent },

  // Rotas de Turmas (NOVAS)
  { path: 'turmas', component: TurmaListComponent },               // Lista de turmas
  { path: 'turmas/novo', component: TurmaFormComponent },          // Formulário para nova turma
  { path: 'turmas/editar/:id', component: TurmaFormComponent },    // Edição de turma

  // Rota padrão (caso a URL esteja vazia, redireciona para /alunos)
  { path: '', redirectTo: '/alunos', pathMatch: 'full' }
];
