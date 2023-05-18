package br.com.forum.utility;

public final class Util {

    public static boolean isNotEmptyOrNull(Object valor){
        return (valor != null) && (! "".equals(valor));
    }
}
