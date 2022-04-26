package com.tutorials.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private TextView name;
    private TextView occupation;
    private TextView description;
    private TextView age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        name = findViewById(R.id.name);
        occupation = findViewById(R.id.occupation);
        description = findViewById(R.id.description);
        age = findViewById(R.id.age);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            if (bundle.containsKey(MainActivity.nameKey)) {
                name.setText(bundle.getString(MainActivity.nameKey));
            }
            if (bundle.containsKey(MainActivity.occupationKey)) {
                occupation.setText(bundle.getString(MainActivity.occupationKey));
            }
            if (bundle.containsKey(MainActivity.descriptionKey)) {
                description.setText(bundle.getString(MainActivity.descriptionKey));
            }
            if (bundle.containsKey(MainActivity.ageKey)) {
                age.setText("" + bundle.getInt(MainActivity.ageKey));
            }
        }
    }

    public void onBackClick(View view) {
        finish();
    }
}
