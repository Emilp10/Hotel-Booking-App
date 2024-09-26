package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Model.BookForm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import android.widget.Button;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Bookinghistory2 extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;
    private TextView bookingHistoryTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinghistory2);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        bookingHistoryTextView = findViewById(R.id.bookingHistoryTextView);
        backButton = findViewById(R.id.bckButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the Home2 activity
                Intent intent = new Intent(Bookinghistory2.this, Home2.class);
                startActivity(intent);
            }
        });

        if (currentUser != null) {
            displayBookingHistory();
        } else {
            Toast.makeText(this, "User not logged in.", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayBookingHistory() {
        String userId = currentUser.getUid();
        Query query = databaseReference.child("users").child(userId).child("bookings");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> bookingList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BookForm booking = snapshot.getValue(BookForm.class);
                    if (booking != null) {
                        String bookingInfo = "Name: " + booking.name + "\n" +
                                "Surname: " + booking.surname + "\n" +
                                "Contact: " + booking.contact + "\n" +
                                "Email: " + booking.email + "\n" +
                                "Room Type: " + booking.ticketType + "\n\n";
                        bookingList.add(bookingInfo);
                    }
                }
                if (!bookingList.isEmpty()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String bookingInfo : bookingList) {
                        stringBuilder.append(bookingInfo);
                    }
                    bookingHistoryTextView.setText(stringBuilder.toString());
                } else {
                    bookingHistoryTextView.setText("No booking history found.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Bookinghistory2.this, "Failed to retrieve booking history.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
