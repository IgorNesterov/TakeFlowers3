package com.example.takeflowers3;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button toShops_button;
    private Button loginButton;

    public EditText mail = null;
    public EditText password = null;
    //public Switch lang_switch = null;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //super.onCreate(savedInstanceState);



        //setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        password = (EditText) findViewById(R.id.passwordText);
        mail = (EditText) findViewById(R.id.mailText);
        toShops_button = (Button) findViewById(R.id.toListButton);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void onShopsClick(View view) {
        Intent i;
        //i = new Intent(this, ListShops.class);
        i = new Intent(this, ListShops.class);
        startActivity(i);
    }

    public void onLoginClick(View view) {
        mAuth.signInWithEmailAndPassword(mail.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }
    public void updateUI(FirebaseUser in){
        if(in != null){
            toShops_button.setEnabled(true);
            loginButton.setVisibility(View.INVISIBLE);
            password.setVisibility(View.INVISIBLE);
            mail.setVisibility(View.INVISIBLE);
            toShops_button.setVisibility(View.VISIBLE);;
        }
        else{
            loginButton.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
            toShops_button.setVisibility(View.INVISIBLE);;
        }
    }
}

