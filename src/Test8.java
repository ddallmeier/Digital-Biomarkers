package com.example.dallm.tfm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class Test8 extends AppCompatActivity {

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
    public int frq2 = 400;
    public int frq3 = 333;
    public int z = 0;
    public boolean b = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test0);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();


        final Button circle1 = findViewById(R.id.circle1);
        int testlength = 1970000;

        final Button background = findViewById(R.id.background);
        final float[] lastTouchDownXY = new float[2];

        final ArrayList<Long> timestamp = new ArrayList<>();
        final ArrayList<Float> coordX = new ArrayList<>();
        final ArrayList<Float> coordY = new ArrayList<>();
        final ArrayList<String> event = new ArrayList<>();
        final ArrayList<Integer> fq = new ArrayList<>();

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
        mixed.add(10);
        Collections.shuffle(mixed);

        final ArrayList<Integer> mixedFq = new ArrayList<>();

        mixedFq.add(frq1);
        mixedFq.add(frq2);
        mixedFq.add(frq3);
        Collections.shuffle(mixedFq);
        frequency = mixedFq.get(0);


        final AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(true);

        final AlertDialog alertDialog = new AlertDialog.Builder(Test8.this, android.R.style.Theme_Holo_NoActionBar_Fullscreen).create();
        alertDialog.setMessage("Test is now finished. Great job!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(Test8.this, MainActivity.class));
                    }
                });

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.ssrclick);
        mp.setAudioStreamType(AudioManager.MODE_IN_COMMUNICATION);
        mp.start();
        timestamp.add(System.currentTimeMillis());
        event.add("S01");
        coordX.add((float) 0);
        coordY.add((float) 0);
        fq.add(frequency);

        circle1.setVisibility(View.VISIBLE);
        event.add("AC1");
        timestamp.add(System.currentTimeMillis());
        coordX.add((float) 487.5);
        coordY.add((float) 674.5);
        fq.add(frequency);


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
                                fq.add(frequency);

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
                            z++;


                        } else {


                            Collections.shuffle(mixed);
                            circle1.setVisibility(View.VISIBLE);

                            if (mixed.get(1) == 9) {
                                circle1.setBackgroundColor(Color.parseColor("#FF2B19"));
                                b = true;
                                timestamp.add(System.currentTimeMillis());
                                event.add("ARC1");
                                coordX.add((float) 487.5);
                                coordY.add((float) 674.5);
                                fq.add(frequency);

                            } else {
                                circle1.setBackgroundColor(Color.parseColor("#000000"));
                                b = false;
                                timestamp.add(System.currentTimeMillis());
                                event.add("AC1");
                                coordX.add((float) 487.5);
                                coordY.add((float) 674.5);
                                fq.add(frequency);
                            }


                        }

                        //Onclick events: timestamp, animation, sound
                        circle1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                circle1.startAnimation(anim);
//                                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 50);
                                timestamp.add(System.currentTimeMillis());

                                if (b == true) {
                                    event.add("TRC1");
                                } else {
                                    event.add("TC1");
                                }
                                float x = lastTouchDownXY[0] + circle1.getLeft();
                                float y = lastTouchDownXY[1] + circle1.getTop();
                                coordX.add(x);
                                coordY.add(y);
                                fq.add(frequency);


                            }
                        });


                        if (z == 800) {
                            frequency = mixedFq.get(1);
                        } else if (z == 1600) {
                            frequency = mixedFq.get(2);
                        }

                        handler.postDelayed(this, frequency);
                    }
                });


        //Timer that brings you back to the main test page
        new Timer().schedule(new TimerTask() {
            public void run() {
                Test8.this.runOnUiThread(new Runnable() {
                    public void run() {

                        mp.start();
                        timestamp.add(System.currentTimeMillis());
                        event.add("S01");
                        coordX.add((float) 0);
                        coordY.add((float) 0);
                        fq.add(frequency);

                        //Save file with converted timestamp
                        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                        cal.setTimeInMillis(System.currentTimeMillis());
                        String date = DateFormat.format("dd-MM-yyyy kk-mm-ss", cal).toString();

                        //file name and create writer
                        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                        String fileName = "Test8" + "_" + date + ".csv";
                        String filePath = baseDir + File.separator + fileName;
                        File f = new File(filePath);

                        //CSV file creater and writer
                        if (f.exists() && !f.isDirectory()) {
                            PrintWriter csvWriter = null;
                            try {
                                csvWriter = new PrintWriter(filePath);

                                csvWriter.println("Timestamp,Event,X,Y,Frequency");

                                for (int j = 0; j < timestamp.size(); j++) {
                                    csvWriter.print(timestamp.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(event.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(coordX.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(coordY.get(j));
                                    csvWriter.print(",");
                                    csvWriter.println(fq.get(j));


                                }
                                csvWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else {
                            try {
                                PrintWriter csvWriter = null;

                                csvWriter = new PrintWriter(filePath);

                                csvWriter.println("Timestamp,Event,X,Y,Frequency");

                                for (int j = 0; j < timestamp.size(); j++) {
                                    csvWriter.print(timestamp.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(event.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(coordX.get(j));
                                    csvWriter.print(",");
                                    csvWriter.print(coordY.get(j));
                                    csvWriter.print(",");
                                    csvWriter.println(fq.get(j));
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

                        if (!alertDialog.isShowing()) {
                            startActivity(new Intent(Test8.this, MainActivity.class));
                        }
                    }
                });
            }
        }, testlength);


    }


}