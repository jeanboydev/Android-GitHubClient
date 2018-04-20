package com.jeanboy.app.github.net.api;

import com.jeanboy.app.github.data.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebApi {

    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);
}
