package com.example.cikodev1.partyvit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private RadioButton rb_option1, rb_option2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();

        rb_option1 = (RadioButton)findViewById(R.id.rb_opt1);
        rb_option2 = (RadioButton)findViewById(R.id.rb_opt2);

    }
    public void Siguiente (View view){
        if (rb_option1.isChecked() || rb_option2.isChecked()){
            int result = 0;
            int res = getIntent().getIntExtra("dato", 0);
            if (rb_option1.isChecked()){
                result = 1;
            }else if (rb_option2.isChecked()){
                result = -1;
            }
            result = res + result;
            Intent i = new Intent(this, Main4Activity.class);
            i.putExtra("dato2", result);
            startActivity(i);
        } else {
            Toast.makeText(this,"Debes marcar una respuesta",Toast.LENGTH_SHORT).show();
        }
    }
}
