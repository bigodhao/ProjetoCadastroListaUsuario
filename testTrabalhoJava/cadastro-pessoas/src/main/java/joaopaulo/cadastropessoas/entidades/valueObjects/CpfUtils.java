package joaopaulo.cadastropessoas.entidades.valueObjects;

import joaopaulo.cadastropessoas.resource.exceptions.CpfInvalidExceptions;

public class CpfUtils {
	
    
    public static void validar(String valor){
        if (valor.length() != 11){
        	throw new CpfInvalidExceptions("CPF invalido");
        }else{
            if(valor.equals("00000000000")||valor.equals("11111111111")||valor.equals("22222222222")||valor.equals("33333333333")||
            valor.equals("44444444444")||valor.equals("55555555555")||valor.equals("66666666666")||valor.equals("77777777777")||
            valor.equals("88888888888")||valor.equals("99999999999")){
            	throw new CpfInvalidExceptions("CPF invalido");
            }else{
                    int codVerificador1, codVerificador2;
                    int validacao = 10;
                    int total = 0;
                    for (int aux = 0; aux < 9; aux++) {
                        int aux1 = (valor.charAt(aux) - 48);
                        total = total + (validacao * aux1);
                        validacao = validacao - 1;
                    }
                    int confirmacao = 11 - (total % 11);
                    codVerificador1 = (valor.charAt(9) - 48);
                    codVerificador2 = (valor.charAt(10) - 48);
                    if ((confirmacao == 10)||(confirmacao == 11)){
                        confirmacao = 0;
                    }
                    if(confirmacao != codVerificador1){
                    	throw new CpfInvalidExceptions("CPF invalido");
                    }else{
                        validacao = 11;
                        total = 0;
                        for (int aux = 0; aux < 10; aux++) {
                            int aux1 = (valor.charAt(aux) - 48);
                            total = total + (validacao * aux1);
                        }
                        confirmacao = 11 - (total % 11);
                        if ((confirmacao == 10)||(confirmacao == 11)){
                            confirmacao = 0;
                        }
                        if (confirmacao != codVerificador2){
                        	throw new CpfInvalidExceptions("CPF invalido");
                        }
                    }
            }
        }
    }
}
