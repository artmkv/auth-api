package com.solbegsoft.authapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response Message
 */
@Data
@AllArgsConstructor // TODO: 30.07.2022 если кидаешь алларг кидай и ноу арг
public class ResponseApi<T> {

    /**
     *  data
     */
    private T data;
}