package example.packt.com.implicitapp.client.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        boolean httpError = (response.code() >= 400);
        if (httpError) {
            throw new HttpException();
        }

        return response;
    }

    public static class HttpException extends RuntimeException {
    }
}
