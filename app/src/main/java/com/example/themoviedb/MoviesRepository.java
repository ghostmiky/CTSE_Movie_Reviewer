package com.example.themoviedb;

import android.support.annotation.NonNull;

import com.example.themoviedb.MovieResponse;
import com.example.themoviedb.OnGetMoviesCallback;
import com.example.themoviedb.TMDbApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en-US";

    private static MoviesRepository repository;

    private TMDbApi api;

    private MoviesRepository(TMDbApi api) {
        this.api = api;
    }

    public static MoviesRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new MoviesRepository(retrofit.create(TMDbApi.class));
        }

        return repository;
    }

    public void getMovies(final OnGetMoviesCallback callback) {
        api.getPopularMovies("7213d62b38ca01e8699e0479fd3a0f88", LANGUAGE, 1)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            MovieResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                callback.onSuccess(moviesResponse.getMovies());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getGenres(final OnGetGenresCallback callback) {
        api.getGenres("7213d62b38ca01e8699e0479fd3a0f88", LANGUAGE)
                .enqueue(new Callback<GenreRes>() {
                    @Override
                    public void onResponse(Call<GenreRes> call, Response<GenreRes> response) {
                        if (response.isSuccessful()) {
                            GenreRes genresResponse = response.body();
                            if (genresResponse != null && genresResponse.getGenres() != null) {
                                callback.onSuccess(genresResponse.getGenres());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenreRes> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}