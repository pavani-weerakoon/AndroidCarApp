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
import java.util.List;
import java.util.Random;

public class Hints extends AppCompatActivity {
    List<String> Cars;
    String carsArray[];
    ImageView imageViewHint;
    Button btnSubmit;
    int loadedHintImage;
    EditText editTextDash;
    boolean isTimerSwitchOn = false;
    TextView textViewHint, textViewHintAns, textView2, textviewTimer3;
    int count = 0;
    String input = "";
    String current = "";
    CountDownTimer countDownTimer;
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
        setContentView(R.layout.activity_hints);
        Cars = Arrays.asList(getResources().getStringArray(R.array.carsName));
        carsArray = getResources().getStringArray(R.array.carsName);

        imageViewHint = findViewById(R.id.imageViewHint);
        btnSubmit = findViewById(R.id.btnSubmit);
        editTextDash = (EditText) findViewById(R.id.editTextDash);
        textViewHint = (TextView) findViewById(R.id.textViewhint);
        textViewHintAns = (TextView) findViewById(R.id.textViewhintans);
        textView2 = findViewById(R.id.textView2);
        textviewTimer3 = findViewById(R.id.textViewTimer3);

        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");  //To get the boolean value from main activity

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
                textviewTimer3.setText(seconds);
            }

            @Override
            public void onFinish() {
                textViewHintAns.setText("WRONG!");//method for when the timer finished
                textViewHintAns.setTextColor(Color.RED);

                nextbutton();
            }
        };
        countDownTimer.start();
        //start();//To Start the countdown
    }

    public void start() {
        Random random = new Random();
        loadedHintImage = random.nextInt(image.length);
        imageViewHint.setImageResource(image[loadedHintImage]);


        String finalWord = Cars.get(loadedHintImage);
        int word = carsArray[loadedHintImage].length();
        String[] marks = new String[word];

        textViewHint.setText("");
        for (int i = 0; i < word; i++) {
            marks[i] = "_";
            textViewHint.append(marks[i] + " ");
        }
        input = editTextDash.getText().toString();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = "";
                input = editTextDash.getText().toString().toLowerCase();
                if (input.length() != 1) {
                    textViewHintAns.setText("Invalid");
                } else if (!finalWord.contains(input)) {
                    textViewHintAns.setText("WRONG");
                    textViewHintAns.setTextColor(Color.YELLOW);
                    count++;
                    if (isTimerSwitchOn) {
                        countDownTimer.cancel();
                        switchTimer();//To pause the timer
                    }
                } else if (finalWord.contains(input)) {

                    for (int i = 0; i < finalWord.length(); i++) {
                        if (finalWord.charAt(i) == input.charAt(0)) {
                            marks[i] = "" + input.charAt(0);
                        }
                        current += marks[i];
                    }
                }
                textViewHint.setText("");
                for (int i = 0; i < marks.length; i++) {
                    textViewHint.append(marks[i] + " ");
                }


                if (current.equals(finalWord)) {
                    textViewHintAns.setText("CORRECT!");
                    textViewHintAns.setTextColor(Color.GREEN);

                    if (isTimerSwitchOn) {
                        countDownTimer.cancel();                   //To pause the timer
                    }
                    ;

                    // change label
                    nextbutton();

                }


                if (count == 3) {
                    textViewHintAns.setText("WRONG!");
                    textViewHintAns.setTextColor(Color.RED);
                    textView2.setText(finalWord);
                    textView2.setTextColor(Color.YELLOW);

                    if (isTimerSwitchOn) {
                        countDownTimer.cancel();                   //To pause the timer
                    }

                    nextbutton();
                    // change label

                }
            }
        });

    }

    public void nextbutton() {
        btnSubmit.setText("NEXT");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hints.class);
                intent.putExtra("switchValue", isTimerSwitchOn);
                startActivity(intent);
            }
        });
    }

    public void HomeClickHint(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}

