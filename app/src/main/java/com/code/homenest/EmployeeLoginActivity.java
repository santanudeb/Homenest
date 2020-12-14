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

public class EmployeeLoginActivity extends AppCompatActivity {

    EditText etemployeeenteremail, etemployeeenterpassword;
    Button btnemployeelogin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_login_layout);

        etemployeeenteremail=(EditText)findViewById(R.id.etemployeeenteremail);
        etemployeeenterpassword=(EditText)findViewById(R.id.etemployeeenterpassword);
        btnemployeelogin=(Button)findViewById(R.id.btnemployeelogin);
        mFirebaseAuth=FirebaseAuth.getInstance();

        btnemployeelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=etemployeeenteremail.getText().toString().trim();
                String password=etemployeeenterpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    etemployeeenteremail.setError("Email needed");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    etemployeeenterpassword.setError("Password needed");
                    return;
                }

                //Auth
                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(EmployeeLoginActivity.this, "Logged In", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), EmployeeDashBoardActivity.class));
                        }
                        else {
                            Toast.makeText(EmployeeLoginActivity.this, "Log In Error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
