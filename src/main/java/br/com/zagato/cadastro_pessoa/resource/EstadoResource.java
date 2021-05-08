package br.com.zagato.cadastro_pessoa.resource;

import br.com.zagato.cadastro_pessoa.domain.Estado;
import br.com.zagato.cadastro_pessoa.resource.utils.IResource;
import br.com.zagato.cadastro_pessoa.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/estado")
public class EstadoResource implements IResource<Estado> {

    @Autowired
    private EstadoService estadoService;

    @Override
    @GetMapping
    public ResponseEntity<List<Estado>> get() {
        List<Estado> estados = estadoService.get();
        return ResponseEntity.ok().body(estados);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Estado> get(UUID id) {
        Estado estado = estadoService.get(id);
        return ResponseEntity.ok().body(estado);
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid Estado estado) {
        estado = estadoService.insert(estado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid Estado estado) {
        estado = estadoService.update(estado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + estado.getId()).buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(UUID id) {
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
