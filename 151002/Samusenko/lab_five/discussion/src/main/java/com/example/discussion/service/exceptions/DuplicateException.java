package com.example.discussion.service.exceptions;

public class DuplicateException extends ResourceException {
    public DuplicateException(int code, String message)
    {
        super(code, message);
    }
}
