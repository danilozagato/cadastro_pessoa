package br.com.zagato.cadastro_pessoa.resource;

import br.com.zagato.cadastro_pessoa.domain.Cidade;
import br.com.zagato.cadastro_pessoa.resource.utils.IResource;
import br.com.zagato.cadastro_pessoa.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeResource implements IResource<Cidade> {

    @Autowired
    private CidadeService cidadeService;

    @Override
    @GetMapping
    public ResponseEntity<List<Cidade>> get() {
        List<Cidade> cidades = cidadeService.get();
        return ResponseEntity.ok().body(cidades);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Cidade> get(@PathVariable UUID id) {
        Cidade cidade = cidadeService.get(id);
        return ResponseEntity.ok().body(cidade);
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid Cidade cidade) {
        cidade = cidadeService.insert(cidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cidade.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid Cidade cidade) {
        cidade = cidadeService.update(cidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + cidade.getId()).buildAndExpand(cidade.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
