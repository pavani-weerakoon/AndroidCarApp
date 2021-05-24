package android.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IdentifyCarsAnswer extends AppCompatActivity {
    private boolean isTimerSwitchOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_cars_answer);
        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");
    }



    public void launchIdentifyCarMake(View view) {
        Intent intent = new Intent(getApplicationContext(),IdentifyCarMake.class);
        intent.putExtra("switchValue", isTimerSwitchOn);
        startActivity(intent);
    }
}