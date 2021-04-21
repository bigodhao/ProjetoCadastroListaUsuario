package joaopaulo.cadastropessoas.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

import joaopaulo.cadastropessoas.entidades.Pessoa;
import joaopaulo.cadastropessoas.entidades.valueObjects.Sexo;
import joaopaulo.cadastropessoas.resource.PessoaResource;
import joaopaulo.cadastropessoas.services.PessoaService;

@RunWith(MockitoJUnitRunner.class)
public class PessoaControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private com.fasterxml.jackson.databind.ObjectMapper ObjectMapper;
	
	@Mock
	private PessoaService serviceMock = mock(PessoaService.class);
	
	@InjectMocks
	private PessoaResource resource;
	
	@Before
	public void setup() {
		
		ObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(resource).setControllerAdvice(new JsonViewResponseBodyAdvice()) .build();
		
		resource.setService(serviceMock);
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		
	}
	
	@Test
	public void BuscarAll() throws Exception {
		
		this.mockMvc.perform(get("/pessoas")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
      
	}
	
	@Test
	public void BuscarPessoa() throws Exception{
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		pessoa.setId(150L);
		
		when(serviceMock.findById(150L)).thenReturn(pessoa);
		
		this.mockMvc.perform(get("/pessoas/150")
                .contentType("application/json"))
                .andExpect(status().isOk());
		
	}
	
	@Test
	public void BuscarPessoaError() throws Exception{
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		pessoa.setId(150L);
		
		when(serviceMock.findById(150L)).thenReturn(null);
		
		this.mockMvc.perform(get("/pessoas/150")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
		
	}
	
	@Test
    public void SalvarPessoa() throws Exception {
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		
		when(serviceMock.insert(pessoa)).thenReturn(pessoa);
		
        this.mockMvc.perform(post("/pessoas")
                .contentType("application/json")
                .content(ObjectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isCreated());      

    }
	
	@Test
    public void deletarPessoa() throws Exception {
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		pessoa.setId(150L);
		
		when(serviceMock.delete(150L)).thenReturn(true);
		
        this.mockMvc.perform(delete("/pessoas/150")
                .contentType("application/json"))
                .andExpect(status().is2xxSuccessful());      

    }
	
	@Test
    public void deletarPessoaError() throws Exception {
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		pessoa.setId(150L);
		
		when(serviceMock.delete(150L)).thenReturn(false);
		
        this.mockMvc.perform(delete("/pessoas/150")
                .contentType("application/json"))
                .andExpect(status().isNotFound());      

    }
	
	@Test
    public void editarPessoa() throws Exception {
		
		Pessoa pessoa = new Pessoa("pedro", new Date(2/1/2021), Sexo.MASCULINO, "dsacsdsgdfgds");
		pessoa.setCpf("10026219840");
		pessoa.setId(150L);

        this.mockMvc.perform(put("/pessoas/150")
                .contentType("application/json")
                .content(ObjectMapper.writeValueAsString(pessoa)))
    			.andExpect(status().isOk());
        
        verify(serviceMock,times(1)).update(150, pessoa);
	}

}
