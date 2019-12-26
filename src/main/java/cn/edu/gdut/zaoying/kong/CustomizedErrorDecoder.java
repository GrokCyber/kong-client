package cn.edu.gdut.zaoying.kong;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomizedErrorDecoder extends ErrorDecoder.Default {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new KongException(methodKey, response);
    }
}
