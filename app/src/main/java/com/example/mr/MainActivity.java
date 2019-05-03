package com.example.mr;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import NetUtils.Nutils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private String APIKey = "7213d62b38ca01e8699e0479fd3a0f88";
    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopTopRatedList;


    @BindView(R.id.indeterminateBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.INVISIBLE); //Hide Progressbar by Default
        FetchMovies fm = new FetchMovies();

        int mCorePoolSize = 60;
        int mMaximumPoolSize = 80;
        int mKeepAliveTime = 10;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(mMaximumPoolSize);
        Executor mCustomThreadPoolExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, mKeepAliveTime, TimeUnit.SECONDS, workQueue);
        fm.executeOnExecutor();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//            fm.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
//        else
//            fm.execute((Void[])null);
//        Toast.makeText(MainActivity.this,"Hi",Toast.LENGTH_LONG).show();

    }

    public class FetchMovies extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            String popularMovies = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+APIKey;
            String topRatedMovies = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key="+APIKey;
            Toast.makeText(MainActivity.this,"yo",Toast.LENGTH_LONG).show();
            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();

            try {
                if(Nutils.networkStatus(MainActivity.this)){
                    mPopularList = Nutils.fetchData(popularMovies); //Get popular movies
                    mTopTopRatedList = Nutils.fetchData(topRatedMovies); //Get top rated movies
                    Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
