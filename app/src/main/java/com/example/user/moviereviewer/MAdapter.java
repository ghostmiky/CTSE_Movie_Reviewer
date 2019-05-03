package com.example.user.moviereviewer;

/**
 * Created by User on 5/3/2019.
 */
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MAdapter extends BaseAdapter {

    private static final String Base_Image_URL = "https://image.tmdb.org/t/p/w185";
    private Context context;
    ArrayList<Movie> mList;

    public MAdapter(Context context,ArrayList<Movie> movie_list){
        this.context = context;
        this.mList = movie_list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Movie getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = null;
        Movie movies = getItem(i);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(200, 300));

        if(view == null){
            ImageView imageView1 = new ImageView(context);
            imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView1.setAdjustViewBounds(true);
            relativeLayout.addView(imageView1);
        }else{
            imageView = (ImageView) view;
        }

        Picasso.with(context).load(Base_Image_URL + movies.getPosterPath())
                .placeholder(R.drawable.image_placeholder)
                .into(imageView);

        return imageView;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
