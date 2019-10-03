package com.example.cikodev1.partyvit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

public class MainActivity extends AppCompatActivity {
    private EditText txt_nombre, txt_correo;
    private String TAG = "Vit";
    private int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        txt_nombre = (EditText)findViewById(R.id.txt_nombre);
        txt_correo = (EditText)findViewById(R.id.txt_correo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");

            }

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
    public void comenzar (View view){
        String nombre = txt_nombre.getText().toString();
        String correo = txt_correo.getText().toString();

            if (txt_nombre.getText().toString().length() == 0  && txt_correo.getText().toString().length() == 0){
                txt_nombre.setError("Debes ingresar un nombre");
                txt_correo.setError("Debes ingresar un correo");
            }else {
                String name = txt_nombre.getText().toString();
                String email = txt_correo.getText().toString();

                try {
                    String lineToWrite = new String("\n" + name + ", " + email);
                    String fileName = "users_info.txt";
                    String folderName = "UsersInfo";
                    File root = android.os.Environment.getExternalStorageDirectory();
                    Log.w("BYRON", "Guardar: " + root);

                    // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

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
            }
            Intent i = new Intent(this, Main2Activity.class);
            startActivity(i);
                txt_nombre.setText("");
                txt_correo.setText("");
    }
}