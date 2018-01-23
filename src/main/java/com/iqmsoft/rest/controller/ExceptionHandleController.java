package com.iqmsoft.rest.controller;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.iqmsoft.rest.util.RestaurantNotFoundException;
import com.iqmsoft.rest.util.UserNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandleController {

   
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException e) {
        return new HashMap<String, String>(){ { put("message", e.getLocalizedMessage()); } };
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Error> resourceNotFound(ResourceNotFoundException e) {
    	Error er = new Error(100, "Restaurant Not Found");
    	return new ResponseEntity<Error>(er, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(RestaurantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Error> restaurantNotFound(RestaurantNotFoundException e) {
	    Error er = new Error(100, "Resource Not Found");
    	return new ResponseEntity<Error>(er, HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Error> UserNotFound(UserNotFoundException e) {
    	Error er = new Error(100, "Resource Not Found");
      	return new ResponseEntity<Error>(er, HttpStatus.NOT_FOUND);
    }

    
    
    
    
    class Error {

      
        private final int code;
        private final String message;

        public Error(int code, String message) {
          
            this.code = code;
            this.message = message;
        }

        

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}