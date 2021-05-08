package br.com.zagato.cadastro_pessoa.repository;

import br.com.zagato.cadastro_pessoa.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, UUID> {
}
