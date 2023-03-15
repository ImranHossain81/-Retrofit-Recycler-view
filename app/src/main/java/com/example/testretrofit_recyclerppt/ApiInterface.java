package com.example.testretrofit_recyclerppt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("v2/list")
    Call<List<ModelData>> getData();
}
