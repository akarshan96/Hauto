package com.example.akarshan.hauto;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Boolean connection = false;
    Button connect;
    EditText server,port;
    static String ipadres;
    static int portnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        connect=(Button)findViewById(R.id.button);
        server=(EditText)findViewById(R.id.editText2);
        port=(EditText)findViewById(R.id.editText3);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connect.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                connect();
                return false;
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


        public void connect() {
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    ipadres=server.getText().toString();
                    portnumber=Integer.parseInt(port.getText().toString());
                    Socket client = new Socket(ipadres, portnumber);
                    PrintWriter printwriter = new PrintWriter(client.getOutputStream(), true);
                    printwriter.write("HELLO SERVER");
                    printwriter.flush();
                    printwriter.close();
                    connection = true;
                    Log.d("socket", "connected " + connection);
                    if(connection==true)
                    {
                        Intent intent = new Intent(MainActivity.this,Living_Room.class);
                        startActivity(intent);
                        finish();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                                Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                            }
                        });

                }
                catch (UnknownHostException e2){
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Unknown host please make sure IP address", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                catch (IOException e1) {
                    Log.d("socket", "IOException");
                    e1.printStackTrace();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Error Occured"+ "  " , Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        }).start();
    }
    public static String ip()
    {
        return ipadres;
    }
    public static int port()
    {
        return portnumber;
    }
    }

