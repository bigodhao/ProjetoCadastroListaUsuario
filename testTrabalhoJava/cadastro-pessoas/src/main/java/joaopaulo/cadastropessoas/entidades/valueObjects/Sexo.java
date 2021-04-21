package joaopaulo.cadastropessoas.entidades.valueObjects;

public enum Sexo {
	
	MASCULINO('M'),
	FEMININO('F'),
	OUTROS('O');
	
	private char valor;
	
	private Sexo(char valor) {
	this.valor = valor;
	}

	public char getSexo() {
		return valor;
	}
	public static Sexo valueOf(char valor) {
		if (valor == 'M') {
			return MASCULINO;
		}else if (valor == 'F') {
			return FEMININO;
		}else if (valor == 'O'){
			return OUTROS;
		}else {
			throw new IllegalArgumentException("Sexo invalido");
		}
	}
	
}
