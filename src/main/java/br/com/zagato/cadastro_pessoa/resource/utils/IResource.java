package br.com.zagato.cadastro_pessoa.resource.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public interface IResource<T> {

    @GetMapping
    ResponseEntity<List<T>> get();

    @GetMapping("/{id}")
    ResponseEntity<T> get(@PathVariable UUID id);

    @PostMapping
    ResponseEntity<Void> insert(@RequestBody @Valid T t);

    @PutMapping
    ResponseEntity<Void> update(@RequestBody @Valid T t);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);
}