package com.vsd.exception.customeEx;

public class LetterNotFoundException extends RuntimeException{

    public LetterNotFoundException(String message){
        super(message);
    }
}
