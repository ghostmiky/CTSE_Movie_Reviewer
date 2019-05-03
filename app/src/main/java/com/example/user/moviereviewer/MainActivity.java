package com.example.user.moviereviewer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import networkUtility.NetworkUtility;

import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String MoviesByPopularity = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=7213d62b38ca01e8699e0479fd3a0f88";
    private static final String MoviesByRating = "https://api.themoviedb.org/3/discover/movie?certification_country=US&certification=R&sort_by=vote_average.desc&api_key=7213d62b38ca01e8699e0479fd3a0f88";
    ArrayList<Movie> popularList;
    ArrayList<Movie> topTopRatedList;

    @BindView(R.id.movie_list)
    GridView mGridView;

    @BindView(R.id.loadingBar)
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        progressBar.setVisibility(View.INVISIBLE);
        new getMovies().execute();
//        Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG).show();
//        GridView gv = (GridView) findViewById(R.id.gv);
//        String[] plants = new String[]{
//                "Catalina ironwood",
//                "Cabinet cherry",
//                "Pale corydalis",
//                "Pink corydalis",
//                "Land cress",
//                "Coast polypody",
//                "Water fern"
//        };
//
//        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));
//
//        // Create a new ArrayAdapter
//        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
//                (this,android.R.layout.simple_list_item_1, plantsList);
//
//        gv.setAdapter(gridViewArrayAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int position, long l) {
                Movie movie = (Movie) view.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("detail", movie);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pop_movies) {
            refreshList(popularList);
        }
        if (id == R.id.top_movies) {
            refreshList(topTopRatedList);
        }
        return super.onOptionsItemSelected(item);
    }

    public class getMovies extends AsyncTask<Void, Void, Void> {

        private final String TAG = NetworkUtility.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            popularList = new ArrayList<Movie>();
            topTopRatedList = new ArrayList<Movie>();
            Toast.makeText(MainActivity.this,"getMovies",Toast.LENGTH_LONG).show();
            try{
                if(NetworkUtility.isConnected(MainActivity.this)){
                    //get the popular movies using the api and query
                    popularList = NetworkUtility.getData(MoviesByPopularity);
                    //get the top rayed movies using the api and query
                    topTopRatedList = NetworkUtility.getData(MoviesByRating);
                }
                else{
                    Toast.makeText(MainActivity.this,"No Internet Connectivity",Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                e.printStackTrace();
                Log.e(TAG,"Error in doInBackground method");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
        }




    }

    private void refreshList(ArrayList<Movie> list) {
        MAdapter adapter = new MAdapter(MainActivity.this, list);
        mGridView.invalidateViews();
        mGridView.setAdapter(adapter);
    }



}