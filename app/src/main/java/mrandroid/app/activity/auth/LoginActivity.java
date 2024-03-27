package mrandroid.app.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import mrandroid.app.R;
import mrandroid.app.activity.doctor.DoctorActivity;
import mrandroid.app.activity.student.StudentActivity;
import mrandroid.app.databinding.ActivityLoginBinding;
import mrandroid.app.util.Constants;
import mrandroid.app.util.LoadingDialog;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadingDialog = new LoadingDialog(this);

        binding.btnLogin.setOnClickListener(view -> {
            boolean isValid = checkValidation();
            if (isValid) loginToApp();
        });

        binding.tvSignUp.setOnClickListener(view -> {
            startActivity(new Intent(getBaseContext(), SignupActivity.class));
        });
    }

    private boolean checkValidation() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        boolean isEmpty = email.isEmpty() || password.isEmpty();
        if (isEmpty) {
            Toast.makeText(this, "Data is required!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // All is fine
        return true;
    }

    private void loginToApp() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        loadingDialog.display();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loadingDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Welcome! Login Successfully", Toast.LENGTH_LONG).show();

                        Constants.IS_Doctor =
                                (binding.radioGroup.getCheckedRadioButtonId() == R.id.rbDoctor);

                        if (Constants.IS_Doctor) startActivity(new Intent(getBaseContext(), DoctorActivity.class));
                        else startActivity(new Intent(getBaseContext(), StudentActivity.class));
                        finish();
                    } else {
                        loadingDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Failed to Login", Toast.LENGTH_LONG).show();
                    }
                });
    }
}