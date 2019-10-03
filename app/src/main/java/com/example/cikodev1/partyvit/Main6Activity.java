package com.example.cikodev1.partyvit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {

    private RadioButton rb_option1, rb_option2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        getSupportActionBar().hide();

        rb_option1 = (RadioButton)findViewById(R.id.rb_option1);
        rb_option2 = (RadioButton)findViewById(R.id.rb_option2);
    }
    public void Siguiente (View view){
        if (rb_option1.isChecked() || rb_option2.isChecked()){
            int result = 0;
            int res = getIntent().getIntExtra("dato4", 0);
            if (rb_option1.isChecked()){
                result = 1;
            }else if (rb_option2.isChecked()){
                result = -1;
            }
            result = res + result;
            if (result >2){
                Intent i = new Intent(this, Main7Activity.class);
                startActivity(i);
            }else{
                Intent i = new Intent(this, Main8Activity.class);
                startActivity(i);

            }
        } else {
            Toast.makeText(this,"Debes marcar una respuesta",Toast.LENGTH_SHORT).show();
        }
    }
}
