package android.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IdentifyCarImage2 extends AppCompatActivity {
    private boolean isTimerSwitchOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_image2);
        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");
    }

    public void launchIdentifyCarImage(View view) {
        Intent intent = new Intent(getApplicationContext(),IdentifyCarImage.class);
        intent.putExtra("switchValue", isTimerSwitchOn);
        startActivity(intent);
    }
}