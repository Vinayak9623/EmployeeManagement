package com.vsd.exception.customeEx;


public class EmployeeNotFoundException extends RuntimeException {

public EmployeeNotFoundException(String message){
    super(message);
}

}
