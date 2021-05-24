package android.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdvancedLevel2 extends AppCompatActivity {
    private boolean isTimerSwitchOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level2);
        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");
    }

    public void launchIdentifyCarAdvanced(View view) {
        Intent intent = new Intent(getApplicationContext(),AdvancedLevel.class);
        intent.putExtra("switchValue", isTimerSwitchOn);
        startActivity(intent);
    }
}