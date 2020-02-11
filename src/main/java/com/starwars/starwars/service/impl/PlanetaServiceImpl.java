package com.starwars.starwars.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.starwars.starwars.Entity.Planeta;
import com.starwars.starwars.client.StarWarsClient;
import com.starwars.starwars.repository.PlanetaRepository;
import com.starwars.starwars.service.PlanetaService;

@Service
public class PlanetaServiceImpl implements PlanetaService {

    @Resource
    private PlanetaRepository repository;

    @Override
    public List<Planeta> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public List<Planeta> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public Planeta buscaPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Planeta save(Planeta planeta) {
        planeta.setAparicoes(StarWarsClient.buscarQuantidadeAparicoesFilmePorNomePlaneta(planeta.getNome()));
        return repository.save(planeta);
    }

    @Override
    public void deletar(String id) {
        repository.deleteById(id);
    }
}
