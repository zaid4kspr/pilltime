package com.example.myapplication;

import com.example.myapplication.data.model.ResponseObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("users/register")
    Call<ResponseBody> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("sexe") Integer sexe,
            @Field("birthdate") String birthdate
            );


    @FormUrlEncoded
    @POST("users/login")
    Call<ResponseObject> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @GET("Temperature")
    Call<ResponseBody> geTemperature(
    );



}
