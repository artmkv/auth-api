package com.solbegsoft.authapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Response Message
 */
@Data
@AllArgsConstructor
@Builder
public class ResponseApi<T> {

    /**
     *  data
     */
    private T data;
}