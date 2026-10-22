package com.avirat.chc.globalexceptionHandler;

import com.avirat.chc.globalexception.DataNotFoundException;
import com.avirat.chc.helper.Messages;
import jakarta.el.MethodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ownGlobalException {
    //Sevral method

    @ExceptionHandler(RuntimeException.class )
    public ResponseEntity<Messages> runTimeExceptionHandler( RuntimeException e ){

        String msg = e.getMessage();

        Messages messages = Messages.builder().msg(msg).build();

        return new ResponseEntity<>(messages, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Messages> dataNotFoundExceptionHandker(DataNotFoundException dataNotFoundException){

        String msg1 = dataNotFoundException.getMessage();
        Messages messages = Messages.builder().msg(msg1).build();

        return new ResponseEntity<>(messages,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodNotFoundException.class)
    public ResponseEntity<Messages> dataNotFoundExceptionHandker(MethodNotFoundException dataNotFoundException){

        String msg1 = dataNotFoundException.getMessage();
        Messages messages = Messages.builder().msg(msg1).build();

        return new ResponseEntity<>(messages,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> dataNotFoundExceptionHandker(MethodArgumentNotValidException methodArgumentNotValidException) {

        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<ObjectError> objectErrorList = bindingResult.getAllErrors();

        List<String> listDefaultMassage = objectErrorList.stream().map(objectError -> objectError.getDefaultMessage()).toList();

        return new ResponseEntity<>(listDefaultMassage, HttpStatus.NOT_FOUND);


    }

}
