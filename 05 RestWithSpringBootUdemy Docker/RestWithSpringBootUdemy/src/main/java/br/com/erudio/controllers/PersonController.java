package br.com.erudio.controllers;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Api(value = "Person Endpoint", description = "Description for person", tags = {"PersonEndpoint"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices services;

    @Autowired
    private PagedResourcesAssembler<PersonVO> assembler;

    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> persons = services.findAll(pageable);
        persons
            .stream()
            .forEach(p -> {
                    try {
                        p.add(
                                linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
                            );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            );

        PagedResources<?> resources = assembler.toResource(persons);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findPersonByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> persons = services.findPersonByName(firstName, pageable);
        persons
                .stream()
                .forEach(p -> {
                            try {
                                p.add(
                                        linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
                                );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );

        PagedResources<?> resources = assembler.toResource(persons);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping( value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" } )
    public PersonVO findById(@PathVariable("id") Long id) throws Exception {
        PersonVO personVO = services.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        PersonVO personVO = services.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return services.create(person);
    }

    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        PersonVO personVO = services.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return services.update(person);
    }

    @PatchMapping( value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" } )
    public PersonVO disablePerson(@PathVariable("id") Long id) throws Exception {
        PersonVO personVO = services.disablePerson(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        services.delete(id);
        return ResponseEntity.ok().build();
    }
}
