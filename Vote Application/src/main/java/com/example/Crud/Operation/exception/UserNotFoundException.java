package com.example.Crud.Operation.exception;


import com.example.Crud.Operation.entity.User;

public class UserNotFoundException extends  RuntimeException{
public UserNotFoundException(String message){
    super(message);
}
}
