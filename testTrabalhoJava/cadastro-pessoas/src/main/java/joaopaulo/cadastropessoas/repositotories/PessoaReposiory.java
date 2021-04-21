package joaopaulo.cadastropessoas.repositotories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import joaopaulo.cadastropessoas.entidades.Pessoa;

@Repository
public interface PessoaReposiory extends JpaRepository<Pessoa, Long>{
	
	Optional<Pessoa> findByCpf(String cpf);
	
}
