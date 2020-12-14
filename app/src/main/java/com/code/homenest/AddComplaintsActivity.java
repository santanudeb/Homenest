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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddComplaintsActivity extends AppCompatActivity {

    EditText etphone, etflatno;
    RadioGroup rgneed;
    RadioButton rbelectrician, rbjanitor, rbplumber;
    Button btnaddcomplaints;
    String needs="";
    String status="open";
    DatabaseReference reff;
    Complaints complaints;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_complaints_layout);

        etphone=(EditText)findViewById(R.id.etphone);
        etflatno=(EditText)findViewById(R.id.etflatno);
        rgneed=(RadioGroup)findViewById(R.id.rgneed);
        rbelectrician=(RadioButton)findViewById(R.id.rbelectrician);
        rbjanitor=(RadioButton)findViewById(R.id.rbjanitor);
        rbplumber=(RadioButton)findViewById(R.id.rbplumber);
        btnaddcomplaints=(Button)findViewById(R.id.btnaddcomplaints);
        complaints=new Complaints();
        reff=FirebaseDatabase.getInstance().getReference().child("Complaints");

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
        rgneed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbelectrician.isChecked())
                {
                    needs="electrician";
                }
                if (rbjanitor.isChecked())
                {
                    needs="janitor";
                }
                if (rbplumber.isChecked())
                {
                    needs="plumber";
                }
            }
        });

        //Button
        btnaddcomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Long phone=Long.parseLong(etphone.getText().toString().trim());

                //error check
                String phoneno=etphone.getText().toString().trim();
                String flatno=etflatno.getText().toString().trim();
                String needsno=rgneed.toString().trim();

                if (TextUtils.isEmpty(phoneno)){
                    etphone.setError("Phone Number needed");
                    return;
                }

                if (TextUtils.isEmpty(flatno)){
                    etflatno.setError("Flat Number needed");
                    return;
                }

                //errorCheck();

                if (TextUtils.isEmpty(needsno)){
                    Toast.makeText(AddComplaintsActivity.this, "Select Need", Toast.LENGTH_LONG).show();
                    return;
                }

                //sending value to User class
                complaints.setPhone(etphone.getText().toString().trim());
                complaints.setFlatNo(etflatno.getText().toString().trim());
                complaints.setNeeds(needs);
                complaints.setStatus(status);

                //increasing maxid
                reff.child(String.valueOf(maxid+2)).setValue(complaints);
                Toast.makeText(AddComplaintsActivity.this, "Complain Added", Toast.LENGTH_LONG).show();
                Intent i=new Intent(AddComplaintsActivity.this, UserDashBoardActivity.class);
                startActivity(i);
            }


        });

    }

    public void errorCheck(){
        //radio group error check
        int isSelected=rgneed.getCheckedRadioButtonId();

        if (isSelected==-1){
            Toast.makeText(AddComplaintsActivity.this, "Select Need", Toast.LENGTH_LONG).show();
            return;
        }
    }

}
