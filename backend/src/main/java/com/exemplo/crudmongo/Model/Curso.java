package com.exemplo.crudmongo.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cursos") // Especifica o nome da coleção no MongoDB
public class Curso {

    @Id
    private String id; // Corresponde ao _id no MongoDB

    private String nome;

    private String nivel; // Ex: Graduação, Pós-graduação

    private String modalidade; // Ex: Presencial, EaD

    private String turno; // Ex: Matutino, Vespertino, Noturno, Integral

    // Getters e Setters são gerados pelo @Data do Lombok
    // Construtores, se necessário (Lombok pode gerar @NoArgsConstructor, @AllArgsConstructor)
}