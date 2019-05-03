package com.example.user.moviereviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fragments.BackdropF;

/**
 * Created by User on 5/3/2019.
 */

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();

    public static final String BASE_URL ="https://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        Movie mov_intent = (Movie)intent.getSerializableExtra("detail");

        BackdropF backdropFragment = (BackdropF) getFragmentManager().findFragmentById(R.id.fragment_backdrop);

        backdropFragment.set_backdrop(BASE_URL);
}
}


