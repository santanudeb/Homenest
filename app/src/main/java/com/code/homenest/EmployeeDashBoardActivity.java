package com.code.homenest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EmployeeDashBoardActivity extends AppCompatActivity {

    Button complaintsemployee, contactadminemployee, logoutemployee, aboutusemployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_dash_board_layout);

        complaintsemployee=(Button)findViewById(R.id.btn_complaints_employee);
        contactadminemployee=(Button)findViewById(R.id.btn_contact_administrator_employee);
        logoutemployee=(Button)findViewById(R.id.btn_log_out_employee);
        aboutusemployee=(Button)findViewById(R.id.btn_about_us_employee);

        complaintsemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EmployeeDashBoardActivity.this, AdminComplaintsActivity.class);
                startActivity(i);
            }
        });

        contactadminemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EmployeeDashBoardActivity.this, ContactAdministratorActivity.class);
                startActivity(i);
            }
        });

        aboutusemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EmployeeDashBoardActivity.this, AboutUsActivity.class);
                startActivity(i);
            }
        });

        logoutemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EmployeeDashBoardActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
                Intent i=new Intent(EmployeeDashBoardActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
