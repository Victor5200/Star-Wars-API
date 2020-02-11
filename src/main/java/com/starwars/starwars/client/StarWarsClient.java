package com.starwars.starwars.client;

import com.starwars.starwars.Entity.StarWarsResponseWrapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class StarWarsClient {

    public static String buscarQuantidadeAparicoesFilmePorNomePlaneta(String name) {
        Long quantidadeAparicoes = 0l;

        try {
            HttpEntity<String> entity = new HttpEntity<>("parameters", getHttpHeaders());

            ResponseEntity<StarWarsResponseWrapper> result = new RestTemplate().exchange( //
                                                            "https://swapi.co/api/planets/", //
                                                                HttpMethod.GET,  //
                                                                entity, //
                                                                StarWarsResponseWrapper.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                StarWarsResponseWrapper planets = result.getBody();

                quantidadeAparicoes = planets.getResults().stream() //
                                .filter(planeta -> planeta.getName().contentEquals(name)) //
                                .flatMap(planeta -> planeta.getFilmsUrls().stream()) //
                                .count();

                return quantidadeAparicoes.toString();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return quantidadeAparicoes.toString();
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("User-Agent", "SWAPI-Java-Client/1.0");
        return headers;
    }
}
