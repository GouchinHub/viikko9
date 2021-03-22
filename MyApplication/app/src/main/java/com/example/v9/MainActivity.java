package com.example.v9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Finnkino fk = Finnkino.getInstance();
    private String movies[] = {""};
    private Spinner spinner;
    private RecyclerView rView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //create spinner

        ArrayAdapter<FinnkinoTheater> myAdapter = new ArrayAdapter<FinnkinoTheater>(MainActivity.this,
                android.R.layout.simple_list_item_1, fk.getTheatersList());
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
        //create recyclerView
        rView = findViewById(R.id.recyclerView);
        movies = getResources().getStringArray(R.array.Movies);
    }

    public void getSelectedArea(View v) {
        fk.readScheduleXML(spinner , "22.03.2021");
        RecAdapter recAdapter = new RecAdapter(this, fk.getMovieslist());
        rView.setAdapter(recAdapter);
        rView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void refresh(View v){
        fk.readAreasXML();
        ArrayAdapter<FinnkinoTheater> myAdapter = new ArrayAdapter<FinnkinoTheater>(MainActivity.this,
                android.R.layout.simple_list_item_1, fk.getTheatersList());
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
    }

}