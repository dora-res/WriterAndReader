package com.example.class1112;

import static android.provider.Telephony.Mms.Part.FILENAME;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button readBTN, writeBTN;
    TextView readInfo;
    EditText writeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readBTN = findViewById(R.id.read);
        writeBTN = findViewById(R.id.write);
        readInfo = findViewById(R.id.readinfo);
        writeInfo = findViewById(R.id.info);

        Resources res = getResources();

        writeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                writeFile();
            }
        });

        readBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });
    }

    @SuppressLint("RestrictedApi")
    private void readFile() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput ("myfile.txt")));
            String str = "";
            //str = br.readLine();
            while ((str = br.readLine()) != null) {
               readInfo.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile() {
        try {

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput ("myfile.txt",
                            MODE_PRIVATE)));
            bw.write(writeInfo.getText().toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}