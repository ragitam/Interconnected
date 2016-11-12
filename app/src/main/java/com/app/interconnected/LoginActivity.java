package com.app.interconnected;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText ema, passwd;
    private Button signIn;
    private TextView btn_register;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        ema = (EditText) findViewById(R.id.email);
        passwd = (EditText) findViewById(R.id.editText2);
        signIn = (Button) findViewById(R.id.button);
        btn_register = (TextView) findViewById(R.id.textView);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ema.getText().toString().trim();
                final String password = passwd.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    ema.setError("Enter email address!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwd.setError("Enter password!");
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(LoginActivity.this, getString(R.string.minimum_password), Toast.LENGTH_SHORT).show();
                }

                progressDialog.setMessage("Please Wait");
                progressDialog.show();

                //authenticate user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    progressDialog.cancel();
                                    progressDialog.hide();
                                    Toast.makeText(LoginActivity.this, "Log In Failed, Please Try Again", Toast.LENGTH_SHORT).show();
                                } else {
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }
                            }
                        });
            }
        });
    }
}
