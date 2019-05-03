package networkUtility;

import android.util.Log;

import com.example.user.moviereviewer.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.example.user.moviereviewer.Movie;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by User on 5/3/2019.
 */

public class NetworkUtility {
    private static final String TAG = NetworkUtility.class.getSimpleName();

    Movie movies;
    public static ArrayList<Movie> getData(String url) throws IOException{
        ArrayList<Movie> movies = new ArrayList<>();

        try{
            URL movies_url = new URL(url);
            HttpURLConnection httpURLConnection =  (HttpURLConnection)movies_url.openConnection();

            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            String results = IOUtils.toString(inputStream);
            parseJ(results,movies);
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"Error in Fetching Data");
        }

        return movies;
    }

    public static void parseJ(String data, ArrayList<Movie> list){
        try{
            JSONObject mjObject = new JSONObject();
            JSONArray jsonArray = mjObject.getJSONArray("results");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Movie movie = new Movie();
                movie.setId(jsonObject.getInt("id"));
                movie.setVoteAverage(jsonObject.getInt("vote_average"));
                movie.setVoteCount(jsonObject.getInt("vote_count"));
                movie.setOriginalTitle(jsonObject.getString("original_title"));
                movie.setTitle(jsonObject.getString("title"));
                movie.setPopularity(jsonObject.getDouble("popularity"));
                movie.setBackdropPath(jsonObject.getString("backdrop_path"));
                movie.setOverview(jsonObject.getString("overview"));
                movie.setReleaseDate(jsonObject.getString("release_date"));
                movie.setPosterPath(jsonObject.getString("poster_path"));
                //Adding a new movie object into ArrayList
                list.add(movie);

            }



        }catch(Exception e){
            e.printStackTrace();
            Log.e(TAG,"Error in JSON PARSING");
        }
    }
}
