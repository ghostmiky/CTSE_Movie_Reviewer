package com.example.user.moviereviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gv = (GridView) findViewById(R.id.gv);
        String[] plants = new String[]{
                "Catalina ironwood",
                "Cabinet cherry",
                "Pale corydalis",
                "Pink corydalis",
                "Land cress",
                "Coast polypody",
                "Water fern"
        };

        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // Create a new ArrayAdapter
        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, plantsList);

        gv.setAdapter(gridViewArrayAdapter);
    }
}
