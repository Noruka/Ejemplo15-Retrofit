package es.guillemburnleesviada.ejemplo15_retrofit.retrofit_connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {
    private static String url = "https://reqres.in";

    public static Retrofit getConnection(){

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
