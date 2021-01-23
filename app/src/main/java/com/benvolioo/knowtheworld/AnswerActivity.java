package com.benvolioo.knowtheworld;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.benvolioo.knowtheworld.QuizActivity.*;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    public static final String CURRENT_SCORE = "currentScore";
    public static final String QUESTION_COUNT = "questionCounter";


    private Integer pbMax = QuizActivity.questionCountTotal;
    private Integer currentScore;
    private Integer questionCount;
    private Boolean checkedAnswer;


    private TextView txtAnswerResult;
    private Button btnNextQuestion;
    private ProgressBar pbPercentCorrect;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent intent = getIntent();

        txtAnswerResult = findViewById(R.id.txtAnswerResult);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        pbPercentCorrect = findViewById(R.id.pbAnswers);

        checkedAnswer = intent.getBooleanExtra("ANSWER_RESULT", true);
        currentScore = intent.getIntExtra("CURRENT_SCORE", 0);
        questionCount = intent.getIntExtra("QUESTION_COUNT", 0);

        setTxtCheckedAnswer();

        View.OnClickListener onclickNextQuestion = (View view) -> finishAnswer();
        btnNextQuestion.setOnClickListener(onclickNextQuestion);

        int currentProgress = (int) (currentScore * 100.0f)/pbMax;
        pbPercentCorrect.setProgress(currentProgress);

    }

    private void finishAnswer() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(QUESTION_COUNT, questionCount);
        resultIntent.putExtra(CURRENT_SCORE, currentScore);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


    private void setTxtCheckedAnswer() {
        if (checkedAnswer) {
            txtAnswerResult.setText("Correct");
        } else {
            txtAnswerResult.setText("Not quite");
        }
    }


}
