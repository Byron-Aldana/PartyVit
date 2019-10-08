package com.example.cikodev1.partyvit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActivityAskThree extends AppCompatActivity {

    private RadioButton option1RadioButton, option2RadioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_three);
        getSupportActionBar().hide();

        option1RadioButton = (RadioButton)findViewById(R.id.option_one_readio_button);
        option2RadioButton = (RadioButton)findViewById(R.id.option_two_readio_button);
    }
    public void Next (View view){
        if (option1RadioButton.isChecked() || option2RadioButton.isChecked()){
            int option = 0;
            int result = getIntent().getIntExtra("data2", 0);
            if (option1RadioButton.isChecked()){
                option = 1;
            }else if (option2RadioButton.isChecked()){
                option = -1;
            }
            option = result + option;
            Intent i = new Intent(this, ActivityAskFourth.class);
            i.putExtra("data3", option);
            startActivity(i);
        } else {
            Toast.makeText(this,"Debes marcar una respuesta",Toast.LENGTH_SHORT).show();

        }
    }
}
