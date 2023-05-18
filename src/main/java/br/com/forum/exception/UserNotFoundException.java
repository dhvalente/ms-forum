package br.com.forum.exception;

public class UserNotFoundException extends RuntimeException  {

    public UserNotFoundException(String id) {
        super("Usuário com ID: "+ id + " não encontrado(a)");
    }
}
