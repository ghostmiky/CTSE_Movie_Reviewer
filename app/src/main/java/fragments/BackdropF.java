package fragments;

/**
 * Created by User on 5/3/2019.
 */
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.moviereviewer.R;
import com.squareup.picasso.Picasso;

public class BackdropF extends Fragment {
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.backdrop, container, false);
        return view;
    }

    public void set_backdrop(String url) {
        ImageView view = (ImageView) getView().findViewById(R.id.movie_backdrop);
        Picasso.with(context)
                .load(url)
                .into(view);
    }

}
