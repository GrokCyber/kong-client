package cn.edu.gdut.zaoying.kong;

import feign.Response;
import feign.Util;

import java.io.IOException;

public class KongException extends RuntimeException{
    private String methodKey;
    private Response response;
    private Integer code;
    private String message;

    public KongException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public KongException(String methodKey, Response response) {
        this.methodKey = methodKey;
        this.response = response;
        this.code = response.status();
        message = getMessage(methodKey, response);
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getLocalizedMessage() {
        return message;
    }

    public static String getMessage(String methodKey, Response response) {
        String message = String.format("status %s reading %s", response.status(), methodKey);
        try {
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader());
                message += "; content:\n" + body;
            }
        } catch (IOException e) {
            e.printStackTrace();
//            log.error("读取KongClientResponse失败", e);
        }
        return message;
    }

    public String getMethodKey() {
        return methodKey;
    }

    public Response getResponse() {
        return response;
    }
}
