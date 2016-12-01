package com.app.interconnected;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private EditText fullnameText, usernameText, emailText, passwordText;
    private Button btn_register;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this);

        fullnameText = (EditText) findViewById(R.id.editText5);
        usernameText = (EditText) findViewById(R.id.editText4);
        emailText = (EditText) findViewById(R.id.editText3);
        passwordText = (EditText) findViewById(R.id.editText6);
        btn_register = (Button) findViewById(R.id.button2);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveUserInformation(){
        final String fullname = fullnameText.getText().toString().trim();
        final String username = usernameText.getText().toString().trim();
        final String email = emailText.getText().toString().trim();
        final String password = passwordText.getText().toString().trim();

        if(TextUtils.isEmpty(fullname)){
            fullnameText.setError("Required");
            return;
        }
        if(TextUtils.isEmpty(username)){
            usernameText.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            emailText.setError("Required");
            return;
        }
        if(TextUtils.isEmpty(password)){
            passwordText.setError("Required");
            return;
        }
        if (password.length() < 6) {
            progressDialog.cancel();
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            progressDialog.cancel();
                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            UserInformation userInformation = new UserInformation(fullname, username, email);

                            FirebaseUser user = mAuth.getCurrentUser();

                            databaseReference.child(user.getUid()).setValue(userInformation);
                            mAuth.signOut();
                            finish();
                            Toast.makeText(RegisterActivity.this, "Your account successfully registered",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
