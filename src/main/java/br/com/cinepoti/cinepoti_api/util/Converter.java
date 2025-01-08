package br.com.cinepoti.cinepoti_api.util;

public class Converter {

    public static <T extends Enum<T>> T stringToEnum(Class<T> enumType, String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty");
        }
        try {
            return Enum.valueOf(enumType, value.toUpperCase()); // Converte para maiúsculas para evitar erros de case
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value for enum " + enumType.getSimpleName() + ": " + value, e);
        }
    }

}
