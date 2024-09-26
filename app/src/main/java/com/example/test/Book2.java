package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Model.BookForm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

public class Book2 extends AppCompatActivity {

    private EditText nameEditText, surnameEditText, contactEditText, emailEditText;
    private RadioGroup ticketTypeRadioGroup;
    private Button submitButton, backButton;
    private FirebaseAuth mAuth;

    // Firebase
    private DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book2);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        nameEditText = findViewById(R.id.editText);
        mAuth = FirebaseAuth.getInstance();
        surnameEditText = findViewById(R.id.editText2);
        contactEditText = findViewById(R.id.editText3);
        emailEditText = findViewById(R.id.editText4);
        ticketTypeRadioGroup = findViewById(R.id.rg_TicketType);
        submitButton = findViewById(R.id.CheckoutBtn);
        backButton = findViewById(R.id.bckbutton);
        userId = mAuth.getCurrentUser().getUid();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void submitForm() {
        String name = nameEditText.getText().toString().trim();
        String surname = surnameEditText.getText().toString().trim();
        String contact = contactEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String ticketType = getSelectedTicketType().toString();


        if (name.isEmpty() || surname.isEmpty() || contact.isEmpty() || email.isEmpty() || ticketType.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        } else {
            BookForm bookForm = new BookForm(name, surname, contact, email, ticketType);

            databaseReference.child("users").child(userId).child("bookings").child(databaseReference.push().getKey()).setValue(bookForm).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(Book2.this, "Booking Successful!", Toast.LENGTH_SHORT).show();
                    clearForm();
                } else {
                    Toast.makeText(Book2.this, "Booking Failed!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private String getSelectedTicketType() {
        int selectedId = ticketTypeRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        return selectedRadioButton.getText().toString();
    }


    private void clearForm() {
        nameEditText.setText("");
        surnameEditText.setText("");
        contactEditText.setText("");
        emailEditText.setText("");
        ticketTypeRadioGroup.clearCheck();
    }

}


