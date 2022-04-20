package eu.tutorials.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        name = findViewById(R.id.name);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            if (bundle.containsKey(MainActivity.usernameKey)) {
                name.setText("Welcome " + bundle.getString(MainActivity.usernameKey));
            }
        }
    }

    public void onBackClick(View view) {
        finish();
    }
}
