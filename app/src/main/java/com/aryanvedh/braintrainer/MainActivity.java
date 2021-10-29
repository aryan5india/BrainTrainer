package com.aryanvedh.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerText, mathText, scoreText, resultTextView;
    Button button1, button2, button3, button4, playAgainBtn;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    ConstraintLayout coverLayout;
    int score = 0;
    int questions = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.resultTextView);
        scoreText = findViewById(R.id.scoreText);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        mathText = findViewById(R.id.mathText);
        timerText = findViewById(R.id.timerText);
        playAgainBtn = findViewById(R.id.playAgainButton);
        coverLayout =findViewById(R.id.coverLayout);

        Button startGame = findViewById(R.id.startGame);
        coverLayout.setVisibility(View.VISIBLE);

        startGame.setOnClickListener(view -> {
            coverLayout.setVisibility(View.INVISIBLE);
        });

//        generateQuestion();

        playAgain(findViewById(R.id.playAgainButton));
    }

    private void generateQuestion() {

        Random randomGenerator = new Random();
        int randomNumber1 = randomGenerator.nextInt(21);
        int randomNumber2 = randomGenerator.nextInt(21);
        mathText.setText(randomNumber1 + " + " + randomNumber2);

        locationOfCorrectAnswer = randomGenerator.nextInt(4);
        answers.clear();

        int incorrectAnswer = 0;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {
                answers.add(randomNumber1 + randomNumber2);
            } else {
                incorrectAnswer = randomGenerator.nextInt(41);
                while (incorrectAnswer == randomNumber1 + randomNumber2) {
                    incorrectAnswer = randomGenerator.nextInt(41);
                }
            }
            answers.add(incorrectAnswer);

        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }


    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view) {

        if (view.getTag().equals(Integer.toString(locationOfCorrectAnswer))) {

            resultTextView.setText("Correct Answer!");
            resultTextView.setTextColor(Color.GREEN);
            score++;
        } else {
            resultTextView.setText("Wrong Answer!");
            resultTextView.setTextColor(Color.RED);
        }
        questions++;
        scoreText.setText(score + "/" + questions);
        generateQuestion();
    }

    public void playAgain(View view) {
        score = 0;
        questions = 0;

        timerText.setText("30s");
        scoreText.setText("0/0");
        resultTextView.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        generateQuestion();

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long milliseconds) {
                timerText.setText(String.valueOf(milliseconds / 1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgainBtn.setVisibility(View.VISIBLE);
                resultTextView.setText("Your score is: " + score + "/" + questions);
                resultTextView.setTextColor(Color.BLACK);

                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
            }
        }.start();

    }


}