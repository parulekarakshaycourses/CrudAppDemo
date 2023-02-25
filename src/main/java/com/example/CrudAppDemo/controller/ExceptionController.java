package com.example.CrudAppDemo.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

@ControllerAdvice
public class ExceptionController
{
    @ExceptionHandler(Exception.class)
    public String pageNotFound()
    {
        return "pageNotFound";
    }
}
