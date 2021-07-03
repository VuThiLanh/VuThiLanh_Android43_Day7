package com.example.vuthilanh_android43_day7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.vuthilanh_android43_day7.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMain{
    ActivityMainBinding binding;
    MainPresent mainPresent;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainPresent = new MainPresent(this);

        List<String> types = new ArrayList<String>();
        types.add("Work");
        types.add("Friend");
        types.add("Family");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spType.setAdapter(adapter);

        binding.tvTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = new String[]{"Family","Game","Android","VTC","Friend"};
                boolean[] booleans = {false,false,false,false,false};
                android.app.AlertDialog alertDialog = new AlertDialog.Builder(binding.tvTags.getContext())
                        .setTitle("Choose tags:")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mainPresent.displaySelect(strings,booleans,binding.tvTags);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        binding.tvWeeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = new String[]{"Monday","Tuesday","Wednesday","Thurday","Friday","Saturday","Sunday"};
                boolean[] booleans = {false,false,false,false,false,false,false};
                android.app.AlertDialog alertDialog = new AlertDialog.Builder(binding.tvWeeks.getContext())
                        .setTitle("Choose tags:")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               mainPresent.displaySelect(strings,booleans,binding.tvWeeks);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        binding.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                        calendar.set(0,0,0,hourOfDay,minute);
                        binding.tvTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },14,20,true);
                timePickerDialog.show();
            }
        });

        binding.tvCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        binding.tvCalender.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },2016,02,04);
                datePickerDialog.show();
            }
        });

        binding.tvTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvFromFile.setVisibility(View.VISIBLE);
                binding.tvFromDefault.setVisibility(View.VISIBLE);
                binding.tvFromFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.tvTune.setText(binding.tvFromFile.getText());
                        binding.tvFromFile.setVisibility(View.INVISIBLE);
                        binding.tvFromDefault.setVisibility(View.INVISIBLE);
                    }
                });
                binding.tvFromDefault.setOnClickListener(new View.OnClickListener() {
                    String[] listItems;
                    @Override
                    public void onClick(View v) {
                        listItems = new String[] {"Nexus Tune","Winphone tune","Peep tune","Nokia Tune","Etc"};
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder((MainActivity.this));
                        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 s = listItems[which];
                            }
                        })
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                binding.tvTune.setText(s);
                                binding.tvFromFile.setVisibility(View.INVISIBLE);
                                binding.tvFromDefault.setVisibility(View.INVISIBLE);
                            }
                        })
                                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                binding.tvFromFile.setVisibility(View.INVISIBLE);
                                binding.tvFromDefault.setVisibility(View.INVISIBLE);
                            }
                        });
                        AlertDialog mDialog = mBuilder.create();
                        mDialog.show();
                    }
                });
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menucancel,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnCancel:
            {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
            break;
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public void thongBao(String mess) {
        Toast.makeText(getBaseContext(),mess,Toast.LENGTH_LONG).show();
    }
}