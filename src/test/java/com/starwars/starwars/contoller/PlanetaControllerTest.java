package com.starwars.starwars.contoller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.starwars.starwars.Entity.Planeta;
import com.starwars.starwars.repository.PlanetaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PlanetaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

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
    public void deveRetornarListaPlaneta() {
        ParameterizedTypeReference<List<Planeta>> responseType = new ParameterizedTypeReference<List<Planeta>>() {};
        ResponseEntity<List<Planeta>> response = restTemplate.exchange("/planetas",
                                                                    HttpMethod.GET,
                                                        null,
                                                                    responseType);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void deveRetornarPlanetaPorNome() {
        ParameterizedTypeReference<List<Planeta>> responseType = new ParameterizedTypeReference<List<Planeta>>() {};
        ResponseEntity<List<Planeta>> response = restTemplate.exchange("/planetas/nome/{name}",
                                                                        HttpMethod.GET,
                                                            null,
                                                                        responseType,
                                                            "Alderaan");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void deveRetornarPlanetaPorId() {
        Planeta planeta = planetaRepository.findByNome("Alderaan").get(0);
        ResponseEntity<Planeta> response = restTemplate.getForEntity("/planetas/{id}",
                                                                    Planeta.class,
                                                                    planeta.getId());
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getNome()).isNotNull();
    }

    @Test
    public void deveRetornarErro404() {
        ResponseEntity<Planeta> response = restTemplate.getForEntity("/planetas/{id}",
                                                                    Planeta.class,
                                                                    "1");
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void deveDeletarPorId() {
        Planeta planeta = planetaRepository.findByNome("Alderaan").get(0);
        ResponseEntity<String> response = restTemplate.exchange("/planetas/{id}",
                                                                HttpMethod.DELETE,
                                                    null,
                                                                String.class,
                                                                planeta.getId());
        assertThat(response.getStatusCodeValue()).isEqualTo(204);
    }

    @Test
    public void deveRetornarErro404AoDeletar() {
        ResponseEntity<String> response = restTemplate.exchange("/planetas/{id}",
                                                                HttpMethod.DELETE,
                                                                null,
                                                                String.class,
                                                                "1");
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void deveSalvarNovoPlaneta() {
        Planeta planeta = new Planeta(null, "Dagobah", "murky", "swamp, jungles", "0");
        ResponseEntity<Planeta> response = restTemplate.exchange("/planetas",
                                                                HttpMethod.POST,
                                                                new HttpEntity<>(planeta),
                                                                Planeta.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void deveRetornarErroAoSalvarPlaneta() {
        Planeta planeta = new Planeta(null, "", "", "", "0");
        ResponseEntity<Planeta> response = restTemplate.exchange("/planetas",
                HttpMethod.POST,
                new HttpEntity<>(planeta),
                Planeta.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void deveRetornarErro400AoSalvarPlaneta() {
        Planeta planeta = new Planeta();
        ResponseEntity<Planeta> response = restTemplate.exchange("/planetas",
                HttpMethod.POST,
                new HttpEntity<>(planeta),
                Planeta.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }
}


