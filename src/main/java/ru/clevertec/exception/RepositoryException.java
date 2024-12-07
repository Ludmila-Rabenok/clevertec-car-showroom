package ru.clevertec.exception;

public class RepositoryException extends RuntimeException{
    public RepositoryException(String message){
        super(message);
    }
    public RepositoryException(Throwable throwable){
        super(throwable);
    }
}
