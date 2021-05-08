package br.com.zagato.cadastro_pessoa.resource;

import br.com.zagato.cadastro_pessoa.domain.Pessoa;
import br.com.zagato.cadastro_pessoa.resource.utils.IResource;
import br.com.zagato.cadastro_pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource implements IResource<Pessoa> {

    @Autowired
    private PessoaService pessoaService;

    @Override
    @GetMapping
    public ResponseEntity<List<Pessoa>> get() {
        List<Pessoa> pessoas = pessoaService.get();
        return ResponseEntity.ok().body(pessoas);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> get(UUID id) {
        Pessoa pessoa = pessoaService.get(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> insert(Pessoa pessoa) {
        pessoa = pessoaService.insert(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> update(Pessoa pessoa) {
        pessoa = pessoaService.update(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + pessoa.getId()).buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(UUID id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
