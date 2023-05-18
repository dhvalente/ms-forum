package br.com.forum.exception;

public class PostNotFoundException extends RuntimeException  {

    public PostNotFoundException(String id) {
        super("Post com ID: "+ id + " não encontrado(a)");
    }
}
