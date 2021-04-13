package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trivia.data.AnswerListAsyncResponse;
import com.example.trivia.data.QuestionBank;
import com.example.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;
    private TextView counterTextView;
    private TextView possibleAnswer1TextView;
    private TextView possibleAnswer2TextView;
    private TextView possibleAnswer3TextView;
    private TextView possibleAnswer4TextView;
    private ImageButton previousQuestionImageButton;
    private ImageButton nextQuestionImageButton;
    private int currentIndexCounter = -1;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionList = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                //Log.d("Main", "onCreate " + questionArrayList);
            }
        });

        questionTextView = findViewById(R.id.questionTextView);
        counterTextView = findViewById(R.id.counterTextView);
        possibleAnswer1TextView = findViewById(R.id.firstPossibleAnswerTextView);
        possibleAnswer2TextView = findViewById(R.id.secondPossibleAnswerTextView);
        possibleAnswer3TextView = findViewById(R.id.thirdPossibleAnswerTextView);
        possibleAnswer4TextView = findViewById(R.id.forthPossibleAnswerTextView);
        previousQuestionImageButton = findViewById(R.id.previousQuestionImageButton);
        nextQuestionImageButton = findViewById(R.id.nextQuestionImageButton);

        previousQuestionImageButton.setOnClickListener(this);
        nextQuestionImageButton.setOnClickListener(this);
        possibleAnswer1TextView.setOnClickListener(this);
        possibleAnswer2TextView.setOnClickListener(this);
        possibleAnswer3TextView.setOnClickListener(this);
        possibleAnswer4TextView.setOnClickListener(this);

       // Log.d("Main", "onCreate " + questionList);
    }

    // Better attach onClick to CardViews instead of TextViews - possibleAnswers
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previousQuestionImageButton:
                if (currentIndexCounter > 0) {
                    currentIndexCounter = (currentIndexCounter - 1) % questionList.size();
                    updateQuestion();
                    updateCounterText();
                }
                break;
            case R.id.nextQuestionImageButton:
                currentIndexCounter = (currentIndexCounter + 1) % questionList.size();
                updateQuestion();
                updateCounterText();
                break;
            case R.id.firstPossibleAnswerTextView:
                evaluateAnswer(possibleAnswer1TextView);

                break;
            case R.id.secondPossibleAnswerTextView:
                evaluateAnswer(possibleAnswer2TextView);
                break;
            case R.id.thirdPossibleAnswerTextView:
                evaluateAnswer(possibleAnswer3TextView);
                break;
            case R.id.forthPossibleAnswerTextView:
                evaluateAnswer(possibleAnswer4TextView);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void evaluateAnswer(TextView view) {
        if (checkAnswer(view.getText().toString())) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    private boolean checkAnswer(String selectedAnswer) {
        return questionList.get(currentIndexCounter).getCorrectAnswer().equals(selectedAnswer);
    }

    private void updateCounterText() {
        counterTextView.setText(String.format("%d / %d", currentIndexCounter, questionList.size()));
    }

    private void updateQuestion() {
        possibleAnswer1TextView.setText("");
        possibleAnswer2TextView.setText("");
        possibleAnswer3TextView.setText("");
        possibleAnswer4TextView.setText("");

        questionTextView.setText(questionList.get(currentIndexCounter).getQuestion());

        if (questionList.get(currentIndexCounter).getPossibleAnswers().size() >= 1) {
            possibleAnswer1TextView.setText(questionList.get(currentIndexCounter).getPossibleAnswers().get(0));
        }
        if (questionList.get(currentIndexCounter).getPossibleAnswers().size() >= 2) {
            possibleAnswer2TextView.setText(questionList.get(currentIndexCounter).getPossibleAnswers().get(1));
        }
        if (questionList.get(currentIndexCounter).getPossibleAnswers().size() >= 3) {
            possibleAnswer3TextView.setText(questionList.get(currentIndexCounter).getPossibleAnswers().get(2));
        }
        if (questionList.get(currentIndexCounter).getPossibleAnswers().size() >= 4) {
            possibleAnswer4TextView.setText(questionList.get(currentIndexCounter).getPossibleAnswers().get(3));
        }

    }
}