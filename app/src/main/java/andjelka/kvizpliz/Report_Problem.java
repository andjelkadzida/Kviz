package andjelka.kvizpliz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Report_Problem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        Button btnSendReport = findViewById(R.id.btnSendReport);
        EditText problemDescription = findViewById(R.id.problemDescription);
        EditText contact = findViewById(R.id.contact);
        RadioGroup radioGroup = findViewById(R.id.reportSelector);
        View view = findViewById(R.id.problemReportLayout);

        btnSendReport.setOnClickListener(v -> {
            RadioButton radioButton;
            if(problemDescription.getText().toString().isEmpty() || radioGroup.getCheckedRadioButtonId() == -1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Report_Problem.this, R.style.AlertDialogCustomDesign);
                builder.setTitle(R.string.errorTitle);
                builder.setMessage(R.string.errorMessage);
                builder.setIcon(R.drawable.ic_warning);
                builder.setCancelable(false);

                builder.setPositiveButton(R.string.OK, (dialog, which) -> dialog.dismiss());
                builder.show();
            }
            else {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"andjelkadzida@gmail.com"});

                int selectedRadioButton = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedRadioButton);
                intent.putExtra(Intent.EXTRA_SUBJECT, radioButton.getText().toString());
                if(contact.getText().toString().isEmpty()) {
                    intent.putExtra(Intent.EXTRA_TEXT, problemDescription.getText().toString());
                }
                else {
                    intent.putExtra(Intent.EXTRA_TEXT, problemDescription.getText().toString() + " \n" + getResources().getString(R.string.contact) + contact.getText().toString());
                }
                try {
                    startActivity(Intent.createChooser(intent, getResources().getString(R.string.send)));
                    boolean activityStarted = isActivityStarted(intent);

                    if(activityStarted) {
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            //Poruka o poslatoj prijavi
                            Snackbar snackbar = Snackbar.make(view, getResources().getString(R.string.msgSent), Snackbar.LENGTH_INDEFINITE);
                            TextView messageView = snackbar.getView().findViewById(R.id.snackbar_text);
                            messageView.setMaxLines(5);
                            snackbar.setAction(getResources().getString(R.string.OK), v12 -> snackbar.dismiss());
                            snackbar.setActionTextColor(getResources().getColor(android.R.color.holo_green_dark)).show();
                        }, 5000);

                        //Obrisi polja
                        contact.getText().clear();
                        problemDescription.getText().clear();
                        radioGroup.clearCheck();
                    }
                }
                catch (Exception ex)
                {
                        Snackbar.make(view, getResources().getString(R.string.noEmailApp), Snackbar.LENGTH_SHORT).setAction(getResources().getString(R.string.installNow), v1 -> {
                            try
                            {
                                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.gm"));
                                startActivity(i);
                            }
                            catch (Exception exception)
                            {
                                Toast.makeText(Report_Problem.this, getResources().getString(R.string.checkConnection), Toast.LENGTH_SHORT).show();
                            }
                        }).setActionTextColor(getResources().getColor(android.R.color.holo_green_dark)).show();
                    }
                }
            });
    }
    private boolean isActivityStarted(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return !activities.isEmpty();
    }

}