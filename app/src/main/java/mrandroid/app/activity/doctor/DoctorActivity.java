package mrandroid.app.activity.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mrandroid.app.activity.student.StudentActivity;
import mrandroid.app.adapter.QuestionsAdapter;
import mrandroid.app.databinding.ActivityDoctorBinding;
import mrandroid.app.util.LoadingDialog;

public class DoctorActivity extends AppCompatActivity implements QuestionsAdapter.OnItemClickListener {

    private ActivityDoctorBinding binding;
    private QuestionsAdapter adapter;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadingDialog = new LoadingDialog(this);
        adapter = new QuestionsAdapter();
        adapter.setListener(this);
        binding.rvQuestions.setAdapter(adapter);

        binding.fabStudent.setOnClickListener(view -> {
            startActivity(new Intent(getBaseContext(), StudentActivity.class));
        });

        binding.fabAdd.setOnClickListener(view -> {
            startActivity(new Intent(getBaseContext(), AddQuestionActivity.class));
        });

        getAllQuestions();
    }

    private void getAllQuestions() {
        loadingDialog.display();
        FirebaseDatabase.getInstance().getReference().child("questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loadingDialog.dismiss();
                List<String> questions = new ArrayList<>();
                for (DataSnapshot questionSnapshot : snapshot.getChildren()) {
                    questions.add(questionSnapshot.getKey());
                }
                adapter.setList(questions);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingDialog.dismiss();
                Toast.makeText(getBaseContext(), "Error fetching questions: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEditClick(String item) {

    }

    @Override
    public void onDeleteClick(String item) {
        loadingDialog.display();
        FirebaseDatabase.getInstance().getReference().child("questions").child(item).removeValue()
                .addOnSuccessListener(aVoid -> {
                    loadingDialog.dismiss();
                    Toast.makeText(getBaseContext(), "Question deleted successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    loadingDialog.dismiss();
                    Toast.makeText(getBaseContext(), "Error adding answer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}