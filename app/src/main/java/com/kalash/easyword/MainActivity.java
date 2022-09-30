package com.kalash.easyword;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.kalash.easyword.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
ActivityMainBinding binding;

private boolean blackmode=false;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        requestQueue= Volley.newRequestQueue(this);
        binding.ModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked)
                {
                    blackmode=true;
                    darkMode mode=new darkMode(binding);
                    mode.Active();
                    Log.d("check", "onCheckedChanged: ");
                }
                else
                {
                    blackmode=false;
                    darkMode mode=new darkMode(binding);
                    mode.DeActive();
                    Log.d("check", "Not checked ");
                }
            }
        });
        EditText inputText=binding.inputWord;
        inputText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if(keyEvent.getAction() == keyEvent.ACTION_DOWN && i== KeyEvent.KEYCODE_ENTER && !inputText.getText().toString().equals("")){
                    String word=inputText.getText().toString();
                    fetchData data=new fetchData(word,requestQueue);
                data.getData(MainActivity.this, binding.defList, binding.similarWord);
                binding.word.setText("Word:" + inputText.getText().toString().toUpperCase());
//                    try{
//                        Thread.sleep(1500);
//
//                        // Then do something meaningful...
//                    }catch(InterruptedException e){
//                        e.printStackTrace();
//                    }
                }
                return false;
            }
        });


    }


}