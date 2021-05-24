package android.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IdentifyCarMake extends AppCompatActivity {
    List<String> Cars;
    ImageView imageView,imageViewHome;
    Button btn;
    Random random;
    Spinner spinner;
    int loadedImage;
    TextView textViewAns,textViewCorrect;
    TextView timer1;
    CountDownTimer countTimer;
    boolean   isTimerSwitchOn=false;


    Integer[] imageArray = {
            R.drawable.audi1, R.drawable.benz1, R.drawable.bmw1, R.drawable.lamborghini1, R.drawable.nissan1,
            R.drawable.axio, R.drawable.vagannar, R.drawable.allion, R.drawable.rangerover, R.drawable.vitz,
            R.drawable.vessel, R.drawable.yaris, R.drawable.rush, R.drawable.corolla, R.drawable.chr,
            R.drawable.civic, R.drawable.crz, R.drawable.ferrari, R.drawable.bugatti, R.drawable.pagani,
            R.drawable.agera, R.drawable.jesko, R.drawable.zenvo, R.drawable.hennessy, R.drawable.peel,
            R.drawable.porsche, R.drawable.mclaren, R.drawable.gemera, R.drawable.camaro, R.drawable.ford,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_make);
        imageView = findViewById(R.id.imageView);
        btn = findViewById(R.id.btnIdentify);
        textViewAns = findViewById(R.id.textViewAns);
        textViewCorrect = findViewById(R.id.textViewCorrect);
        imageViewHome = findViewById(R.id.imageViewHome1);
        Cars = Arrays.asList(getResources().getStringArray(R.array.cars));
        timer1 = findViewById(R.id.textViewTimer);

        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");  //To get the boolean value from main activity

        if (isTimerSwitchOn) {    //Check weather switch is on or not
            countTimer = new CountDownTimer(21000, 1000) {  //Create the countDownTimer
                @Override
                public void onTick(long millisUntilFinished) {
                    String seconds = millisUntilFinished / 1000 + "";
                    timer1.setText(seconds);
                }

                @Override
                public void onFinish() {   //method for when the timer finished

                    btn = findViewById(R.id.btnIdentify);
                    int selectedItem = spinner.getSelectedItemPosition();
                    if (selectedItem == loadedImage) {
                        String correct = "CORRECT!";
                        textViewAns.setText(correct); //display correct message
                        textViewAns.setTextColor(Color.parseColor("#1BBEA1"));

                    } else {
                        String wrong = "WRONG!";  //display wrong message
                        textViewAns.setText(wrong);
                        textViewAns.setTextColor(Color.parseColor("#FF0000"));
                        textViewCorrect.setText(Cars.get(loadedImage));
                    }
                    nxtButtonCarMake();
                }
            };
            countTimer.start();
            start();


        } else {
            start();
        }

    }  //create the spinner

    public void start() {

        spinner = findViewById(R.id.label_spinner);
        ArrayAdapter<String> carAdapter = new ArrayAdapter<String>(IdentifyCarMake.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cars));
        carAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter to the spinner
        if (spinner != null) {
            spinner.setAdapter(carAdapter);
        }
        //create random images
        random = new Random();
        loadedImage = random.nextInt(imageArray.length);
        imageView.setImageResource(imageArray[loadedImage]);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert spinner != null;
                int selectedItem = spinner.getSelectedItemPosition();
                if (selectedItem == loadedImage) {
                    String correct = "CORRECT!";
                    textViewAns.setText(correct); //display correct message
                    textViewAns.setTextColor(Color.parseColor("#1BBEA1"));

                    if (isTimerSwitchOn) {
                        countTimer.cancel();                   //To pause the timer
                    }


                } else {
                    String wrong = "WRONG!";  //display wrong message
                    textViewAns.setText(wrong);
                    textViewAns.setTextColor(Color.parseColor("#FF0000"));
                    textViewCorrect.setText(Cars.get(loadedImage));

                    if (isTimerSwitchOn) {
                        countTimer.cancel();                   //To pause the timer
                    }


                }
                btn = findViewById(R.id.btnIdentify);
                //change button label
                nxtButtonCarMake();

            }
        });
    }
//}

    public void nxtButtonCarMake(){
    String next = "NEXT";
    btn.setText(next);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), IdentifyCarMake.class);
            intent.putExtra("switchValue", isTimerSwitchOn);
            startActivity(intent);
        }
    });
}
    public void HomeClick(View view) { //create home button to go back main page
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}