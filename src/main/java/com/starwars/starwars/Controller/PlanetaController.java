package com.starwars.starwars.Controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.starwars.starwars.Entity.Planeta;
import com.starwars.starwars.service.PlanetaService;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "planetas")
public class PlanetaController {

    @Autowired
    private PlanetaService planetaService;

    @GetMapping
    public ResponseEntity<List<Planeta>> getAll() {
        return new ResponseEntity<>(planetaService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Planeta> findById(@PathVariable(name = "id") String id) {
        Planeta planeta = planetaService.buscaPorId(id);
        return  isNull(planeta) ? ResponseEntity.notFound().build() : ResponseEntity.ok(planeta);
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<List<Planeta>> findByName(@PathVariable(name = "nome") String nome) {
        return new ResponseEntity<>(planetaService.buscarPorNome(nome), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Planeta> add(@Valid @RequestBody Planeta planeta, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(planetaService.save(planeta), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Planeta> update(@PathVariable String id, @Valid @RequestBody Planeta planeta, BindingResult result) {
        Planeta planetaCadastrado = planetaService.buscaPorId(id);

        if (isNull(planetaCadastrado)) {
            return ResponseEntity.notFound().build();
        }

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(planetaService.save(planeta), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remove(@PathVariable(name = "id") String id) {
        Planeta planeta = planetaService.buscaPorId(id);

        if (isNull(planeta)) {
            return ResponseEntity.notFound().build();
        }

        planetaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
