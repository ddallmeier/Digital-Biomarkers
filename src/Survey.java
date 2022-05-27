package com.example.dallm.tfm;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Locale;

public class Survey extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();


        final EditText nameT = findViewById(R.id.nameT);
        final EditText emailT = findViewById(R.id.emailT);
        final EditText ageT = findViewById(R.id.ageT);
        final EditText drugsT = findViewById(R.id.drugT);
        final EditText sleepT = findViewById(R.id.sleephours);

        final RatingBar feelstars = findViewById(R.id.feelstars);
        final RatingBar itstars = findViewById(R.id.itstars);
        final RatingBar gamerstars = findViewById(R.id.gamerstars);
        final RatingBar sleepstars = findViewById(R.id.sleepstars);
        final RatingBar sportsstars = findViewById(R.id.sportstars);
        final RatingBar musicstars = findViewById(R.id.musicstars);

        final CheckBox handleft = findViewById(R.id.handleft);
        final String lefthand = "L";
        final CheckBox handright = findViewById(R.id.handright);
        final String righthand = "R";
        final CheckBox male = findViewById(R.id.sexm);
        final String malesex = "M";
        final CheckBox female = findViewById(R.id.sexf);
        final String femalesex = "F";




        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Save file with converted timestamp
                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.setTimeInMillis(System.currentTimeMillis());
                String date = DateFormat.format("dd-MM-yyyy kk-mm-ss", cal).toString();

                //file name and create writer
                String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
                String fileName = "Survey" + "_" + date + ".csv";
                String filePath = baseDir + File.separator + fileName;
                File f = new File(filePath);

                if (TextUtils.isEmpty(emailT.getText())){
                    emailT.setText("N/A");
                }

                if (TextUtils.isEmpty(drugsT.getText())){
                    drugsT.setText("N/A");
                }

                if (TextUtils.isEmpty(nameT.getText())){
                    nameT.setText("N/A");
                }

                if (TextUtils.isEmpty(ageT.getText())){
                    ageT.setText("N/A");
                }

                if (TextUtils.isEmpty(sleepT.getText())){
                    sleepT.setText("N/A");
                }

                //CSV file creater and writer
                if (f.exists() && !f.isDirectory()) {
                    PrintWriter csvWriter = null;
                    try {
                        csvWriter = new PrintWriter(filePath);

                        csvWriter.println("Name,Email,Age,Sex,Feeling,IT,Gamer,Sleep,Sleep(h),Drugs,Sports,Musician,L/R");
                        csvWriter.print(nameT.getText());
                        csvWriter.print(",");
                        csvWriter.print(emailT.getText());
                        csvWriter.print(",");
                        csvWriter.print(ageT.getText());
                        csvWriter.print(",");

                        if (male.isChecked()) {
                            csvWriter.print(malesex);
                        } else {
                            csvWriter.print(femalesex);
                        }

                        csvWriter.print(",");
                        csvWriter.print(feelstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(itstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(gamerstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(sleepstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(sleepT.getText());
                        csvWriter.print(",");
                        csvWriter.print(drugsT.getText());
                        csvWriter.print(",");
                        csvWriter.print(sportsstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(musicstars.getNumStars());
                        csvWriter.print(",");

                        if (handleft.isChecked()) {
                            csvWriter.print(lefthand);
                        } else {
                            csvWriter.print(righthand);
                        }

                        csvWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        PrintWriter csvWriter = null;

                        csvWriter = new PrintWriter(filePath);

                        csvWriter.println("Name,Email,Age,Sex,Feeling,IT,Gamer,Sleep,Sleeph,Drugs,Sports,Musician,Hand");
                        csvWriter.print(nameT.getText());
                        csvWriter.print(",");
                        csvWriter.print(emailT.getText());
                        csvWriter.print(",");
                        csvWriter.print(ageT.getText());
                        csvWriter.print(",");

                        if (male.isChecked()) {
                            csvWriter.print(malesex);
                        } else {
                            csvWriter.print(femalesex);
                        }

                        csvWriter.print(",");
                        csvWriter.print(feelstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(itstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(gamerstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(sleepstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(sleepT.getText());
                        csvWriter.print(",");
                        csvWriter.print(drugsT.getText());
                        csvWriter.print(",");
                        csvWriter.print(sportsstars.getNumStars());
                        csvWriter.print(",");
                        csvWriter.print(musicstars.getNumStars());
                        csvWriter.print(",");

                        if (handleft.isChecked()) {
                            csvWriter.print(lefthand);
                        } else {
                            csvWriter.print(righthand);
                        }


                        csvWriter.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(Survey.this, MainActivity.class));
            }


        });


    }

}

