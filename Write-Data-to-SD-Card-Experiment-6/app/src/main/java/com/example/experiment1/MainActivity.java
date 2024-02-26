package com.example.experiment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button);
        final Button showButton = findViewById(R.id.button2);
        final Button clearButton = findViewById(R.id.button3);
        final EditText textInp   = findViewById(R.id.editText);


        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                store data

                try{
                    File f=new File("/sdcard/myfile.txt"); f.createNewFile();
                    FileOutputStream fout=new FileOutputStream(f);
                    fout.write(textInp.getText().toString().getBytes()); fout.close();
                    ShowToast(v,"Data Written in SDCARD");

                }
                catch (Exception e)
                {
                    ShowToast(v,e.getMessage());

                }

            }
        });
        showButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                read data
                String message;
                String buf = "";
                try {
                    File f = new File("/sdcard/myfile.txt");
                    FileInputStream fin = new FileInputStream(f);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                    while ((message = br.readLine()) != null) {
                        buf += message;
                    }
                    textInp.setText(buf);
                    br.close();
                    fin.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getBaseContext(), "Data Recived from SDCARD", Toast.LENGTH_LONG).show();


            }
            });

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                clear data
                textInp.setText("");
                ShowToast(v,"Cleared Input");
            }
        });

    }

    public void ShowToast(View v,String msg){

        Toast.makeText(v.getContext(), msg,
                Toast.LENGTH_SHORT).show();

    }
}