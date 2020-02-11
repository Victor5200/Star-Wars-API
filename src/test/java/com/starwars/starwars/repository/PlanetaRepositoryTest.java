package com.starwars.starwars.repository;

import com.starwars.starwars.Entity.Planeta;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetaRepositoryTest {

    @Autowired
    private PlanetaRepository planetaRepository;

    @Before
    public void init() {
        planetaRepository.deleteAll();
        planetaRepository.save(new Planeta(null,
                "Alderaan", "temperate", "grasslands, mountains", "0"));
    }

    @After
    public void finish() {
        planetaRepository.deleteAll();
    }

    @Test
    public void buscarTodosPlanetas() {
        List<Planeta> planets = planetaRepository.findAll();
        assertThat(planets).isNotEmpty();
        assertThat(planets.size()).isEqualTo(1);
    }

    @Test
    public void criarPlaneta() {
        Planeta planet = new Planeta(null, "Yavin IV", "temperate, tropical", "jungle, rainforests", "1");
        planetaRepository.save(planet);
        assertThat(planet.getId()).isNotNull();
        assertThat(planet.getNome()).isEqualTo("Yavin IV");
        assertThat(planet.getAparicoes()).isEqualTo("1");
    }

    @Test
    public void deletarPlanetaCadastrado() {
        Planeta planet = new Planeta(null, "Dagobah", "murky", "swamp, jungles", "0");
        planetaRepository.save(planet);
        planetaRepository.deleteById(planet.getId());
        assertThat(planetaRepository.findById(planet.getId()).isPresent()).isFalse();
    }

    @Test
    public void findByIdShould() {
        Planeta planeta = new Planeta(null, "Dagobah", "murky", "swamp, jungles", "0");
        planetaRepository.save(planeta);
        Optional<Planeta> planetaAposPersistencia =
                planetaRepository.findById(planeta.getId());
        assertThat(planetaAposPersistencia.isPresent()).isTrue();
        assertThat(planetaAposPersistencia.get().getNome()).isEqualTo("Dagobah");
    }

    @Test
    public void findByNameShould() {
        List<Planeta> planeta = planetaRepository.findByNome("Alderaan");
        assertThat(planeta).isNotEmpty();
        assertThat(planeta.size()).isEqualTo(1);
        assertThat(planeta.get(0).getNome()).isEqualTo("Alderaan");
    }
}