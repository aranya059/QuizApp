package edu.usta.cs3443.week6_d1_quizapp;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.InputStream;

import edu.usta.cs3443.week6_d1_quizapp.model.QuizBank;

public class MainActivity extends AppCompatActivity {

    private QuizBank quizBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button next_btn, true_btn, false_btn;


        next_btn = findViewById(R.id.next_btn);
        true_btn = findViewById(R.id.true_btn);
        false_btn = findViewById(R.id.false_btn);


        createQuizBank();
        displayQuestion();

        true_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getAnswer()){
                    Toast.makeText(v.getContext(), "Yay! Correct answer", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(v.getContext(), "Try again! Wrong answer", Toast.LENGTH_LONG).show();
                }
            }
        });

        false_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getAnswer()){
                    Toast.makeText(v.getContext(), "Yay! Correct answer", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(v.getContext(), "Try again!", Toast.LENGTH_LONG).show();
                }
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayQuestion();
            }
        });
    }

    public void createQuizBank(){
        quizBank = new QuizBank();
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("questions.csv");
            quizBank.loadQuestion(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayQuestion(){
        quizBank.getCurrentQuestion();
        TextView quiz_question;
        quiz_question = findViewById(R.id.quiz_question);
        quiz_question.setText(getQuestion());
    }

    private String getQuestion(){
        return quizBank.getCurrentQuestionText();
    }

    private boolean getAnswer(){
        return quizBank.getCurrentQuestionAnswer();
    }
}

// This is a test comment to experiment with git
// zcj245
