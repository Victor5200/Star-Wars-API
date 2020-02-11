package com.starwars.starwars.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsResponseWrapper {

    public int count;
    public String next;
    public String previous;
    public List<StarWarsApiWrapper> results;

}
