package com.example.cikodev1.partyvit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActivityAskTwo extends AppCompatActivity {
    public static final String EXTRA_DATA = "Data";

    private RadioButton option1RadioButton,option2RadioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_two);
        getSupportActionBar().hide();

        option1RadioButton = (RadioButton)findViewById(R.id.option_one_radio_button);
        option2RadioButton = (RadioButton)findViewById(R.id.option_two_radio_button);

    }
    public void Next (View view){
        if (option1RadioButton.isChecked() || option2RadioButton.isChecked()){
            int option = 0;

            int result = getIntent().getIntExtra( ActivityAskOne.EXTRA_DATA, 0);
            if (option1RadioButton.isChecked()){
                option = 1;
            }else if (option2RadioButton.isChecked()){
                option = -1;
            }
            option = result + option;
            Intent i = new Intent(this, ActivityAskThree.class);
            i.putExtra( EXTRA_DATA, option);
            startActivity(i);
        } else {
            Toast.makeText(this,getResources().getString(R.string.message),Toast.LENGTH_SHORT).show();
        }
    }
}
