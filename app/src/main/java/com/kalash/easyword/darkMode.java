package com.kalash.easyword;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Switch;

import androidx.annotation.BinderThread;
import androidx.annotation.ColorInt;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.kalash.easyword.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class darkMode {
    ActivityMainBinding binding;
    ConstraintLayout layout;
    darkMode(ActivityMainBinding binding)
    {
        this.binding=binding;

    }
    protected void Active()  //Switch on
    {
//        binding.background.setBackgroundColor(Color.BLACK);  //background black
        binding.background.setBackgroundColor(Color.rgb(0,10,50));
        binding.appName.setTextColor(Color.WHITE);

    binding.inputWord.setBackgroundResource(R.drawable.plaintextboxdark);
    binding.word.setTextColor(Color.WHITE);
    binding.similar.setTextColor(Color.WHITE);

    binding.ModeSwitch.setTextColor(Color.WHITE);
    binding.ModeSwitch.setText("Default Mode");
//    binding.definition.setTextColor(Color.WHITE);

    }
    protected void DeActive()  //Switch Off
    {

          binding.background.setBackgroundColor(Color.WHITE);  //background white
        binding.appName.setTextColor(Color.BLACK);
        binding.inputWord.setBackgroundResource(R.drawable.plaintextbox);

        binding.word.setTextColor(Color.BLACK);
        binding.similar.setTextColor(Color.BLACK);
        binding.ModeSwitch.setText("Dark Mode");
        binding.ModeSwitch.setTextColor(Color.BLACK);

    }


}
