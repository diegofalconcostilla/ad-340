package eu.tutorials.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.SyncStateContract;
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
    public static final String usernameKey = "username";
    public static final String datePicked = "selectedBirthday";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.names);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
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

        LocalDate todayDate = LocalDate.now();
        LocalDate birthDate = LocalDate.of(year, month, day);
        int age = Period.between(birthDate, todayDate).getYears();

        if(age < 18){
            Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
            return;
        }

        if(temporalEmail.equals("") || temporalUsername.equals("") || temporalName.equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        intent.putExtra(usernameKey, temporalUsername);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(datePicked, pickerView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(datePicked)) {
            pickerView.setText(savedInstanceState.getString(datePicked));
        }
    }

}