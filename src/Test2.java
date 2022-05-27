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


public class Test2 extends AppCompatActivity {

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
    public int frequency;
    public int frq1 = 500;
    public int frq2 = 444;
    public int frq3 = 400;
    public int frq4 = 363;
    public int frq5 = 333;
    public int z = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();


        final Button circle1 = findViewById(R.id.circle1);
        final Button circle2 = findViewById(R.id.circle2);
        int testlength = 320700;

        final Button background = findViewById(R.id.background);
        final float[] lastTouchDownXY = new float[2];

        final ArrayList<Long> timestamp = new ArrayList<>();
        final ArrayList<Float> coordX = new ArrayList<>();
        final ArrayList<Float> coordY = new ArrayList<>();
        final ArrayList<String> event = new ArrayList<>();

        final ArrayList<Integer> mixed = new ArrayList<>();

        mixed.add(frq1);
        mixed.add(frq2);
        mixed.add(frq3);
        mixed.add(frq4);
        mixed.add(frq5);
        Collections.shuffle(mixed);
        frequency = mixed.get(0);

        final AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(true);

        final AlertDialog alertDialog = new AlertDialog.Builder(Test2.this, android.R.style.Theme_Holo_NoActionBar_Fullscreen).create();
        alertDialog.setMessage("Test is now finished. Great job!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(Test2.this, MainActivity.class));
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
        circle2.setVisibility(View.INVISIBLE);
        event.add("AC1");
        timestamp.add(System.currentTimeMillis());
        coordX.add((float) 487.5);
        coordY.add((float) 674.5);


        //Loop that alternates the buttons visibility at a determined frequency X
        handler.post(
                new Runnable() {
                    @Override
                    public void run() {

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


                        circle2.setOnTouchListener(new View.OnTouchListener() {
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
                            circle2.setVisibility(View.VISIBLE);
                            timestamp.add(System.currentTimeMillis());
                            event.add("AC2");
                            coordX.add((float) 1554.5);
                            coordY.add((float) 674.5);
                            z++;

                            //Onclick events: timestamp, animation, sound
                            circle2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    circle2.startAnimation(anim);
//                                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 50);
                                    timestamp.add(System.currentTimeMillis());
                                    event.add("TC2");
                                    float x = lastTouchDownXY[0] + circle2.getLeft();
                                    float y = lastTouchDownXY[1] + circle2.getTop();
                                    coordX.add(x);
                                    coordY.add(y);


                                }
                            });


                        } else {
                            circle1.setVisibility(View.VISIBLE);
                            circle2.setVisibility(View.INVISIBLE);
                            timestamp.add(System.currentTimeMillis());
                            event.add("AC1");
                            coordX.add((float) 487.5);
                            coordY.add((float) 674.5);
                            z++;


                            //Onclick events: timestamp, animation, sound
                            circle1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    circle1.startAnimation(anim);
//                                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 50);
                                    timestamp.add(System.currentTimeMillis());
                                    event.add("TC1");
                                    float x = lastTouchDownXY[0] + circle1.getLeft();
                                    float y = lastTouchDownXY[1] + circle1.getTop();
                                    coordX.add(x);
                                    coordY.add(y);


                                }
                            });

                        }


                        if (z == 100) {
                            frequency = mixed.get(1);
                        } else if (z == 200) {
                            frequency = mixed.get(2);
                        } else if (z == 300) {
                            frequency = mixed.get(3);
                        } else if (z == 400) {
                            frequency = mixed.get(4);
                        }

                        handler.postDelayed(this, frequency);
                    }
                });


        //Timer that brings you back to the main test page
        new Timer().schedule(new TimerTask() {
            public void run() {
                Test2.this.runOnUiThread(new Runnable() {
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
                        String fileName = "Test2" + "_" + date + ".csv";
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

                            startActivity(new Intent(Test2.this, MainActivity.class));
                        }
                    }
                });
            }
        }, testlength);


    }


}