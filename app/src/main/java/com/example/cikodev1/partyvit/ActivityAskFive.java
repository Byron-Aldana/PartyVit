package com.example.cikodev1.partyvit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActivityAskFive extends AppCompatActivity {

    private RadioButton rb_option1, rb_option2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_five);
        getSupportActionBar().hide();

        rb_option1 = (RadioButton)findViewById(R.id.rb_option1);
        rb_option2 = (RadioButton)findViewById(R.id.rb_option2);
    }
    public void Result (View view){
        if (rb_option1.isChecked() || rb_option2.isChecked()){
            int option = 0;
            int result = getIntent().getIntExtra("data4", 0);
            if (rb_option1.isChecked()){
                option = 1;
            }else if (rb_option2.isChecked()){
                option = -1;
            }
            option = result + option;
            if (option >2){
                Intent i = new Intent(this, ActivityBoringResult.class);
                startActivity(i);
            }else{
                Intent i = new Intent(this, ActivityPartyResult.class);
                startActivity(i);

            }
        } else {
            Toast.makeText(this,"Debes marcar una respuesta",Toast.LENGTH_SHORT).show();
        }
    }
}
