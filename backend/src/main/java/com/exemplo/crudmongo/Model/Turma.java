package com.exemplo.crudmongo.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "turmas") // Especifica o nome da coleção no MongoDB
public class Turma {

    @Id
    private String id; // Corresponde ao _id no MongoDB

    private String disciplinaId; // ID da disciplina associada a esta turma

    private Integer ano;

    private Integer semestre;

    private String professor; // Nome ou ID do professor responsável

    // Getters e Setters são gerados pelo @Data do Lombok
    // Construtores, se necessário (Lombok pode gerar @NoArgsConstructor, @AllArgsConstructor)
}