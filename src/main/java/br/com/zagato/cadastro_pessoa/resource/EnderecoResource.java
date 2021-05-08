package br.com.zagato.cadastro_pessoa.resource;

import br.com.zagato.cadastro_pessoa.domain.Endereco;
import br.com.zagato.cadastro_pessoa.resource.utils.IResource;
import br.com.zagato.cadastro_pessoa.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoResource implements IResource<Endereco> {

    @Autowired
    private EnderecoService enderecoService;

    @Override
    @GetMapping
    public ResponseEntity<List<Endereco>> get() {
        List<Endereco> enderecos = enderecoService.get();
        return ResponseEntity.ok().body(enderecos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> get(UUID id) {
        Endereco endereco = enderecoService.get(id);
        return ResponseEntity.ok().body(endereco);
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> insert(Endereco endereco) {
        endereco = enderecoService.insert(endereco);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> update(Endereco endereco) {
        endereco = enderecoService.update(endereco);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + endereco.getId()).buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(UUID id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
