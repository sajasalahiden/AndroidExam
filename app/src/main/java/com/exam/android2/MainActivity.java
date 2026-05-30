package com.exam.android2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button   btnLogin;

    private boolean backPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail    = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin   = findViewById(R.id.btn_login);

        btnLogin.setEnabled(false);

        etEmail.addTextChangedListener(mFieldWatcher);
        etPassword.addTextChangedListener(mFieldWatcher);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModeActivity.class);
            startActivity(intent);
        });
    }

    private final TextWatcher mFieldWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* unused */ }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { /* unused */ }

        @Override
        public void afterTextChanged(Editable s) {
            validateFields();
        }
    };


    private void validateFields() {
        String email    = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        boolean emailOk    = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean passwordOk = password.length() >= 6;

        btnLogin.setEnabled(emailOk && passwordOk);
    }


    @Override
    public void onBackPressed() {
        if (backPressedOnce) {
            // Second press — finish the app.
            super.onBackPressed();
            return;
        }

        // First press — warn the user.
        backPressedOnce = true;

        View rootView = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(
                rootView,
                getString(R.string.snackbar_exit_message),
                Snackbar.LENGTH_SHORT
        );

        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                backPressedOnce = false;
            }
        });

        snackbar.show();
    }
}
