package br.com.cinepoti.cinepoti_api.util;

public class CPFValidator {

    // Metodo principal para validar CPF
    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }

        // Verifica se todos os números são iguais, o que é inválido
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
                cpf.equals("99999999999")) {
            return false;
        }

        // Valida os dois dígitos verificadores
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += (cpf.charAt(i) - '0') * (10 - i);
            sum2 += (cpf.charAt(i) - '0') * (11 - i);
        }

        int checkDigit1 = 11 - (sum1 % 11);
        checkDigit1 = checkDigit1 >= 10 ? 0 : checkDigit1;

        int checkDigit2 = 11 - (sum2 % 11);
        checkDigit2 = checkDigit2 >= 10 ? 0 : checkDigit2;

        return checkDigit1 == (cpf.charAt(9) - '0') && checkDigit2 == (cpf.charAt(10) - '0');
    }

    // Metodo para lançar exceção caso o CPF seja inválido
    public static void validate(String cpf) {
        if (!isValid(cpf)) {
            throw new IllegalArgumentException("Invalid CPF");
        }
    }
}
