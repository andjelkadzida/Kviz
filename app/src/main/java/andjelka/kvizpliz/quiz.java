package andjelka.kvizpliz;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.analytics.FirebaseAnalytics;


public class quiz extends AppCompatActivity
{

    Button answer, answer2, answer3, answer4;

    TextView score, question;

    ImageButton imageButton;

    private Questions mQuestions = new Questions();

    SharedPreferences sharedPreferences;

    private FirebaseAnalytics mFirebaseAnalytics;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionsNumber = 0;

    //sharedPrefereneces
    private static final String PREF_NAME = "MyResults";
    private static final String SCORE = "score";
    private static final String HIGH_SCORE = "highScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        sharedPreferences = getSharedPreferences(PREF_NAME, 0);

        //Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Ads
        AdView mAdView;
        MobileAds.initialize(this, initializationStatus -> {
        });

        mAdView = findViewById(R.id.AdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        answer = findViewById(R.id.answer);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        score = findViewById(R.id.score);
        question = findViewById(R.id.question);
        imageButton = findViewById(R.id.imageButton);
        score.setText(R.string.yourScore + mScore);

        mQuestions.initQuests(getApplicationContext());
        updateQuestion();
        updateScore(mScore);

        //Analitika za prijavu problema
        recordImageButtonClick();
    }

    public void chooseAnswer(View view)
    {
        Button answer = (Button) view;
        if(answer.getText().equals(mAnswer))
        {
            mScore+=1;
            Toast.makeText(quiz.this, R.string.correctAnswer, Toast.LENGTH_SHORT).show();
            updateQuestion();
            updateScore(mScore);
        }
        else
        {
            gameOver();
        }
    }

    public void reportProblem(View view)
    {
        Intent intent = new Intent(quiz.this, Report_Problem.class);
        startActivity(intent);
    }

    private void updateQuestion()
    {
        if(mQuestionsNumber<mQuestions.getLength())
        {
            question.setText(mQuestions.getQuestion(mQuestionsNumber));
            answer.setText(mQuestions.getChoice(mQuestionsNumber, 1));
            answer2.setText(mQuestions.getChoice(mQuestionsNumber, 2));
            answer3.setText(mQuestions.getChoice(mQuestionsNumber, 3));
            answer4.setText(mQuestions.getChoice(mQuestionsNumber, 4));
            mAnswer = mQuestions.getCorrectAnswer(mQuestionsNumber);
            mQuestionsNumber++;
        }
        else
        {
            Toast.makeText(quiz.this, R.string.gameCompleted, Toast.LENGTH_SHORT).show();
            updateHighscore();
            //Pokretanje aktivnosti sa najboljim rezultatima
            Intent i = new Intent(quiz.this, HighestScoreActivity.class);
            startActivity(i);
        }
    }

    private void updateScore(int points) {
        score.setText( getString(R.string.yourScore,  mScore));
    }

    private void updateHighscore() {
        int mCurrentScore = mScore;
        int mHighestScore = this.sharedPreferences.getInt(HIGH_SCORE, 0);
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        if(mCurrentScore >= mHighestScore) {
            //Upisivanje novog najboljeg rezultata
            editor.putInt(HIGH_SCORE, mCurrentScore);
        }
        editor.putInt(SCORE, mCurrentScore);
        editor.apply();
    }

    private void gameOver() {
        updateHighscore();
        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle(R.string.gameOver)
                        .setMessage(R.string.gameOverMessage)
                        .setNegativeButton(R.string.end, (dialog, i) -> finish())
                        .setPositiveButton(R.string.tryAgain, (dialog, i) -> {
                            updateQuestion();
                            mQuestions.initQuests(getApplicationContext());
                            mScore = 0;
                            score.setText(getString(R.string.yourScore, mScore));
                        });
        // Show the AlertDialog.
       alertDialogBuilder.show();
    }

    private void recordImageButtonClick() {

        String id = "imageButton";
        String name = getResources().getString(R.string.reportProblem);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "report_problem");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
