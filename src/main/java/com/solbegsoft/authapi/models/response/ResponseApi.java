package com.solbegsoft.authapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response Message
 */
@Data
@AllArgsConstructor
public class ResponseApi<T> {

    /**
     *  data
     */
    private T data;
}