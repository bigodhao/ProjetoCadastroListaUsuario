package joaopaulo.cadastropessoas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import joaopaulo.cadastropessoas.entidades.Pessoa;
import joaopaulo.cadastropessoas.entidades.valueObjects.Sexo;
import joaopaulo.cadastropessoas.repositotories.PessoaReposiory;
import joaopaulo.cadastropessoas.services.PessoaService;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {
	
	@Mock
	private PessoaReposiory repository;
	
	@InjectMocks
	private PessoaService service;
	
	@Before
	public void setup() {
		service = new PessoaService(repository);

	}
	
	@Test
    public void findById() {

		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");

		when(repository.findById(100L)).thenReturn(Optional.of(pessoa));

        Pessoa result = service.findById(100L);
        
        assertThat(result.getId()).isNotNull();
        assertThat(result.getNome()).isEqualTo(pessoa.getNome());
        assertThat(result.getCpf()).isEqualTo(pessoa.getCpf());
        assertThat(result.getDataNascimento()).isEqualTo(pessoa.getDataNascimento());
    }
	
	@Test
    public void save() {

		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		

		when(repository.save(pessoa)).thenReturn(pessoa);

        Pessoa result = service.insert(pessoa);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getNome()).isEqualTo(pessoa.getNome());
        assertThat(result.getCpf()).isEqualTo(pessoa.getCpf());
        assertThat(result.getDataNascimento()).isEqualTo(pessoa.getDataNascimento());
    }
	
	@Test
    public void saveErrorCpfInvalido() {

		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("999999999");
        
        assertThrows(RuntimeException.class,() -> service.insert(pessoa), "");

    }
	
	@Test
    public void findByCpfCadastrado() {
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		
		when(repository.findByCpf("10026219840")).thenReturn(Optional.of(pessoa));
	
        
        assertThrows(RuntimeException.class,() -> service.insert(pessoa), "");

    }
	
	@Test
    public void update() {

		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		pessoa.setId(150L);
		
		when(repository.findById(150L)).thenReturn(Optional.of(pessoa));
		
		when(repository.save(pessoa)).thenReturn(pessoa);
		
		pessoa.setNome("lucas");
		pessoa.setCpf("04529447910");
		
        Pessoa result = service.update(150L, pessoa);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getNome()).isEqualTo(pessoa.getNome());
        assertThat(result.getCpf()).isEqualTo(pessoa.getCpf());
        assertThat(result.getDataNascimento()).isEqualTo(pessoa.getDataNascimento());
    }
	
	@Test
    public void updateError() {
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		pessoa.setId(150L);
		
		when(repository.findById(150L)).thenReturn(Optional.ofNullable(null));
		
		pessoa.setNome("lucas");
		pessoa.setCpf("04529447910");
		
        Pessoa result = service.update(150L, pessoa);

        assertThat(result).isNull();
    }
	
	@Test
    public void delete() {
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		pessoa.setId(150L);
		
		when(repository.findById(150L)).thenReturn(Optional.of(pessoa));
		
		boolean result = service.delete(pessoa.getId());
		
		assertThat(result).isTrue();
        
    }
	
	@Test
    public void deleteError() {
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		pessoa.setId(150L);
		
		when(repository.findById(150L)).thenReturn(Optional.ofNullable(null));
		
		boolean result = service.delete(150L);
		
		assertThat(result).isFalse();
        
    }
	
	@Test
    public void findAll() {
		
		List<Pessoa> pessoaList = new ArrayList<>();
		
		repository.findAll();
		
		pessoaList = service.findAll();
		
        
    }
	
	


}
