package com.example.v5t71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    protected EditText tiedostonNimi, kirjoitettuTektsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiedostonNimi = (EditText) findViewById(R.id.tiedostonNimi);
        kirjoitettuTektsi = (EditText) findViewById(R.id.teksti);
    }

    public void kirjoitus (View v) {
        String nimi = tiedostonNimi.getText().toString();
        String teksti = kirjoitettuTektsi.getText().toString();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(nimi, MODE_PRIVATE);
            fileOutputStream.write(teksti.getBytes());
            kirjoitettuTektsi.getText().clear();
            System.out.println("Tiedosto kirjoitettu");
            tiedostonNimi.setText("");
        } catch (IOException e) {
            Log.e("IOExpection", "Virhe syötteessä");
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.e("IOExpection", "Virhe tiedoston sulkemisessa");
                }
            }
        }
    }

    public void luku (View v) {
        FileInputStream fileInputStream = null;
        String nimi = tiedostonNimi.getText().toString();
        try {
            fileInputStream = openFileInput(nimi);
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            //
            kirjoitettuTektsi.setText(sb.toString());
            tiedostonNimi.setText("");
            System.out.println("Tiedosto luettu");
        } catch (IOException e) {
            Log.e("IOExpection", "Virhe syötteessä");
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    Log.e("IOExpection", "Virhe tiedoston sulkemisessa");
                }
            }
        }
    }


}