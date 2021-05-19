package andjelka.kvizpliz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class quiz extends AppCompatActivity
{

    AdView AdView;

    Button answer, answer2, answer3, answer4;

    TextView score, question;

    ImageButton imageButton;

    private Questions mQuestions = new Questions();

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionsLenght = mQuestions.mQuestions.length;
    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Ads
        AdView mAdView;
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.AdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        r = new Random();


        answer = (Button)findViewById(R.id.answer);
        answer2 = (Button)findViewById(R.id.answer2);
        answer3 = (Button)findViewById(R.id.answer3);
        answer4 = (Button)findViewById(R.id.answer4);
        score = (TextView)findViewById(R.id.score);
        question = (TextView)findViewById(R.id.question);
        imageButton=(ImageButton)findViewById(R.id.imageButton);
        score.setText("Rezultat: " + mScore);

        updateQuestion(r.nextInt(mQuestionsLenght));

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer.getText() == mAnswer) {
                    mScore++;
                    score.setText("Rezultat: " + mScore);
                    updateQuestion(r.nextInt(mQuestionsLenght));
                    Toast.makeText(quiz.this, "Tačan odgovor :-)", Toast.LENGTH_SHORT).show();
                } else {
                    gameOver();
                }
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer2.getText() == mAnswer) {
                    mScore++;
                    score.setText("Rezultat: " + mScore);
                    updateQuestion(r.nextInt(mQuestionsLenght));
                    Toast.makeText(quiz.this, "Tačan odgovor :-)", Toast.LENGTH_SHORT).show();
                } else {
                    gameOver();
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer3.getText() == mAnswer) {
                    mScore++;
                    score.setText("Rezultat: " + mScore);
                    updateQuestion(r.nextInt(mQuestionsLenght));
                    Toast.makeText(quiz.this, "Tačan odgovor :-)", Toast.LENGTH_SHORT).show();
                } else {
                    gameOver();
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer4.getText() == mAnswer) {
                    mScore++;
                    score.setText("Rezultat: " + mScore);
                    updateQuestion(r.nextInt(mQuestionsLenght));
                    Toast.makeText(quiz.this, "Tačan odgovor :-)", Toast.LENGTH_SHORT).show();
                } else {
                    gameOver();
                }

            }
        });


    }

    public void drive(View view) {
        Intent browse=new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdEMSDCOhO7Gt0ZkNjW-XSPZGRhNfj-6JQ5UOIv6NLPv7DvSw/viewform?usp=sf_link"));
        startActivity(browse);
    }

    private void updateQuestion(int num){
        mAnswer="";
        question.setText(mQuestions.getmQuestions(num));
        answer.setText(mQuestions.getChoice1(num));
        answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));
        answer4.setText(mQuestions.getChoice4(num));
        mAnswer = mQuestions.getCorrectAnswer(num);

    }
    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("IGRA JE ZAVRŠENA")
                        .setMessage("Igra je završena! Da li želite da pokušate ponovo?")
                        .setNegativeButton("KRAJ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setPositiveButton("POKUŠAJ PONOVO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                updateQuestion(r.nextInt(mQuestionsLenght));
                                score.setText("Rezultat: " + 0);
                                mScore = 0;
                            }
                        });

// Show the AlertDialog.
        AlertDialog alertDialog = alertDialogBuilder.show();


    }
}
