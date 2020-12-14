package com.code.homenest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {

    EditText etadminenteremail, etadminenterpassword;
    Button btnadminlogin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_layout);

        etadminenteremail=(EditText)findViewById(R.id.etadminenteremail);
        etadminenterpassword=(EditText)findViewById(R.id.etadminenterpassword);
        btnadminlogin=(Button)findViewById(R.id.btnadminlogin);
        mFirebaseAuth=FirebaseAuth.getInstance();

        btnadminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=etadminenteremail.getText().toString().trim();
                String password=etadminenterpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    etadminenteremail.setError("Email needed");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    etadminenterpassword.setError("Password needed");
                    return;
                }

                //Auth
                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AdminLoginActivity.this, "Logged In", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), AdminDashBoardActivity.class));
                        }
                        else {
                            Toast.makeText(AdminLoginActivity.this, "Log In Error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
