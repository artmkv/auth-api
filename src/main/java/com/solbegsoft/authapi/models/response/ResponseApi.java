package com.solbegsoft.authapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response Message
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi<T> {

    /**
     *  data
     */
    private T data;
}