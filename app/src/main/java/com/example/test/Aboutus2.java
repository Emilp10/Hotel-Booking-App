package com.example.test;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Aboutus2 extends AppCompatActivity {
    Button knowMoreButton; // Declared at class level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus2);

        Button backButton = findViewById(R.id.bckbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the MainActivity (home screen)
                Intent intent = new Intent(Aboutus2.this, Home2.class);
                startActivity(intent);
                // Finish the current activity (Aboutus2) to prevent it from being added to the back stack
                finish();
            }
        });

        // Remove the redundant declaration of knowMoreButton here
        knowMoreButton = findViewById(R.id.knowmorebutton); // Initialize the class-level variable
        knowMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Taj Hotels website when the button is clicked
                Uri webpage = Uri.parse("http://www.tajhotels.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
    }
}
