package br.com.zagato.cadastro_pessoa.service;

import br.com.zagato.cadastro_pessoa.domain.Cidade;
import br.com.zagato.cadastro_pessoa.repository.CidadeRepository;
import br.com.zagato.cadastro_pessoa.service.utils.CustomObjectNotFoundException;
import br.com.zagato.cadastro_pessoa.service.utils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CidadeService implements IService<Cidade> {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public Cidade get(UUID id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cidade.orElseThrow(() -> new CustomObjectNotFoundException(id, Cidade.class));
    }

    @Override
    public List<Cidade> get() {
        return cidadeRepository.findAll();
    }

    @Override
    public Cidade insert(Cidade Cidade) {
        return cidadeRepository.save(Cidade);
    }

    @Override
    public Cidade update(Cidade cidade) {
        return cidadeRepository.findById(cidade.getId()).map(c -> {
            if (cidade.getNome() != null) c.setNome(cidade.getNome());
            return cidadeRepository.save(c);
        }).orElseThrow(() -> new CustomObjectNotFoundException(cidade.getId(), Cidade.class));
    }

    @Override
    public void delete(UUID id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomObjectNotFoundException(id, Cidade.class);
        }
    }
}
