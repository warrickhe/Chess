package com.example.chess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TitleScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);
        View TitleScreen = findViewById(R.id.title_screen);
        View root = TitleScreen.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        Button Start = (Button) findViewById(R.id.start_game);
        Start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent i = new Intent(TitleScreen.this , MainActivity.class);
                TitleScreen.this.startActivity(i);
            }
        });
    }
}
