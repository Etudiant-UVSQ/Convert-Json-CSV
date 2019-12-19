package Exceptions;

public class NotValidPathException extends Exception{
    public NotValidPathException(String message, Throwable err){
        super(message,err);
    }
    public NotValidPathException(String message){
        super(message);
    }
}
