package br.com.zagato.cadastro_pessoa.service.utils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IService<T> {

    T get(UUID id);

    List<T> get();

    T insert(T t);

    T update(T t);

    void delete(UUID id);
}
