// A palavra-chave 'export' torna a interface 'Curso' visível e utilizável por outros arquivos da sua aplicação Angular.
// Sem o 'export', arquivos como o CursoService ou CursoListComponent não conseguiriam importá-la.
export interface Curso {

  // O '?' depois do nome do campo 'id' significa que este campo é opcional.
  // Isso é útil porque, ao criar um novo curso, ele ainda não tem um ID (que será gerado pelo banco de dados).
  // Ao editar um curso, o ID será obrigatório.
  id?: string;

  // Define que todo objeto Curso deve ter uma propriedade 'nome', e o valor deve ser do tipo string (texto).
  // Exemplo: "Ciência da Computação".
  nome: string;

  // Define a propriedade 'nivel' como uma string.
  // Exemplo: "Graduação", "Pós-graduação", "Técnico".
  nivel: string;

  // Define a propriedade 'modalidade' como uma string.
  // Exemplo: "Presencial", "EaD", "Híbrido".
  modalidade: string;

  // Define a propriedade 'turno' como uma string.
  // Exemplo: "Matutino", "Vespertino", "Noturno", "Integral".
  turno: string;
}