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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    EditText etname, etemail, etphone, etpassword, etflatno;
    Button btnregister;
    DatabaseReference reff;
    FirebaseAuth mFirebaseAuth;
    User user;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        etname=(EditText)findViewById(R.id.etname);
        etemail=(EditText)findViewById(R.id.etemail);
        etphone=(EditText)findViewById(R.id.etphone);
        etpassword=(EditText)findViewById(R.id.etpassword);
        etflatno=(EditText)findViewById(R.id.etflatno);
        btnregister=(Button)findViewById(R.id.btnregister);
        user=new User();
        reff=FirebaseDatabase.getInstance().getReference().child("User");
        mFirebaseAuth=FirebaseAuth.getInstance();

        //checking maxid
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxid=(dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Register: entering value
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //error check
                String name=etname.getText().toString().trim();
                String phoneno=etphone.getText().toString().trim();
                String email=etemail.getText().toString().trim();
                String password=etpassword.getText().toString().trim();
                String flatno=etflatno.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    etname.setError("Name needed");
                    return;
                }

                if (TextUtils.isEmpty(phoneno)){
                    etphone.setError("Phone Number needed");
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    etemail.setError("Email needed");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    etpassword.setError("Password needed");
                    return;
                }

                if (TextUtils.isEmpty(flatno)){
                    etflatno.setError("Flat Number needed");
                    return;
                }

                //converting to string
                Long phone=Long.parseLong(etphone.getText().toString().trim());

                //Auth
                mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "User Not Created" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

                //sending value to User class
                user.setName(etname.getText().toString().trim());
                user.setEmail(etemail.getText().toString().trim());
                user.setPhone(phone);
                user.setPassword(etpassword.getText().toString().trim());
                user.setFlatNo(etflatno.getText().toString().trim());

                //increasing maxid
                reff.child(String.valueOf(maxid+1)).setValue(user);
                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                Intent i=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}


