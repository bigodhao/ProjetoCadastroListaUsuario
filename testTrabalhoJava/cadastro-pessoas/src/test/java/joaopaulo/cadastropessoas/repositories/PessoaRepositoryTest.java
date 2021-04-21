package joaopaulo.cadastropessoas.repositories;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import joaopaulo.cadastropessoas.entidades.Pessoa;
import joaopaulo.cadastropessoas.entidades.valueObjects.Sexo;
import joaopaulo.cadastropessoas.repositotories.PessoaReposiory;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTest {
	@Autowired
	private PessoaReposiory pessoaRepository;
	
	@Test
	public void createSholdPersistData() {
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		Pessoa salva = this.pessoaRepository.save(pessoa);
		Assertions.assertThat(salva.getId()).isNotNull();
		Assertions.assertThat(salva.getNome()).isEqualTo("pedro");
	}
	
	@Test
	public void createShouldRemoveData() {
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021) , Sexo.MASCULINO, "dsacsdsgdfgds");
		this.pessoaRepository.save(pessoa);
		pessoaRepository.delete(pessoa);		
		Assertions.assertThat(pessoaRepository.findById(pessoa.getId())).isNotNull();

	}
	
	@Test
	public void updateShouldChangeAndPersistData() {
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		this.pessoaRepository.save(pessoa);
		pessoa.setNome("joao");
		pessoa.setCpf("04529447910");
		pessoa.setDataNascimento(new Date(20/10/2020));
		pessoa = this.pessoaRepository.save(pessoa);
		Assertions.assertThat(pessoa.getNome()).isEqualTo("joao");
		Assertions.assertThat(pessoa.getDataNascimento()).isEqualTo(new Date(20/10/2020));
	}
	
	@Test
	public void findByCpfShouldChangeAndPersistData() {
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		this.pessoaRepository.save(pessoa);
		Optional<Pessoa> pessoaCerta = pessoaRepository.findByCpf(pessoa.getCpf());
		Assertions.assertThat(pessoaCerta.get().getId()).isNotNull();
		Assertions.assertThat(pessoaCerta.get().getNome()).isEqualTo("pedro");
		Assertions.assertThat(pessoaCerta.get().getCpf()).isEqualTo("10026219840");
	}
}
