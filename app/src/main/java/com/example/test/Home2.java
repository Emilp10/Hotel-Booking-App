package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class Home2 extends AppCompatActivity implements View.OnClickListener {

    private CardView fareCard, bookingCard, feedbackCard, profilecard, logoutcard;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        mAuth = FirebaseAuth.getInstance();

        // defines Cards
        fareCard = (CardView) findViewById(R.id.fare_card);
        bookingCard = (CardView) findViewById(R.id.booking_card);
        feedbackCard = (CardView) findViewById(R.id.feedback_card);
        profilecard = (CardView) findViewById(R.id.profile_card);
        logoutcard = (CardView) findViewById(R.id.profileview_card);



        // Add Click listener to cards
        fareCard.setOnClickListener(this);
        bookingCard.setOnClickListener(this);
        feedbackCard.setOnClickListener(this);
        profilecard.setOnClickListener(this);
        logoutcard.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i;

        if (view.getId() == R.id.fare_card) {
            i = new Intent(this, Fares2.class);
            startActivity(i);
        } else if (view.getId() == R.id.booking_card) {
            i = new Intent(this, Book2.class);
            startActivity(i);
        } else if (view.getId() == R.id.feedback_card) {
            i = new Intent(this, Bookinghistory2.class);
            startActivity(i);
        } else if (view.getId() == R.id.profile_card) {
            i = new Intent(this, Aboutus2.class);
            startActivity(i);
        } else if (view.getId() == R.id.profileview_card) {
            mAuth.signOut();
            i = new Intent(this, MainActivity2.class);
            startActivity(i);
        }
    }
}