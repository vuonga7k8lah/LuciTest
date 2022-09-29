package com.vuongkma.luci.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
    super(message);
    }
}