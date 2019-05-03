package com.example.user.moviereviewer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String MoviesByPopularity = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=7213d62b38ca01e8699e0479fd3a0f88";
    private static final String MoviesByRating = "https://api.themoviedb.org/3/discover/movie?certification_country=US&certification=R&sort_by=vote_average.desc&api_key=7213d62b38ca01e8699e0479fd3a0f88";
    ArrayList<Movie> popularList;
    ArrayList<Movie> topTopRatedList;


    @BindView(R.id.loadingBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.INVISIBLE);


    }

    public class getMovies extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            popularList = new ArrayList<Movie>();
            topTopRatedList = new ArrayList<Movie>();


            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}