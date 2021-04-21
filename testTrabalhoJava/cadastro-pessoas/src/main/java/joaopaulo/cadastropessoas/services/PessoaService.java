package joaopaulo.cadastropessoas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import joaopaulo.cadastropessoas.entidades.Pessoa;
import joaopaulo.cadastropessoas.entidades.valueObjects.CpfUtils;
import joaopaulo.cadastropessoas.repositotories.PessoaReposiory;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {
	
	@Autowired
	private PessoaReposiory repository;
	
	public List<Pessoa> findAll(){
		return repository.findAll();
	}

	public Pessoa findById(long id) {
		Optional<Pessoa> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	public Pessoa insert(Pessoa obj) {
		validarCadastro(obj.getCpf());
		return repository.save(obj);
	}
	
	public boolean delete (long id) {
		Optional<Pessoa> obj = repository.findById(id);
		if (!obj.isPresent()) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}
	public Pessoa update(long id, Pessoa obj) {
		Optional<Pessoa> valid = repository.findById(id);
		if (!valid.isPresent()) {
			return null;
		}
		return repository.save(update(valid.get(), obj));
		
	}

	private Pessoa update(Pessoa entity, Pessoa obj) {
		entity.setNome(obj.getNome());
		entity.setCpf(obj.getCpf());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setSexo(obj.getSexo());
		entity.setBiografia(obj.getBiografia());
		return entity;
	}
	
	private void validarCadastro(String cpfString) {
		
		CpfUtils.validar(cpfString);
		
		Optional<Pessoa> pessoa = repository.findByCpf(cpfString);
		
		if (pessoa.isPresent()) {
			throw new RuntimeException("CPF cadastrado");
		}
	}
}
