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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText etenteremail,etenterpassword;
    Button btnlogin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        etenteremail=(EditText)findViewById(R.id.etenteremail);
        etenterpassword=(EditText)findViewById(R.id.etenterpassword);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        mFirebaseAuth=FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //error check
                String email=etenteremail.getText().toString().trim();
                String password=etenterpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    etenteremail.setError("Email needed");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    etenterpassword.setError("Password needed");
                    return;
                }

                //Auth
                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), UserDashBoardActivity.class));
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Log In Error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
