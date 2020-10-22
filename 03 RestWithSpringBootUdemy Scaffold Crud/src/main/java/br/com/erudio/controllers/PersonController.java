package br.com.erudio.controllers;

import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping
    public List<PersonVO> findAll() throws Exception {
        return services.findAll();
    }

    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable("id") Long id) throws Exception {
        return services.findById(id);
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        return services.create(person);
    }

    @PutMapping
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        return services.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        services.delete(id);
        return ResponseEntity.ok().build();
    }
}
