package mrandroid.app.activity.doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import mrandroid.app.R;
import mrandroid.app.databinding.ActivityAddQuestionBinding;
import mrandroid.app.model.AnswerModel;
import mrandroid.app.model.QuestionModel;
import mrandroid.app.util.LoadingDialog;

public class AddQuestionActivity extends AppCompatActivity {

    private ActivityAddQuestionBinding binding;
    private boolean is4Options = true;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadingDialog = new LoadingDialog(this);

        binding.rgOptionsNum.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.rbOptionNum2) setup2Options();
            else setup4Options();
        });

        binding.fabSubmit.setOnClickListener(view -> {
            boolean isValid = validateQuestion();
            if (isValid) createQuestion();
        });

    }

    private void setup4Options() {
        is4Options = true;
        binding.itlOption3.setVisibility(View.VISIBLE);
        binding.itlOption4.setVisibility(View.VISIBLE);
        binding.rbOption3.setVisibility(View.VISIBLE);
        binding.rbOption4.setVisibility(View.VISIBLE);
    }

    private void setup2Options() {
        is4Options = false;
        binding.itlOption3.setVisibility(View.GONE);
        binding.itlOption4.setVisibility(View.GONE);
        binding.rbOption3.setVisibility(View.GONE);
        binding.rbOption4.setVisibility(View.GONE);
    }

    private boolean validateQuestion() {
        String question = binding.etQuestion.getText().toString().trim();
        String option1Text = binding.etOption1.getText().toString().trim();
        String option2Text = binding.etOption2.getText().toString().trim();
        String option3Text = binding.etOption3.getText().toString().trim();
        String option4Text = binding.etOption4.getText().toString().trim();

        boolean isEmpty;
        if (is4Options)
            isEmpty = question.isEmpty() || option1Text.isEmpty() || option2Text.isEmpty() || option3Text.isEmpty() || option4Text.isEmpty();
        else isEmpty = question.isEmpty() || option1Text.isEmpty() || option2Text.isEmpty();

        if (isEmpty) {
            Toast.makeText(this, "Data is required!", Toast.LENGTH_SHORT).show();
            return false;
        }

        boolean hasCorrectAnswer;
        if (is4Options)
            hasCorrectAnswer = binding.rbOption1.isChecked() || binding.rbOption2.isChecked()
                    || binding.rbOption3.isChecked() || binding.rbOption4.isChecked();
        else hasCorrectAnswer = binding.rbOption1.isChecked() || binding.rbOption2.isChecked();

        if (!hasCorrectAnswer) {
            Toast.makeText(this, "Correct answer is required!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void createQuestion() {
        loadingDialog.display();

        boolean isCode = binding.cbCode.isChecked();
        String question = binding.etQuestion.getText().toString().trim();
        String option1Text = binding.etOption1.getText().toString().trim();
        String option2Text = binding.etOption2.getText().toString().trim();
        String option3Text = binding.etOption3.getText().toString().trim();
        String option4Text = binding.etOption4.getText().toString().trim();

        List<AnswerModel> answerList = new ArrayList<>();
        answerList.add(new AnswerModel(option1Text, binding.rbOption1.isChecked()));
        answerList.add(new AnswerModel(option2Text, binding.rbOption2.isChecked()));
        answerList.add(new AnswerModel(option3Text, binding.rbOption3.isChecked()));
        answerList.add(new AnswerModel(option4Text, binding.rbOption4.isChecked()));
        QuestionModel questionModel = new QuestionModel(question, is4Options,isCode, answerList);

        DatabaseReference questionRef = FirebaseDatabase.getInstance().getReference().child("questions").child(question);
        questionRef.setValue(questionModel)
                .addOnSuccessListener(aVoid -> {
                    loadingDialog.dismiss();
                    Toast.makeText(getBaseContext(), "Question created successfully", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    loadingDialog.dismiss();
                    Toast.makeText(getBaseContext(), "Error adding answer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}