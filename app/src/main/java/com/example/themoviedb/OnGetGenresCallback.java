package com.example.themoviedb;

import java.util.List;

public interface OnGetGenresCallback {

    void onSuccess(List<Genres> genres);

    void onError();
}