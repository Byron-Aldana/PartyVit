package com.example.cikodev1.partyvit;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ActivityUser extends AppCompatActivity {
    private EditText nameEditText, mailEditText;
    private int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();

        nameEditText = (EditText)findViewById(R.id.name_edit_text);
        mailEditText = (EditText)findViewById(R.id.mail_edit_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }
    public void Start (View view){
            if (nameEditText.getText().toString().length() == 0  && mailEditText.getText().toString().length() == 0){
                nameEditText.setError("Debes ingresar un nombre");
                mailEditText.setError("Debes ingresar un correo");
            }else {
                String name = nameEditText.getText().toString();
                String email = mailEditText.getText().toString();

                try {
                    String lineToWrite = new String("\n" + name + ", " + email);
                    String fileName = "users_info.txt";
                    String folderName = "UsersInfo";
                    File root = android.os.Environment.getExternalStorageDirectory();

                    File filePath = new File (root.getAbsolutePath() + File.separator + folderName);
                    filePath.mkdirs();

                    File file = new File(filePath + File.separator + fileName);
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(file,true));
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(lineToWrite);
                    bufferedWriter.close();
                } catch (FileNotFoundException e) {
                    Log.e("Error", "e: " + e);
                } catch (IOException e) {
                    Log.e("Error", "e: " + e);
                }
                Intent i = new Intent(this, ActivityAskOne.class);
                startActivity(i);
                nameEditText.setText("");
                mailEditText.setText("");
            }
    }
}