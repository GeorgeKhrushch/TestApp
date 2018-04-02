package com.test.khrushch.testapplication.new_client.exception;

public class ResponseException extends Exception {

    public ResponseException(Throwable cause) {
        super(cause);
    }

    public ResponseException(String emptyResponse) {
        super(emptyResponse);
    }
}
