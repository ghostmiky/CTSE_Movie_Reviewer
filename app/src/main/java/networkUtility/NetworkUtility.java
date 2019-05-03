package networkUtility;

import android.util.Log;

import com.example.user.moviereviewer.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import com.example.user.moviereviewer.Movie;

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
    }

    public static void parseJ(String data, ArrayList<Movie> list){
        try{
            JSONObject mjObject = new JSONObject();
            JSONArray jsonArray = mjObject.getJSONArray("results");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Movie mov = new Movie();
                
            }



        }catch(Exception e){
            e.printStackTrace();
            Log.e(TAG,"Error in JSON PARSING");
        }
    }
}
