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

public class AdminRegisterActivity extends AppCompatActivity {

    EditText etadminname, etadminphone, etadminemail, etadminpassword;
    Button btnadminregister;
    DatabaseReference reff;
    FirebaseAuth mFirebaseAuth;
    Admin admin;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_register_layout);

        etadminname=(EditText)findViewById(R.id.etadminname);
        etadminphone=(EditText)findViewById(R.id.etadminphone);
        etadminemail=(EditText)findViewById(R.id.etadminemail);
        etadminpassword=(EditText)findViewById(R.id.etadminpassword);
        btnadminregister=(Button)findViewById(R.id.btnadminregister);
        admin=new Admin();
        reff= FirebaseDatabase.getInstance().getReference().child("Admin");
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
        btnadminregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //error check
                String adminname=etadminname.getText().toString().trim();
                String adminphoneno=etadminphone.getText().toString().trim();
                String adminemail=etadminemail.getText().toString().trim();
                String adminpassword=etadminpassword.getText().toString().trim();

                if (TextUtils.isEmpty(adminname)){
                    etadminname.setError("Name needed");
                    return;
                }

                if (TextUtils.isEmpty(adminphoneno)){
                    etadminphone.setError("Phone needed");
                    return;
                }

                if (TextUtils.isEmpty(adminemail)){
                    etadminemail.setError("Email needed");
                    return;
                }

                if (TextUtils.isEmpty(adminpassword)){
                    etadminpassword.setError("Password needed");
                    return;
                }

                //converting to string
                Long phone=Long.parseLong(etadminphone.getText().toString().trim());

                //Auth
                mFirebaseAuth.createUserWithEmailAndPassword(adminemail,adminpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AdminRegisterActivity.this, "Admin Created", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(AdminRegisterActivity.this, "Admin Not Created" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

                //sending value to User class
                admin.setName(etadminname.getText().toString().trim());
                admin.setEmail(etadminemail.getText().toString().trim());
                admin.setPhone(phone);
                admin.setPassword(etadminpassword.getText().toString().trim());

                //increasing maxid
                reff.child(String.valueOf(maxid+1)).setValue(admin);
                Toast.makeText(AdminRegisterActivity.this, "Admin Registration Successful", Toast.LENGTH_LONG).show();
                Intent i=new Intent(AdminRegisterActivity.this, AdminLoginActivity.class);
                startActivity(i);
            }
        });
    }
}
