package com.cascado.server.error;

import java.util.NoSuchElementException;

public class GettingCityException extends NoSuchElementException {

    public GettingCityException(){

    }

    public GettingCityException(String s){
        super(s);
    }

}
