package com.starwars.starwars.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "planets")
public class Planeta implements Serializable {

    @Id
    private String id;

    @TextIndexed
    private String nome;

    @TextIndexed
    private String clima;

    @TextIndexed
    private String terreno;

    @TextIndexed
    private String aparicoes;

}