package joaopaulo.cadastropessoas.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import joaopaulo.cadastropessoas.entidades.Pessoa;
import joaopaulo.cadastropessoas.entidades.valueObjects.CpfUtils;
import joaopaulo.cadastropessoas.entidades.valueObjects.Sexo;
import joaopaulo.cadastropessoas.repositotories.PessoaReposiory;

@Configuration
public class testConfig implements CommandLineRunner {
	
	//@Autowired
	//private PessoaReposiory pessoaRepository;

	@Override
	public void run(String... args) throws Exception {
		//Pessoa p1 = new Pessoa("joao", "20/10/2020", new Cpf("04529447910") , Sexo.MASCULINO, "dsacs");
		//Pessoa p2 = new Pessoa("pedro", "2/1/2021", new Cpf("10026219840") , Sexo.MASCULINO, "dsacsdsgdfgds");
		
		//pessoaRepository.saveAll(Arrays.asList(p1, p2));
		
	}
}
