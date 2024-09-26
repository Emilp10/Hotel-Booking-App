package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test.Home2;
import com.example.test.R;

public class Fares2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fares2);

        Button backButton = findViewById(R.id.bckbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate back to the home screen
                Intent intent = new Intent(Fares2.this, Home2.class);
                startActivity(intent);
                finish(); // Optional: Finish the current activity to remove it from the back stack
            }
   });
}
}