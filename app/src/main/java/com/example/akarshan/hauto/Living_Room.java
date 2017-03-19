package com.example.akarshan.hauto;

import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

public class Living_Room extends AppCompatActivity {

    int LIGHT_1=0, LIGHT_2=0, LIGHT_3=0, FAN_1=0,FAN_2=0, TV=0, DOOR=0, WIFI=0;
    ImageButton light1,light2,light3,fan1,fan2,tv,door,wifi;
    SeekBar light_1,light_2,light_3,fan_1,fan_2;
    String ip = MainActivity.ip();
    int port = MainActivity.port();
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living__room);
        light1 = (ImageButton) findViewById(R.id.imageButton6);
        light2 = (ImageButton) findViewById(R.id.imageButton7);
        light3 = (ImageButton) findViewById(R.id.imageButton8);
        fan1 = (ImageButton) findViewById(R.id.imageButton9);
        fan2 = (ImageButton) findViewById(R.id.imageButton10);
        tv = (ImageButton) findViewById(R.id.imageButton11);
        wifi = (ImageButton) findViewById(R.id.imageButton12);
        door = (ImageButton) findViewById(R.id.imageButton13);
        light_1 = (SeekBar) findViewById(R.id.seekBar4);
        light_2 = (SeekBar) findViewById(R.id.seekBar5);
        light_3 = (SeekBar) findViewById(R.id.seekBar6);
        fan_1 = (SeekBar) findViewById(R.id.seekBar8);
        fan_2 = (SeekBar) findViewById(R.id.seekBar9);
        tcp("Living_Room");
        final JSONObject obj = new JSONObject();
        light1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (LIGHT_1!=0)
                {
                    LIGHT_1=0;
                    light1.setImageResource(R.drawable.off_bulb);
                }
                else
                {
                    LIGHT_1=100;
                    light1.setImageResource(R.drawable.on_bulb);
                }
                light_1.setProgress(LIGHT_1);
                try{
                    obj.put("LIGHT_1",LIGHT_1);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                tcp(obj.toString());
                return false;
            }
        });
        light2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (LIGHT_2!=0)
                {
                    LIGHT_2=0;
                    light2.setImageResource(R.drawable.off_bulb);
                }
                else
                {
                    LIGHT_2=100;
                    light2.setImageResource(R.drawable.on_bulb);
                }
                light_2.setProgress(LIGHT_2);
                try{
                    obj.put("LIGHT_2",LIGHT_2);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                tcp(obj.toString());
                return false;
            }
        });
        light3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (LIGHT_3!=0)
                {
                    LIGHT_3=0;
                    light3.setImageResource(R.drawable.off_bulb);
                }
                else
                {
                    LIGHT_3=100;
                    light3.setImageResource(R.drawable.on_bulb);
                }
                light_3.setProgress(LIGHT_3);
                try{
                    obj.put("LIGHT_3",LIGHT_3);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                tcp(obj.toString());
                return false;

            }
        });
        fan1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (FAN_1!=0)
                {
                    FAN_1=0;
                    fan1.setBackgroundColor(Color.GRAY);
                }
                else
                {
                    FAN_1=100;
                    fan1.setBackgroundColor(Color.GREEN);
                }
                fan_1.setProgress(FAN_1);
                try{
                    obj.put("FAN_1",FAN_1);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                tcp(obj.toString());
                return false;
            }
        });
        fan2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (FAN_2 != 0) {
                    FAN_2 = 0;
                    fan2.setBackgroundColor(Color.GRAY);
                } else {
                    FAN_2 = 100;
                    fan2.setBackgroundColor(Color.GREEN);
                }
                fan_2.setProgress(FAN_2);
                try {
                    obj.put("FAN_2", FAN_2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tcp(obj.toString());
                return false;
            }
        });
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (TV != 0) {
                    TV = 0;
                    tv.setBackgroundColor(Color.GRAY);
                } else {
                    TV = 100;
                    tv.setBackgroundColor(Color.GREEN);
                }
                try {
                    obj.put("TV", TV);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tcp(obj.toString());
                return false;
            }

        });
        door.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (DOOR != 0) {
                    DOOR = 0;
                    door.setImageResource(R.drawable.closed);
                } else {
                   DOOR = 100;
                    door.setImageResource(R.drawable.open);
                }
                try {
                    obj.put("DOOR", DOOR);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tcp(obj.toString());
                return false;
            }
        });
        wifi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (WIFI != 0) {
                    WIFI = 0;
                    wifi.setBackgroundColor(Color.GRAY);
                } else {
                    WIFI = 100;
                    wifi.setBackgroundColor(Color.GREEN);
                }
                try {
                    obj.put("WIFI", WIFI);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tcp(obj.toString());
                return false;
            }
        });
        light_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LIGHT_1=progress;
                if (LIGHT_1==0)
                {
                    light1.setImageResource(R.drawable.off_bulb);
                }
                else
                {
                    light1.setImageResource(R.drawable.on_bulb);
                }
                try{
                    obj.put("LIGHT_1",LIGHT_1);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                tcp(obj.toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        light_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LIGHT_2=progress;
                if (LIGHT_2==0)
                {
                    light2.setImageResource(R.drawable.off_bulb);
                }
                else
                {
                    light2.setImageResource(R.drawable.on_bulb);
                }
                try{
                    obj.put("LIGHT_2",LIGHT_2);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                tcp(obj.toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        light_3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LIGHT_3=progress;
                if (LIGHT_3==0)
                {
                    light3.setImageResource(R.drawable.off_bulb);
                }
                else
                {
                    light3.setImageResource(R.drawable.on_bulb);
                }
                try{
                    obj.put("LIGHT_3",LIGHT_3);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                tcp(obj.toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        fan_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                FAN_1=progress;
                if (FAN_1==0)
                {
                    fan1.setBackgroundColor(Color.GRAY);
                }
                else
                {
                    fan1.setBackgroundColor(Color.GREEN);
                }
                try{
                    obj.put("FAN_1",FAN_1);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                tcp(obj.toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        fan_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                FAN_2=progress;
                if (FAN_2 == 0) {
                    fan2.setBackgroundColor(Color.LTGRAY);
                } else {
                    fan2.setBackgroundColor(Color.GREEN);
                }
                try{
                    obj.put("FAN_2",FAN_2);
                }catch(JSONException e)
                {
                    e.printStackTrace();
                }
                tcp(obj.toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void set_status(String sta)
    {
        try{
            JSONObject json = new JSONObject(sta);
            LIGHT_1 = json.getInt("LIGHT_1");
            LIGHT_2 = json.getInt("LIGHT_2");
            LIGHT_3 = json.getInt("LIGHT_3");
            FAN_1 = json.getInt("FAN_1");
            FAN_2 = json.getInt("FAN_2");
            TV = json.getInt("TV");
            DOOR = json.getInt("DOOR");
            WIFI = json.getInt("WIFI");
            light_1.setProgress(LIGHT_1);
            if(LIGHT_1>0) { light1.setImageResource(R.drawable.on_bulb);}
            light_2.setProgress(LIGHT_2);
            if(LIGHT_2>0) { light2.setImageResource(R.drawable.on_bulb);}
            light_3.setProgress(LIGHT_3);
            if(LIGHT_3>0) { light3.setImageResource(R.drawable.on_bulb);}
            fan_1.setProgress(FAN_1);
            if(FAN_1>0) {   fan1.setBackgroundColor(Color.GREEN);}
            fan_2.setProgress(FAN_2);
            if(FAN_2>0) {   fan2.setBackgroundColor(Color.GREEN);}
            if(TV==100) {   tv.setBackgroundColor(Color.GREEN);}
            if(DOOR==100) { door.setImageResource(R.drawable.open);}
            if(WIFI>0)  {   wifi.setBackgroundColor(Color.GREEN);}

            }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }

    public Boolean tcp(String str){
        try{

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Socket client = new Socket(ip, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter printwriter = new PrintWriter(client.getOutputStream(), true);
            printwriter.write(str);
            printwriter.flush();
            String line;
            StringBuilder sb = new StringBuilder();
            if(flag==0)
            {
                while ((line = in.readLine ()) != null) {
                    System.out.print(line);
                    if(line!=null)
                    {
                        sb.append(line);
                    }

                }
                flag=1;

                set_status(sb.toString());
            }

        } catch (UnknownHostException e2) {
            Log.d("socket", "unknownhost");
            return false;
        } catch (IOException e1) {
            Log.d("socket", "IOException");
            e1.printStackTrace();
            return false;
        }
        return true;
    }
}

