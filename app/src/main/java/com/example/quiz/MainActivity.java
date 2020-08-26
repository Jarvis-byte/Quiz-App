package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainbutton;
    int locationCorrectAnswer;
    TextView textAnswer;
    TextView textScore;
    TextView textTimer;
    int score=0;
    int numberOfQuestions=0;
    ArrayList<Integer> answers=new ArrayList<Integer>();

public void chooseAnswer(View view)
{


    int tag=Integer.parseInt(view.getTag().toString());
    if(locationCorrectAnswer==tag)
    {
        textAnswer.setVisibility(View.VISIBLE);
        textAnswer.setText("Correct!");
        score++;

    }
    else
    {
        textAnswer.setVisibility(View.VISIBLE);
        textAnswer.setText("Wrong!");
    }
    numberOfQuestions++;
    textScore.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
newQuestion();

}
public void newQuestion()
{
    Random num=new Random();
    int a=num.nextInt(21);
    int b=num.nextInt(21);
    TextView text=(TextView)findViewById(R.id.sumTextView);
    text.setText(String.valueOf(a)+"+"+String.valueOf(b));

    locationCorrectAnswer = num.nextInt(4);
    answers.clear();
    for(int i=0;i<4;i++)
    {
        if(i==locationCorrectAnswer)
        {
            answers.add(a+b);
        }
        else
        {
            int wrongAnswer=num.nextInt(41);

            while (wrongAnswer == (a + b)) {
                wrongAnswer = num.nextInt(41);
            }
            answers.add(wrongAnswer);
        }
    }
    button0.setText(Integer.toString(answers.get(0)));
    button1.setText(Integer.toString(answers.get(1)));
    button2.setText(Integer.toString(answers.get(2)));
    button3.setText(Integer.toString(answers.get(3)));
}

public void playAgain(View view)
{
score=0;
numberOfQuestions=0;
textTimer.setText("30s");
    textScore.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
newQuestion();
    button0.setEnabled(true);
    button1.setEnabled(true);
    button2.setEnabled(true);
    button3.setEnabled(true);
                  timer();
    playAgainbutton.setVisibility(View.INVISIBLE);
    textAnswer.setVisibility(View.INVISIBLE);
}



public void timer()
{
    new CountDownTimer(30100,1000)
    {

        @Override
        public void onTick(long l) {

            textTimer.setText(String.valueOf(l/1000)+"s");
        }

        @Override
        public void onFinish() {
            button0.setEnabled(false);
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            textAnswer.setVisibility(View.VISIBLE);
            playAgainbutton.setVisibility(View.VISIBLE);
            textAnswer.setText("DONE");

        }
    }.start();
}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
textTimer=(TextView)findViewById(R.id.timerTextView);
       textAnswer=(TextView)findViewById(R.id.resultTestView);
textScore=(TextView)findViewById(R.id.scoreTextView);

       button0=findViewById(R.id.button0);
       button1=findViewById(R.id.button1);
       button2=findViewById(R.id.button2);
       button3=findViewById(R.id.button3);
playAgainbutton=findViewById(R.id.playAgainbutton);
                newQuestion();
                timer();

    }
}