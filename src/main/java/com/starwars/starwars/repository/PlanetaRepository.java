package com.starwars.starwars.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.starwars.starwars.Entity.Planeta;

import java.util.List;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {

    List<Planeta> findByNome(String nome);

}
