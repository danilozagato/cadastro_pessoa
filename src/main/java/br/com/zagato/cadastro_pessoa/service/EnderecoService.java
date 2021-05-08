package br.com.zagato.cadastro_pessoa.service;

import br.com.zagato.cadastro_pessoa.domain.Endereco;
import br.com.zagato.cadastro_pessoa.repository.EnderecoRepository;
import br.com.zagato.cadastro_pessoa.service.utils.CustomObjectNotFoundException;
import br.com.zagato.cadastro_pessoa.service.utils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService implements IService<Endereco> {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Endereco get(UUID id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new CustomObjectNotFoundException(id, Endereco.class));
    }

    @Override
    public List<Endereco> get() {
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco insert(Endereco Endereco) {
        return enderecoRepository.save(Endereco);
    }

    @Override
    public Endereco update(Endereco endereco) {
        return enderecoRepository.findById(endereco.getId()).map(en -> {
            if (endereco.getCidade() != null) en.setCidade(endereco.getCidade());
            if (endereco.getCep() != null) en.setCep(endereco.getCep());
            if (endereco.getLogradouro() !=null) en.setLogradouro(endereco.getLogradouro());
            return enderecoRepository.save(en);
        }).orElseThrow(() -> new CustomObjectNotFoundException(endereco.getId(), Endereco.class));
    }

    @Override
    public void delete(UUID id) {
        try {
            enderecoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomObjectNotFoundException(id, Endereco.class);
        }
    }
}
