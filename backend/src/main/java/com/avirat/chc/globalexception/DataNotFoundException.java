package com.avirat.chc.globalexception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(){
        super();

    }

    public DataNotFoundException(String msg){
        super(msg);

    }

}
