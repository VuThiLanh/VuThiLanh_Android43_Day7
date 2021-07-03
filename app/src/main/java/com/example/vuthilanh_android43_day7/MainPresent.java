package com.example.vuthilanh_android43_day7;

import android.app.TimePickerDialog;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainPresent {
    IMain iMain;
    public MainPresent(IMain iMain){
        this.iMain=iMain;
    }
    public void displaySelect(String[] strings, boolean[] booleans, TextView textview ){
        List<String> a = new ArrayList<>();
        for (int i=0;i<booleans.length;i++){
            boolean checked = booleans[i];
            if(checked){
                a.add(strings[i]);
            }
        }
        textview.setText(a.toString().replaceAll("]","").replace("[",""));
    }

}
