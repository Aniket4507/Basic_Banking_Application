package com.prathamesh.basicbanking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button view_users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_users = findViewById(R.id.view_users);

        view_users.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,user_list.class));
            finish();
        });
    }
}