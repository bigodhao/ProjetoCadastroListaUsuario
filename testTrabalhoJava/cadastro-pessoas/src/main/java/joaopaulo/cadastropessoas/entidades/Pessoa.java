package joaopaulo.cadastropessoas.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import joaopaulo.cadastropessoas.entidades.valueObjects.Sexo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String nome;
	
	//localDate
	@NotNull
	private Date dataNascimento;
	
	@NotNull
	private String cpf;
	
	@NotNull
	private char sexo;
	private String biografia;

	public Pessoa(String nome, Date dataNascimento, Sexo sexo, String biografia) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		setSexo(sexo);
		this.biografia = biografia;
	}

	public long getId() {
		return id;
	}

	public Sexo getSexo() {
		return Sexo.valueOf(sexo);
	}

	public void setSexo(Sexo sexo) {
		if (sexo != null) {
			this.sexo = sexo.getSexo();
		}
	}

}
