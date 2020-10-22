package br.com.erudio.controllers;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "Book Endpoint", description = "Description for book", tags = {"BookEndpoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    BookServices services;

    @Autowired
    PagedResourcesAssembler<BookVO> assembler;

    @ApiOperation(value = "Find all people recorded")
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));

        Page<BookVO> books = services.findAll(pageable);
        books
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

        PagedResources<?> resources = assembler.toResource(books);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping( value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" } )
    public BookVO findById(@PathVariable("id") Long id) throws Exception {
        BookVO bookVo = services.findById(id);
        bookVo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return bookVo;
    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO create(@RequestBody BookVO person) throws Exception {
        BookVO bookVo = services.create(person);
        bookVo.add(linkTo(methodOn(PersonController.class).findById(bookVo.getKey())).withSelfRel());
        return services.create(person);
    }

    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO update(@RequestBody BookVO person) throws Exception {
        BookVO bookVo = services.create(person);
        bookVo.add(linkTo(methodOn(PersonController.class).findById(bookVo.getKey())).withSelfRel());
        return services.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        services.delete(id);
        return ResponseEntity.ok().build();
    }
}
