package android.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IdentifyCarImage extends AppCompatActivity {
    TextView timer2;
    Button btnNext;
    TextView textCars, textCarAnswer;
    ImageView imageCar1, imageCar3, imageCar2;
    Random random;
    int randCar;
    CountDownTimer countDownTimer;
    boolean isTimerSwitchOn = false;
    Integer[] carList = new Integer[3];
    Integer[] CarImageArray = {
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
        setContentView(R.layout.activity_identify_car_image);
        timer2 = findViewById(R.id.textViewTimer2);

        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");  //To get the boolean value from main activity

        textCars = findViewById(R.id.textCars);
        imageCar1 = findViewById(R.id.imageCar1);
        imageCar2 = findViewById(R.id.imageCar2);
        imageCar3 = findViewById(R.id.imageCar3);
        textCarAnswer = findViewById(R.id.textCarAnswer);
        btnNext = findViewById(R.id.btnNext);

        if (isTimerSwitchOn) {    //Check weather switch is on or not
            switchTimer();
            start();
        } else {
            start();
        }

    }

    //To start the countdown in the text view
    public void switchTimer() {
        countDownTimer = new CountDownTimer(21000, 1000) {  //Create the countDownTimer
            @Override
            public void onTick(long millisUntilFinished) {
                String seconds = millisUntilFinished / 1000 + "";
                timer2.setText(seconds);
            }

            @Override
            public void onFinish() {   //method for when the timer finished
                String answer = "WRONG!";
                textCarAnswer.setText(answer);//display wrong answer
                textCarAnswer.setTextColor(Color.parseColor("#FF0000"));

            }
        };
        countDownTimer.start();
        start();//To Start the countdown
    }

    public void start() {
        String[] carsArray = getResources().getStringArray(R.array.cars);
        //random images
        random = new Random();
        randCar = random.nextInt(carsArray.length);
        textCars.setText(carsArray[randCar]);

        carList[0] = randCar;
        carList[1] = random.nextInt(CarImageArray.length);
        while (carList[0] == carList[1]){
            carList[1] = random.nextInt(CarImageArray.length);
        }
        carList[2] = random.nextInt(CarImageArray.length);
        while ((carList[0])== carList[2]){
            carList[2] = random.nextInt(CarImageArray.length);
        }
        while ((carList[1])== carList[2]){
            carList[2] = random.nextInt(CarImageArray.length);
        }

        final List listNew = Arrays.asList(carList);
        Collections.shuffle(listNew);
        listNew.toArray(carList);

        imageCar1.setImageResource(CarImageArray[carList[0]]);
        imageCar2.setImageResource(CarImageArray[carList[1]]);
        imageCar3.setImageResource(CarImageArray[carList[2]]);


        imageCar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (randCar == carList[0]) {
                    String answer = "CORRECT!";
                    textCarAnswer.setText(answer);
                    textCarAnswer.setTextColor(Color.parseColor("#1BBEA1"));

                } else {
                    String answer = "WRONG!";
                    textCarAnswer.setText(answer);
                    textCarAnswer.setTextColor(Color.parseColor("#FF0000"));

                }
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }

                disablebutton();
            }
        });

        imageCar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (randCar == carList[1]) {
                    String answer = "CORRECT!";  //display correct answer
                    textCarAnswer.setText(answer);
                    textCarAnswer.setTextColor(Color.parseColor("#1BBEA1"));

                } else {
                    String answer = "WRONG!";   //display wrong answer
                    textCarAnswer.setText(answer);
                    textCarAnswer.setTextColor(Color.parseColor("#FF0000"));

                }
                disablebutton();
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }
            }
        });

        imageCar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (randCar == carList[2]) {
                    String answer = "CORRECT!";
                    textCarAnswer.setText(answer);
                    textCarAnswer.setTextColor(Color.parseColor("#1BBEA1"));
                    if (isTimerSwitchOn) {
                        countDownTimer.cancel();                   //To pause the timer
                    }

                } else {
                    String answer = "WRONG!";
                    textCarAnswer.setText(answer);
                    textCarAnswer.setTextColor(Color.parseColor("#FF0000"));
                    if (isTimerSwitchOn) {
                        countDownTimer.cancel();                   //To pause the timer
                    }
                }
                disablebutton();
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }
            }
        });

    }


    public void onClickNext(View view) {
        Intent intent1 = new Intent(getApplicationContext(), IdentifyCarImage.class);
        intent1.putExtra("switchValue", isTimerSwitchOn);
        startActivity(intent1);
    }

    public void disablebutton() {   //can't edit clickable images
        imageCar1.setEnabled(false);
        imageCar2.setEnabled(false);
        imageCar3.setEnabled(false);
    }


    public void HomeClickCarImage(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}