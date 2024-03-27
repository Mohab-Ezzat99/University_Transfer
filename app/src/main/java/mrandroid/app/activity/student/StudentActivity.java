package mrandroid.app.activity.student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mrandroid.app.R;
import mrandroid.app.databinding.ActivityStudentBinding;
import mrandroid.app.model.AnswerModel;
import mrandroid.app.model.QuestionModel;
import mrandroid.app.util.LoadingDialog;

public class StudentActivity extends AppCompatActivity {

    private ActivityStudentBinding binding;
    private LoadingDialog loadingDialog;
    private List<QuestionModel> questionList = new ArrayList<>();
    private boolean hasSelectAnswer = false;
    private int questionPosition = -1;
    private int answerPosition = -1;
    private int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadingDialog = new LoadingDialog(this);

        binding.tvOpenCode.setOnClickListener(v -> {
            String url = "https://www.online-python.com";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        binding.rgOptionsAnswer.setOnCheckedChangeListener((group, checkedId) -> {
            hasSelectAnswer = true;
            switch (checkedId) {
                case R.id.rbOption1:
                    answerPosition = 0;
                    break;
                case R.id.rbOption2:
                    answerPosition = 1;
                    break;
                case R.id.rbOption3:
                    answerPosition = 2;
                    break;
                case R.id.rbOption4:
                    answerPosition = 3;
                    break;
            }
        });

        binding.fabSubmit.setOnClickListener(v -> {
            if (!hasSelectAnswer) {
                Toast.makeText(this, "Please select answer!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (questionPosition == questionList.size() - 1) {
                updateAnswerResult();

                Intent intent = new Intent(getBaseContext(), ExamResultActivity.class);
                intent.putExtra("total", questionList.size());
                intent.putExtra("score", correctAnswers);
                startActivity(intent);
                finish();
            } else {
                updateAnswerResult();
                questionPosition++;
                setCurrentQuestion();
            }
        });

        getAllQuestions();
    }

    private void getAllQuestions() {
        loadingDialog.display();
        FirebaseDatabase.getInstance().getReference().child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loadingDialog.dismiss();
                for (DataSnapshot questionSnapshot : snapshot.getChildren()) {
                    questionList.add(questionSnapshot.getValue(QuestionModel.class));
                }
                if (!questionList.isEmpty()) {
                    binding.studentRoot.setVisibility(View.VISIBLE);
                    questionPosition = 0;
                    setCurrentQuestion();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingDialog.dismiss();
                Toast.makeText(getBaseContext(), "Error fetching questions: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateAnswerResult() {
        QuestionModel questionModel = questionList.get(questionPosition);
        boolean isAnswerCorrect = questionModel.getAnswers().get(answerPosition).isCorrect();
        if (isAnswerCorrect) correctAnswers++;
    }

    private void setCurrentQuestion() {
        binding.rgOptionsAnswer.clearCheck();
        hasSelectAnswer = false;
        answerPosition = -1;
        QuestionModel questionModel = questionList.get(questionPosition);

        binding.tvQuestionCount.setText("Q: " + (questionPosition + 1) + "/" + questionList.size());
        binding.etQuestion.setText(questionModel.getQuestion());
        binding.etOption1.setText(questionModel.getAnswers().get(0).getAnswer());
        binding.etOption2.setText(questionModel.getAnswers().get(1).getAnswer());
        binding.etOption3.setText(questionModel.getAnswers().get(2).getAnswer());
        binding.etOption4.setText(questionModel.getAnswers().get(3).getAnswer());

        if (questionModel.isCode()) binding.tvOpenCode.setVisibility(View.VISIBLE);
        else binding.tvOpenCode.setVisibility(View.INVISIBLE);

        if (questionModel.isIs4Option()) {
            binding.etOption3.setVisibility(View.VISIBLE);
            binding.etOption4.setVisibility(View.VISIBLE);
            binding.rbOption3.setVisibility(View.VISIBLE);
            binding.rbOption4.setVisibility(View.VISIBLE);
        } else {
            binding.etOption3.setVisibility(View.GONE);
            binding.etOption4.setVisibility(View.GONE);
            binding.rbOption3.setVisibility(View.GONE);
            binding.rbOption4.setVisibility(View.GONE);
        }

    }

}