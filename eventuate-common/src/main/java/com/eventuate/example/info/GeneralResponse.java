package com.eventuate.example.info;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponse<T> {
    private static final long serialVersionUID = 1L;
    private ResponseStatus status;
    private T data;
}
