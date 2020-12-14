package com.code.homenest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class EmployeeRegisterActivity extends AppCompatActivity {

    EditText etemployeename, etemployeephone, etemployeeemail, etemployeepassword;
    RadioGroup rgtype;
    RadioButton rbtypeelectrician, rbtypejanitor, rbtypeplumber;
    Button btnemployeeregister;
    String type="";
    DatabaseReference reff;
    FirebaseAuth mFirebaseAuth;
    Employee employee;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_register_layout);

        etemployeename=(EditText)findViewById(R.id.etemployeename);
        etemployeephone=(EditText)findViewById(R.id.etemployeephone);
        etemployeeemail=(EditText)findViewById(R.id.etemployeeemail);
        etemployeepassword=(EditText)findViewById(R.id.etemployeepassword);
        rgtype=(RadioGroup)findViewById(R.id.rgtype);
        rbtypeelectrician=(RadioButton)findViewById(R.id.rbtypeelectrician);
        rbtypejanitor=(RadioButton)findViewById(R.id.rbtypejanitor);
        rbtypeplumber=(RadioButton)findViewById(R.id.rbtypeplumber);
        btnemployeeregister=(Button)findViewById(R.id.btnemployeeregister);
        employee=new Employee();
        reff= FirebaseDatabase.getInstance().getReference().child("Employee");
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

        //Radio check
        rgtype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbtypeelectrician.isChecked())
                {
                    type="electrician";
                }
                if (rbtypejanitor.isChecked())
                {
                    type="janitor";
                }
                if (rbtypeplumber.isChecked())
                {
                    type="plumber";
                }
            }
        });

        //Register: entering value
        btnemployeeregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Long phone=Long.parseLong(etemployeephone.getText().toString().trim()); //converting to string

                //error check
                String employeename=etemployeename.getText().toString().trim();
                String employeephoneno=etemployeephone.getText().toString().trim();
                String employeeemail=etemployeeemail.getText().toString().trim();
                String employeepassword=etemployeepassword.getText().toString().trim();
                String employeetype=rgtype.toString().trim();

                if (TextUtils.isEmpty(employeename)){
                    etemployeename.setError("Name needed");
                    return;
                }

                if (TextUtils.isEmpty(employeephoneno)){
                    etemployeephone.setError("Phone needed");
                    return;
                }

                if (TextUtils.isEmpty(employeeemail)){
                    etemployeeemail.setError("Email needed");
                    return;
                }

                if (TextUtils.isEmpty(employeepassword)){
                    etemployeepassword.setError("Password needed");
                    return;
                }

                if (TextUtils.isEmpty(employeetype)){
                    Toast.makeText(EmployeeRegisterActivity.this, "Select Type", Toast.LENGTH_LONG).show();
                    return;
                }

                mFirebaseAuth.createUserWithEmailAndPassword(employeeemail,employeepassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(EmployeeRegisterActivity.this, "Employee Created", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(EmployeeRegisterActivity.this, "Employee Not Created" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

                //sending value to User class
                employee.setName(etemployeename.getText().toString().trim());
                employee.setEmail(etemployeeemail.getText().toString().trim());
                employee.setPhone(etemployeephone.getText().toString().trim());
                employee.setPassword(etemployeepassword.getText().toString().trim());
                employee.setType(type);

                //increasing maxid
                reff.child(String.valueOf(maxid+1)).setValue(employee);
                Toast.makeText(EmployeeRegisterActivity.this, "Employee Registration Successful", Toast.LENGTH_LONG).show();
                Intent i=new Intent(EmployeeRegisterActivity.this, EmployeeLoginActivity.class);
                startActivity(i);
            }
        });
    }
}
