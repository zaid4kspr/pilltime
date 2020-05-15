package com.example.myapplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
private static  final String BASE_URL="https://pill-time.herokuapp.com/api/";
private static  RetrofitClient mInstance;
private Retrofit retrofit;

private RetrofitClient(){
//    retrofit= new Retrofit.Builder().baseUrl().addConverterFactory(GsonConverterFactory.create()).build();
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
    httpClient.addInterceptor(logging);  // <-- this is the important line!

     retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();
}


public static synchronized  RetrofitClient getInstance(){
    if(mInstance==null){
        mInstance=new RetrofitClient();
    }
    return  mInstance;
}

public Api getApi(){
    return retrofit.create(Api.class);
}


}
