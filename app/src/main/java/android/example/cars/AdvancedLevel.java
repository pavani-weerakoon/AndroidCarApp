package android.example.cars;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AdvancedLevel extends AppCompatActivity  {
    Button btnAdvancedSubmit;
    EditText editTextCar1, editTextCar2, editTextCar3;
    ImageView imageCarAdvanced1, imageCarAdvanced2, imageCarAdvanced3;
    Random randomCar;
    int randCar;
    Integer[] carList = new Integer[3];
    TextView textViewAdvancedAns, textViewHint1, textViewHint2, textViewHint3, textViewPoint, textviewtimer4;
    List<String> Cars;
    int attempt = 0;
    int point = 0;
    boolean isTimerSwitchOn = false;
    CountDownTimer countTimer;

    Integer[] image = {
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
        setContentView(R.layout.activity_advanced_level);
        randomCar = new Random();

        btnAdvancedSubmit = findViewById(R.id.btnAdvancedSubmit);
        editTextCar1 = findViewById(R.id.editTextCar1);
        editTextCar2 = findViewById(R.id.editTextCar2);
        editTextCar3 = findViewById(R.id.editTextCar3);
        imageCarAdvanced1 = findViewById(R.id.imageCarAdvanced1);
        imageCarAdvanced2 = findViewById(R.id.imageCarAdvanced2);
        imageCarAdvanced3 = findViewById(R.id.imageCarAdvanced3);
        textViewAdvancedAns = findViewById(R.id.textViewAdvanced);
        textViewHint1 = findViewById(R.id.textViewhint1);
        textViewHint2 = findViewById(R.id.textViewhint2);
        textViewHint3 = findViewById(R.id.textViewhint3);
        textViewPoint = findViewById(R.id.textViewPoint);
        textviewtimer4 = findViewById(R.id.textViewtimer4);


        Cars = Arrays.asList(getResources().getStringArray(R.array.carsName));
        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");  //To get the boolean value from main activit
        if (isTimerSwitchOn) {    //Check weather switch is on or not
            Timer();
            start();
        } else {
            start();
        }

    }

    //To start the countdown in the text view
    public void Timer() {
        countTimer = new CountDownTimer(21000, 1000) {  //Create the countDownTimer
            @Override
            public void onTick(long millisUntilFinished) {
                String seconds = millisUntilFinished / 1000 + "";
                textviewtimer4.setText(seconds);
            }

            @Override
            public void onFinish() {
                //method for when the timer finished
                textViewAdvancedAns = findViewById(R.id.textViewAdvanced);
                textViewAdvancedAns.setText("WRONG!");
                textViewAdvancedAns.setTextColor(Color.RED);
                nextbtn();
            }
        };
        countTimer.start();
        //start();//To Start the countdown
    }

    public void start() {
        carList[0] = randomCar.nextInt(image.length);
        carList[1] = randomCar.nextInt(image.length);
        while (carList[0] == carList[1]){
            carList[1] = randomCar.nextInt(image.length);
        }
        carList[2] = randomCar.nextInt(image.length);
        while (carList[0] == carList[1]){
            carList[2] = randomCar.nextInt(image.length);
        }
        while (carList[0] == carList[2]){
            carList[2] = randomCar.nextInt(image.length);
        }

        final List list = Arrays.asList(carList);
        Collections.shuffle(list);
        list.toArray(carList);

        imageCarAdvanced1.setImageResource(image[carList[0]]);
        imageCarAdvanced2.setImageResource(image[carList[1]]);
        imageCarAdvanced3.setImageResource(image[carList[2]]);
    }

    public void AdvancedOnClick(View view) {
        attempt++;
        if(isTimerSwitchOn){
            countTimer.cancel();                 //To reset the timer
            Timer();
        }
        String answe1 = Cars.get(carList[0]); // save correct answers from array
        String answe2 = Cars.get(carList[1]);
        String answe3 = Cars.get(carList[2]);
        //check user input from 3 tex boxes
        if (editTextCar1 != null) {
            if (answe1.equals(editTextCar1.getText().toString().toLowerCase())) {
                editTextCar1.setTextColor(Color.GREEN);
                point++;
                if (textViewPoint != null) {
                    textViewPoint.setText(Integer.toString(point));
                }
                point = 0;

                editTextCar1.setEnabled(false);
            } else {
                editTextCar1.setTextColor(Color.RED);
            }
        }
        if (editTextCar2 != null) {
            if (answe2.equals(editTextCar2.getText().toString().toLowerCase())) {
                editTextCar2.setTextColor(Color.GREEN);
                point++;
                if (textViewPoint != null) {
                    textViewPoint.setText(Integer.toString(point));
                }
                point = 0;
                editTextCar2.setEnabled(false);
            } else {
                editTextCar2.setTextColor(Color.RED);
            }
        }
        if (editTextCar3 != null) {
            if (answe3.equals(editTextCar3.getText().toString().toLowerCase())) {
                editTextCar3.setTextColor(Color.GREEN);
                point++;
                if (textViewPoint != null) {
                    textViewPoint.setText(Integer.toString(point));
                }
                point = 0;
                editTextCar3.setEnabled(false);


            } else {
                editTextCar3.setTextColor(Color.RED);
            }

        }
        // show correct message for all 3 answers
        if (editTextCar1 != null && editTextCar2 != null && editTextCar3 != null) {
            if (answe1.equals(editTextCar1.getText().toString()) && (answe2.equals(editTextCar2.getText().toString())) && (answe3.equals(editTextCar3.getText().toString()))) {
                String ans = "CORRECT!";
                textViewAdvancedAns.setText(ans);
                textViewAdvancedAns.setTextColor(Color.GREEN);

                if (isTimerSwitchOn) {
                    countTimer.cancel();                   //To pause the timer
                }

                //change label button
                btnAdvancedSubmit.setText("NEXT");
                nextbtn();
            }
        }
        // point 2 answers
        if (answe1.equals(editTextCar1.getText().toString()) && (answe2.equals(editTextCar2.getText().toString()))) {
            textViewPoint.setText(Integer.toString(2));
        }
        if (answe1.equals(editTextCar1.getText().toString()) && (answe3.equals(editTextCar3.getText().toString()))) {
            textViewPoint.setText(Integer.toString(2));
        }
        if (answe2.equals(editTextCar2.getText().toString()) && (answe3.equals(editTextCar3.getText().toString()))) {
            textViewPoint.setText(Integer.toString(2));
        }
        //point 3 for correct all answers
        if (answe1.equals(editTextCar1.getText().toString()) && (answe3.equals(editTextCar3.getText().toString()) && (answe2.equals(editTextCar2.getText().toString())))) {
            textViewPoint.setText(Integer.toString(3));
        }

        //check user attempts
        if (attempt == 3) {
            assert editTextCar1 != null;
            if (!answe1.equals(editTextCar1.getText().toString()) || (!answe2.equals(editTextCar2.getText().toString())) || (!answe3.equals(editTextCar3.getText().toString()))) {
                String ans = "WRONG!";
                textViewAdvancedAns.setText(ans);
                textViewAdvancedAns.setTextColor(Color.RED);

                if (isTimerSwitchOn) {
                    countTimer.cancel();                   //To pause the timer
                }
            }
            if (!answe1.equals(editTextCar1.getText().toString())) {
                textViewHint1.setText(answe1);// show correct answer
                textViewHint1.setTextColor(Color.YELLOW);
            }
            if (!answe2.equals(editTextCar2.getText().toString())) {
                textViewHint2.setText(answe2);// show correct answer
                textViewHint2.setTextColor(Color.YELLOW);
            }
            if (!answe3.equals(editTextCar3.getText().toString())) {
                textViewHint3.setText(answe3);// show correct answer
                textViewHint3.setTextColor(Color.YELLOW);
            }
            //change label
            nextbtn();

        }
    }

    public void nextbtn() {
        btnAdvancedSubmit.setText("NEXT");
        btnAdvancedSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdvancedLevel.class);
                intent.putExtra("switchValue", isTimerSwitchOn);
                startActivity(intent);
            }
        });
    }

    public void HomeClickAdvanced(View view) {  //home button to go back home page
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
}

