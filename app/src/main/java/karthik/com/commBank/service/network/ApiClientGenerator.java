package karthik.com.commBank.service.network;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientGenerator {

    private String BASE_URL = "https://www.dropbox.com/";

    public <T> T createService(OkHttpClient okHttpClient, Gson gson, Class<T> clientClass) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit.create(clientClass);
    }
}
