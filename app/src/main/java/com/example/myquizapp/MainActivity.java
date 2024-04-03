package com.example.myquizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button option1Button, option2Button, option3Button, option4Button;
    private TextView correctAnswerTextView;

    private String[][] questions = {
            {"CAPITAL OF INDIA ?", "MUMBAI", "NEW DELHI", "CHENNAI", "BENGALURU", "NEW DELHI"},
            {"3RD LARGEST TRIBE OF INDIA ?", "SANTHAL", "BHILS", "GONDS", "MUNDA", "SANTHAL"},
            {"AS OF 2023 WHO IS THE LARGEST AUTOMOBILE MANUFACTURER IN THE WORLD ?", "FORD", "SUZUKI", "TOYOTA", "MAHINDRA", "TOYOTA"},
            {"WHO IS THE CEO OF SPACEX ?", "ELON MUSK","SATYA NADELLA", "MARK ZUCKERBERG", "SHOU ZI CHEW", "ELON MUSK"},
            {"HOW MANY LUNGS DOES A HUMAN HAVE ?", "8", "6", "4", "2", "2"},
    };

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.question);
        option1Button = findViewById(R.id.option1);
        option2Button = findViewById(R.id.option2);
        option3Button = findViewById(R.id.option3);
        option4Button = findViewById(R.id.option4);
        correctAnswerTextView = findViewById(R.id.correct_answer);

        option1Button.setOnClickListener(v -> checkAnswer(option1Button.getText().toString()));

        option2Button.setOnClickListener(v -> checkAnswer(option2Button.getText().toString()));

        option3Button.setOnClickListener(v -> checkAnswer(option3Button.getText().toString()));

        option4Button.setOnClickListener(v -> checkAnswer(option4Button.getText().toString()));

        updateQuestion();
    }

    private void updateQuestion() {
        correctAnswerTextView.setText("");

        String[] currentQuestion = questions[currentQuestionIndex];

        questionTextView.setText(currentQuestion[0]);
        option1Button.setText(currentQuestion[1]);
        option2Button.setText(currentQuestion[2]);
        option3Button.setText(currentQuestion[3]);
        option4Button.setText(currentQuestion[4]);
    }


    private void checkAnswer(String selectedOption) {
        String correctAnswer = questions[currentQuestionIndex][5];

        if(selectedOption.equals(correctAnswer)) {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            score++;
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.RED);
            correctAnswerTextView.setText("Correct Answer: " + correctAnswer);
        }

        currentQuestionIndex = (currentQuestionIndex + 1) % questions.length;

        if(currentQuestionIndex == 0) {
            showFinalScore();
        } else {
            questionTextView.postDelayed(() -> {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                updateQuestion();
            }, 2000);
        }
    }

    private void showFinalScore() {
        Toast.makeText(this, "Final Score: " + score, Toast.LENGTH_LONG).show();
    }
}
