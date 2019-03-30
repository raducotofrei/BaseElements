package ch.rcotofrei.baseelements;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper<T> {

    private String baseUrl;

    private Class<T> serviceClass;

    private boolean enableLogging;

    private T service;

    public RetrofitHelper(String baseUrl, Class<T> serviceClass, boolean enableLogging) {
        this.baseUrl = baseUrl;
        this.serviceClass = serviceClass;
        this.enableLogging = enableLogging;
    }

    private void initialize() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder();
        if (enableLogging) {
            builder.client(client);
        }
        builder = builder.baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        service = builder.build().create(serviceClass);
    }

    public T getService() {
        if (service == null) {
            initialize();
        }
        return service;
    }
}
