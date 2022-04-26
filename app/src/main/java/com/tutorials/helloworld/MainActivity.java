package com.tutorials.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener {

    private EditText name;
    private EditText email;
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private EditText username;
    private TextView pickerView;
    private EditText occupation;
    private EditText description;
    public static final String datePicked = "selectedBirthday";
    public static final String ageKey = "age";
    public static final String nameKey = "name";
    public static final String occupationKey = "occupation";
    public static final String descriptionKey = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.names);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        occupation = findViewById(R.id.occupation);
        description = findViewById(R.id.description);
        pickerView = findViewById(R.id.dateOfBirth);
    }

    public void datePicker(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int value1, int value2, int value3) {
        ++value2;
        year = value1;
        month = value2;
        day = value3;
        pickerView.setText(getString(R.string.date, month, day, year));

    }

    public static class DatePickerFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), (MainActivity) getActivity(),
                    year, month, day);
        }
    }

    public void onSubmit(View view){
        String temporalName = name.getText().toString();
        String temporalEmail = email.getText().toString();
        String temporalUsername = username.getText().toString();
        String temporalOccupation = occupation.getText().toString();
        String temporalDescription = description.getText().toString();


        if(temporalEmail.equals("") || temporalUsername.equals("") || temporalName.equals("")
                || temporalOccupation.equals("") || temporalDescription.equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.inputError), Toast.LENGTH_LONG).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(temporalEmail).matches()){
            Toast.makeText(getApplicationContext(), getString(R.string.validEmail), Toast.LENGTH_LONG).show();
            return;
        }

        if(year == 0 || month == 0 || day == 0){
            Toast.makeText(getApplicationContext(), getString(R.string.noBirthdate), Toast.LENGTH_LONG).show();
            return;
        }

        LocalDate todayDate = LocalDate.now();
        LocalDate birthDate = LocalDate.of(year, month, day);
        int age = Period.between(birthDate, todayDate).getYears();

        if(age < 18){
            Toast.makeText(getApplicationContext(), getString(R.string.ageError), Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        intent.putExtra(nameKey, temporalName);
        intent.putExtra(descriptionKey, temporalDescription);
        intent.putExtra(occupationKey, temporalOccupation);
        intent.putExtra(ageKey, age);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(datePicked, pickerView.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(datePicked)) {
            pickerView.setText(savedInstanceState.getString(datePicked));
        }
    }

    @Override
    public void onRestart(){
        super.onRestart();
        name.setText("");
        email.setText("");
        year = 0;
        month = 0;
        day = 0;
        username.setText("");
        pickerView.setText("");
        occupation.setText("");
        description.setText("");
    }

}