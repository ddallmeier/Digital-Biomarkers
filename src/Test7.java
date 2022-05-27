package com.example.dallm.tfm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class Test7 extends AppCompatActivity {


    @Override
    protected void onStop() {
        super.onStop();
        System.exit(0); //kill the app

    }

    @Override
    public void onBackPressed() { //here I capture the event onBackPress
        super.onBackPressed();
        onStop(); //call onStop
    }


    public Handler handler = new Handler();
    public int frequency = 400;
    public int z = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test0);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();


        final Button circle1 = findViewById(R.id.circle1);
        int testlength = 2400;

        final Button background = findViewById(R.id.background);
        final float[] lastTouchDownXY = new float[2];

        final ArrayList<Long> timestamp = new ArrayList<>();
        final ArrayList<Float> coordX = new ArrayList<>();
        final ArrayList<Float> coordY = new ArrayList<>();
        final ArrayList<String> event = new ArrayList<>();

        final ArrayList<Integer> mixed = new ArrayList<>();

        mixed.add(1);
        mixed.add(2);
        mixed.add(3);
        mixed.add(4);
        mixed.add(5);
        mixed.add(6);
        mixed.add(7);
        mixed.add(8);
        mixed.add(9);
        Collections.shuffle(mixed);

        final AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(true);

        final AlertDialog alertDialog = new AlertDialog.Builder(Test7.this, android.R.style.Theme_Holo_NoActionBar_Fullscreen).create();
        alertDialog.setMessage("Test is now finished. Great job!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(Test7.this, MainActivity.class));
                    }
                });

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.ssrclick);
        mp.setAudioStreamType(AudioManager.MODE_IN_COMMUNICATION);
        mp.start();
        timestamp.add(System.currentTimeMillis());
        event.add("S01");
        coordX.add((float) 0);
        coordY.add((float) 0);

        //button flash animation
        final Animation anim = new AlphaAnimation(1, 0);
        anim.setDuration(20); //You can manage the blinking time with this parameter
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(1);


        circle1.setVisibility(View.VISIBLE);
        event.add("AC1");
        timestamp.add(System.currentTimeMillis());
        coordX.add((float) 487.5);
        coordY.add((float) 674.5);


        //Loop that alternates the buttons visibility at a determined frequency X
        handler.post(
                new Runnable() {
                    @Override
                    public void run() {

                        final String red;

                        background.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                // save the X,Y coordinates
                                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                                    lastTouchDownXY[0] = event.getX();
                                    lastTouchDownXY[1] = event.getY();
                                }
                                return false;
                            }
                        });


                        background.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(final View view) {
                                // retrieve the stored coordinates
                                float x = lastTouchDownXY[0];
                                float y = lastTouchDownXY[1];
                                coordX.add(x);
                                coordY.add(y);

                                event.add("TO");
                                timestamp.add(System.currentTimeMillis());

                            }
                        });

                        circle1.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                // save the X,Y coordinates
                                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                                    lastTouchDownXY[0] = event.getX();
                                    lastTouchDownXY[1] = event.getY();
                                }
                                return false;
                            }
                        });


                        if (circle1.getVisibility() == View.VISIBLE) {
                            circle1.setVisibility(View.INVISIBLE);
                            timestamp.add(System.currentTimeMillis());
                            z++;


                        } else {

                            Collections.shuffle(mixed);
                            circle1.setVisibility(View.VISIBLE);

                            if (mixed.get(1) == 9) {
                                circle1.setBackgroundColor(Color.parseColor("#FF2B19"));
                                red = "yes";
                                timestamp.add(System.currentTimeMillis());
                                event.add("ARC1");
                                coordX.add((float) 487.5);
                                coordY.add((float) 674.5);

                            } else {
                                circle1.setBackgroundColor(Color.parseColor("#000000"));
                                red = "no";
                                timestamp.add(System.currentTimeMillis());
                                event.add("AC1");
                                coordX.add((float) 487.5);
                                coordY.add((float) 674.5);
                            }


                            //Onclick events: timestamp, animation, sound
                            circle1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    timestamp.add(System.currentTimeMillis());
                                    if (red == "yes") {
                                        event.add("TRC1");
                                    } else {
                                        event.add("TC1");
                                    }

                                    float x = lastTouchDownXY[0] + circle1.getLeft();
                                    float y = lastTouchDownXY[1] + circle1.getTop();
                                    coordX.add(x);
                                    coordY.add(y);


                                }
                            });

                        }


                        handler.postDelayed(this, frequency);
                    }
                });


        //Timer that brings you back to the main test page
        new Timer().schedule(new TimerTask() {
            public void run() {
                Test7.this.runOnUiThread(new Runnable() {
                    public void run() {

                        mp.start();
                        timestamp.add(System.currentTimeMillis());
                        event.add("S01");
                        coordX.add((float) 0);
                        coordY.add((float) 0);

                        //Save file with converted timestamp
                        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                        cal.setTimeInMillis(System.currentTimeMillis());
                        String date = DateFormat.format("dd-MM-yyyy kk-mm-ss", cal).toString();

                        //file name and create writer
                        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
                        String fileName = "Test7" + "_" + date + ".csv";
                        String filePath = baseDir + File.separator + fileName;
                        File f = new File(filePath);

                        //CSV file creater and writer
                        if (f.exists() && !f.isDirectory()) {
                            PrintWriter csvWriter = null;
                            try {
                                csvWriter = new PrintWriter(filePath);

                                csvWriter.println("Timestamp,Event,X,Y");

                                for (int j = 0; j < timestamp.size(); j++) {
                                    csvWriter.print(timestamp.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(event.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(coordX.get(j));
                                    csvWriter.print(",");
                                    csvWriter.println(coordY.get(j));

                                }
                                csvWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else {
                            try {
                                PrintWriter csvWriter = null;

                                csvWriter = new PrintWriter(filePath);

                                csvWriter.println("Timestamp,Event,X,Y");

                                for (int j = 0; j < timestamp.size(); j++) {
                                    csvWriter.print(timestamp.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(event.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(coordX.get(j));
                                    csvWriter.print(",");
                                    csvWriter.println(coordY.get(j));
                                }

                                csvWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        alertDialog.show();
                        alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextSize(TypedValue.COMPLEX_UNIT_SP, 40.0f);

                        TextView textView = alertDialog.findViewById(android.R.id.message);
                        textView.setTextColor(Color.CYAN);
                        textView.setTextSize(50);
                        textView.setGravity(Gravity.CENTER);

                        if (alertDialog.isShowing()) {
                        } else {
                            startActivity(new Intent(Test7.this, MainActivity.class));
                        }
                    }
                });
            }
        }, testlength);


    }


}