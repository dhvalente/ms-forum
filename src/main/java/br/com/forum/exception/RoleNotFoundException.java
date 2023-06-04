package br.com.forum.exception;

public class RoleNotFoundException extends RuntimeException  {

    public RoleNotFoundException(String nameRole) {
        super("Role com ID: "+ nameRole + " não encontrado(a)");
    }
}
