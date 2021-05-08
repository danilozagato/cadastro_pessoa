package br.com.zagato.cadastro_pessoa;

import br.com.zagato.cadastro_pessoa.resource.utils.MyMessageInterpolator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.Validation;

@SpringBootApplication
public class CadastroPessoaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CadastroPessoaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
