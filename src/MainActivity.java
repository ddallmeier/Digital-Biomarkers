package com.example.dallm.tfm;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

//        final Button submitButton = findViewById(R.id.ok);
//        final EditText name = findViewById(R.id.name);
//
//
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nameId = new String(String.valueOf(name.getText()));
//                Toast.makeText(getApplicationContext(), nameId + "was recorded", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        Button Survey = findViewById(R.id.Survey);
        Survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Survey.class));
            }
        });


        Button test0 = findViewById(R.id.test0);
        test0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test0.class));
            }
        });


        Button test1 = findViewById(R.id.test1);
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test1.class));

            }
        });


        Button test2 = findViewById(R.id.test2);
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test2.class));
            }
        });

        Button test3 = findViewById(R.id.test3);
        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test3.class));
            }
        });

        Button test4 = findViewById(R.id.test4);
        test4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test4.class));
            }
        });


        Button test5 = findViewById(R.id.test5);
        test5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test5.class));
            }
        });


        Button test6 = findViewById(R.id.test6);
        test6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test6.class));
            }
        });

        Button test1_5 = findViewById(R.id.test1_5);
        test1_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test1_5.class));
            }
        });


        Button test7 = findViewById(R.id.test7);
        test7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test7.class));
            }
        });

        Button test8 = findViewById(R.id.test8);
        test8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test8.class));
            }
        });


    }
}
