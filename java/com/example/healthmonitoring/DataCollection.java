package com.example.healthmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DataCollection extends AppCompatActivity {

    private List<DayInfo> DayInfoList;
    private EditText answer1;
    private CheckBox checkbox2_1, checkbox2_2, checkbox2_3, checkbox2_4, checkbox2_5;
    private Spinner spinner3, spinner5;
    private EditText detail3;
    private CheckBox checkbox4_1, checkbox4_2, checkbox4_3, checkbox4_4;
    private SeekBar slider5;
    private CheckBox checkbox6_1, checkbox6_2;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_colection);

        answer1 = findViewById(R.id.answer1);
        checkbox2_1 = findViewById(R.id.checkbox2_1);
        checkbox2_2 = findViewById(R.id.checkbox2_2);
        checkbox2_3 = findViewById(R.id.checkbox2_3);
        checkbox2_4 = findViewById(R.id.checkbox2_4);
        checkbox2_5 = findViewById(R.id.checkbox2_5);
        spinner3 = findViewById(R.id.spinner3);
        detail3 = findViewById(R.id.detail3);
        checkbox4_1 = findViewById(R.id.checkbox4_1);
        checkbox4_2 = findViewById(R.id.checkbox4_2);
        checkbox4_3 = findViewById(R.id.checkbox4_3);
        checkbox4_4 = findViewById(R.id.checkbox4_4);
        spinner5 = findViewById(R.id.spinner5);
        checkbox6_1 = findViewById(R.id.checkbox6_1);
        checkbox6_2 = findViewById(R.id.checkbox6_2);
        finishButton = findViewById(R.id.finishButton);

        boolean isDeleteOperation = getIntent().getBooleanExtra("delete", false);
        if (isDeleteOperation) {
            deleteSelectedDayInfo();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }

    }

    public void finishClicked(View view){
        // Call a function to create the DayInfo object with the user's input
        DayInfo dayInfo = createDayInfoObject();
        DayInfoList.add(dayInfo);

        // Save the updated list to the file
        serializeData();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    // Function to create a new DayInfo object based on user input
    private DayInfo createDayInfoObject() {
        String whatDidIEatAndDrink = answer1.getText().toString();

        boolean[] medicinesTaken = new boolean[5];
        medicinesTaken[0] = checkbox2_1.isChecked();
        medicinesTaken[1] = checkbox2_2.isChecked();
        medicinesTaken[2] = checkbox2_3.isChecked();
        medicinesTaken[3] = checkbox2_4.isChecked();
        medicinesTaken[4] = checkbox2_5.isChecked();

        int feelingRating = Integer.parseInt(spinner3.getSelectedItem().toString());
        String feelingDetail = detail3.getText().toString();

        boolean[] sportsActivities = new boolean[4];
        sportsActivities[0] = checkbox4_1.isChecked();
        sportsActivities[1] = checkbox4_2.isChecked();
        sportsActivities[2] = checkbox4_3.isChecked();
        sportsActivities[3] = checkbox4_4.isChecked();

        int sleepRating = Integer.parseInt(spinner5.getSelectedItem().toString());

        boolean sleptWell = checkbox6_1.isChecked();

        // Create a Calendar instance to set the date
      //  Calendar calendar = Calendar.getInstance();
        int day = getIntent().getIntExtra("day", 0);
        int month = getIntent().getIntExtra("month", 0);
        int year = getIntent().getIntExtra("year", 0);
        //calendar.set(year, month, day);
        //Date date = calendar.getTime();
       // Date date = new Date(day,month,year);
        // Create and return the DayInfo object
        return new DayInfo(day,month,year, whatDidIEatAndDrink, medicinesTaken, feelingRating,
                feelingDetail, sportsActivities, sleepRating, sleptWell);
    }

    // Function to serialize the dayInfoList and save it to a file
    private void serializeData() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    openFileOutput("day_info_data.ser", Context.MODE_PRIVATE));
            outputStream.writeObject(DayInfoList);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to deserialize the data from the file and load it into the dayInfoList
    private void loadSavedData() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(
                    openFileInput("day_info_data.ser"));
            DayInfoList = (LinkedList<DayInfo>) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

            // If an error occurs, initialize the list as an empty list
            DayInfoList = new LinkedList<>();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        serializeData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSavedData();
    }

    // Function to handle the delete operation based on the selected date
    private void deleteSelectedDayInfo() {
        int day = getIntent().getIntExtra("day", 0);
        int month = getIntent().getIntExtra("month", 0);
        int year = getIntent().getIntExtra("year", 0);

        // Loop through the dayInfoList and remove the matching DayInfo object
        for (DayInfo dayInfo : DayInfoList) {

            int d = dayInfo.getDay();
            int m = dayInfo.getMonth();
            int y = dayInfo.getYear();

            if (day == d && month == m && year == y) {
                DayInfoList.remove(dayInfo);
                break;
            }
        }
    }

    public void exportData(View view) {
        exportToTextFile();
        Toast.makeText(this, "Data exported to text file", Toast.LENGTH_SHORT).show();
    }

    private void exportToTextFile() {

        String content = "";

        // Loop through the dayInfoList
        for (DayInfo dayInfo : DayInfoList) {
            content += "Date: " + dayInfo.getDate() + "\n";
            content += "What did I eat and drink: " + dayInfo.getWhatDidIEatAndDrink() + "\n";
            content += "Medicines taken: " + dayInfo.getMedicinesTaken() + "\n";
            content += "Feeling rating: " + dayInfo.getFeelingRating() + "\n";
            content += "Feeling detail: " + dayInfo.getFeelingDetail() + "\n";
            content +="Sports activities: " + dayInfo.getSportsActivities() + "\n";
            content += "Sleep rating: " + dayInfo.getSleepRating() + "\n";
            content += "Slept well: " + dayInfo.isSleptWell() + "\n\n";
        }

        // Save the content to a text file
        try {
            File file = new File(getExternalFilesDir(null), "health_data.txt");
            FileWriter writer = new FileWriter(file);
            writer.append(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
