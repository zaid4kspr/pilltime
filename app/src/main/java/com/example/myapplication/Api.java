package com.example.myapplication;

import com.example.myapplication.data.model.PriseModel;
import com.example.myapplication.data.model.ResponseObject;
import com.example.myapplication.data.model.TemperatureModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface Api {
    @FormUrlEncoded
    @POST("users/register")
    Call<ResponseBody> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("sexe") Integer sexe,
            @Field("birthYear") String birthYear
    );


    @FormUrlEncoded
    @POST("users/login")
    Call<ResponseObject> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("temperature")
    Call<ResponseBody> addTemperature(
            @Body TemperatureModel t
            );

    @GET("temperature")
    Call<ArrayList<TemperatureModel>> getAllTemperature();

    @GET("prise")
    Call<ArrayList<PriseModel>> getPrise(
            @Query("query") String query,
            @Query("populate") String populate


    );




}
