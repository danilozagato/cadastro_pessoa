package br.com.zagato.cadastro_pessoa.service;

import br.com.zagato.cadastro_pessoa.domain.Estado;
import br.com.zagato.cadastro_pessoa.repository.EstadoRepository;
import br.com.zagato.cadastro_pessoa.service.utils.CustomObjectNotFoundException;
import br.com.zagato.cadastro_pessoa.service.utils.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EstadoService implements IService<Estado> {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public Estado get(UUID id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return estado.orElseThrow(() -> new CustomObjectNotFoundException(id, Estado.class));
    }

    @Override
    public List<Estado> get() {
        return estadoRepository.findAll();
    }

    @Override
    public Estado insert(Estado Estado) {
        return estadoRepository.save(Estado);
    }

    @Override
    public Estado update(Estado estado) {
        return estadoRepository.findById(estado.getId()).map(es -> {
            if (estado.getNome() != null) es.setNome(estado.getNome());
            if (estado.getSigla() != null) es.setSigla(estado.getSigla());
            return estadoRepository.save(es);
        }).orElseThrow(() -> new CustomObjectNotFoundException(estado.getId(), Estado.class));
    }

    @Override
    public void delete(UUID id) {
        try {
            estadoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CustomObjectNotFoundException(id, Estado.class);
        }
    }
}
