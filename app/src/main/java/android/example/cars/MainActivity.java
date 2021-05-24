package android.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Switch switchTimer;
    Button option1;
    Button option2;
    boolean isTimerSwitchOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchTimer = findViewById(R.id.switch1);


        //To change the boolean value when the switch button clicked
        switchTimer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isTimerSwitchOn = true;
                }else{
                    isTimerSwitchOn = false;
                }
            }
        });

    }


    public void CarMake(View view) {   //change to open IdentifyCarMake page
        Intent intent = new Intent(getApplicationContext(),IdentifyCarsAnswer.class);
        intent.putExtra("switchValue", isTimerSwitchOn);
        startActivity(intent);

    }

    public void HintClick(View view) {  //change to open Hints page
        Intent intent = new Intent(getApplicationContext(),CarHint2.class);
        intent.putExtra("switchValue", isTimerSwitchOn);
        startActivity(intent);
    }

    public void CarImageClick(View view) { // //change to open IdentifyCarImage page
        Intent intent = new Intent(getApplicationContext(),IdentifyCarImage2.class);
        intent.putExtra("switchValue", isTimerSwitchOn);
        startActivity(intent);
    }

    public void AdvancedClick(View view) { // //change to open AdvancedLevel page
        Intent intent = new Intent(getApplicationContext(),AdvancedLevel2.class);
        intent.putExtra("switchValue", isTimerSwitchOn);
        startActivity(intent);
    }
}