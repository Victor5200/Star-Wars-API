package com.starwars.starwars.service;

import com.starwars.starwars.Entity.Planeta;

import java.util.List;

public interface PlanetaService {
    List<Planeta> buscarTodos();
    List<Planeta> buscarPorNome(String name);
    Planeta buscaPorId(String id);
    Planeta save(Planeta planet);
    void deletar(String id);
}
