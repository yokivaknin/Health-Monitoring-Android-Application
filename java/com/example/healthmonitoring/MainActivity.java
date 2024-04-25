package com.example.healthmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner daySpinner = findViewById(R.id.daySpinner);
        Spinner monthSpinner = findViewById(R.id.monthSpinner);
        Spinner yearSpinner = findViewById(R.id.yearSpinner);

        // Create arrays for days, months, and years
        Integer[] daysArray = new Integer[31];
        for (int i = 0; i < 31; i++) {
            daysArray[i] = i + 1;
        }

        Integer[] monthsArray = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        };

        Integer[] yearsArray = new Integer[]{
                2023,2024,2025,2026,2027,2028,2029,2030
        };


        // Create adapters and set data for spinners
        ArrayAdapter<Integer> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, daysArray);
        ArrayAdapter<Integer> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monthsArray);
        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearsArray);

        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daySpinner.setAdapter(dayAdapter);
        monthSpinner.setAdapter(monthAdapter);
        yearSpinner.setAdapter(yearAdapter);
    }

    public void insertClick(View v){

        Spinner day = findViewById(R.id.daySpinner);
        Spinner month = findViewById(R.id.monthSpinner);
        Spinner year = findViewById(R.id.yearSpinner);

        Intent i = new Intent(this, DataCollection.class);
        i.putExtra("day", (int)day.getSelectedItem());
        i.putExtra("month", (int)month.getSelectedItem());
        i.putExtra("year", (int)year.getSelectedItem());
        startActivity(i);
    }

    public void deleteClick(View v) {

        Spinner daySpinner = findViewById(R.id.daySpinner);
        Spinner monthSpinner = findViewById(R.id.monthSpinner);
        Spinner yearSpinner = findViewById(R.id.yearSpinner);

        // Get the selected date from the spinners
        int day = (int) daySpinner.getSelectedItem();
        int month = (int) monthSpinner.getSelectedItem();
        int year = (int) yearSpinner.getSelectedItem();

        // Create an intent to open the DataCollection activity
        Intent i = new Intent(this, DataCollection.class);
        i.putExtra("day", day);
        i.putExtra("month", month);
        i.putExtra("year", year);
        i.putExtra("delete", true); // Indicate that it's a delete operation
        startActivity(i);
    }

}
