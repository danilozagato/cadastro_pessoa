package br.com.zagato.cadastro_pessoa.service.utils;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomObjectNotFoundException extends ObjectNotFoundException {

    public CustomObjectNotFoundException(Integer id, Class<?> clazz) {
        super(id, "Objeto não Encontrado: Id " + id + " , Tipo: " + clazz.getName());
    }

    public CustomObjectNotFoundException(UUID id, Class<?> clazz) {
        super(id, "Objeto não Encontrado: Id " + id + " , Tipo: " + clazz.getName());
    }
}
