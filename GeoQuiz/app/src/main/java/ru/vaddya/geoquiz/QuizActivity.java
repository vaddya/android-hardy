package ru.vaddya.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    private static final int REQUST_CODE_CHEAT = 0;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button cheatButton;

    private TextView questionTextView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            isCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    private boolean isCheater;

    private Question[] questions = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, true)
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(this.getLocalClassName(), "onSaveInstanceState");
        outState.putInt(KEY_INDEX, currentIndex);
    }

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = (TextView) findViewById(R.id.qustion_text);
        isCheater = false;
        updateQuestion();

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (Button) findViewById(R.id.next_button);
        cheatButton = (Button) findViewById(R.id.cheat_button);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = ++currentIndex % questions.length;
                updateQuestion();
            }
        });

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isTrue = questions[currentIndex].isAnswerTrue();
                startActivityForResult(CheatActivity.newIntent(QuizActivity.this, isTrue), REQUST_CODE_CHEAT);
            }
        });
    }

    private void updateQuestion() {
        int question = questions[currentIndex].getTextResId();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isAnswerTrue();
        int messageResId = 0;

        if (isCheater) {
            messageResId = R.string.judgment_toast;
        } else {

            if (userAnswer == correctAnswer) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}
