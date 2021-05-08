package br.com.zagato.cadastro_pessoa.service;

import br.com.zagato.cadastro_pessoa.domain.Pessoa;
import br.com.zagato.cadastro_pessoa.repository.PessoaRepository;
import br.com.zagato.cadastro_pessoa.service.utils.CustomObjectNotFoundException;
import br.com.zagato.cadastro_pessoa.service.utils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService implements IService<Pessoa> {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa get(UUID id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElseThrow(() -> new CustomObjectNotFoundException(id, Pessoa.class));
    }

    @Override
    public List<Pessoa> get() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa insert(Pessoa Pessoa) {
        return pessoaRepository.save(Pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return pessoaRepository.findById(pessoa.getId()).map(p -> {
            if (pessoa.getNome() != null) p.setNome(pessoa.getNome());
            if (pessoa.getSobrenome() != null) p.setSobrenome(pessoa.getSobrenome());
            return pessoaRepository.save(p);
        }).orElseThrow(() -> new CustomObjectNotFoundException(pessoa.getId(), Pessoa.class));
    }

    @Override
    public void delete(UUID id) {
        try {
            pessoaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomObjectNotFoundException(id, Pessoa.class);
        }
    }
}
