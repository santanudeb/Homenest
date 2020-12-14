package com.code.homenest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminDashBoardActivity extends AppCompatActivity {

    Button complaintsadmin, viewemployees, logoutadmin, aboutusadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dash_board_layout);

        complaintsadmin=(Button)findViewById(R.id.btn_complaints_admin);
        viewemployees=(Button)findViewById(R.id.btn_view_employees);
        //editcomplaints=(Button)findViewById(R.id.btn_edit_complaints);
        logoutadmin=(Button)findViewById(R.id.btn_log_out_admin);
        aboutusadmin=(Button)findViewById(R.id.btn_about_us_admin);

        complaintsadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminDashBoardActivity.this, AdminComplaintsActivity.class);
                startActivity(i);
            }
        });

        viewemployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminDashBoardActivity.this, AdminViewEmployees.class);
                startActivity(i);
            }
        });

        /*
        editcomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminDashBoardActivity.this, AdminEditComplaintsActivity.class);
                startActivity(i);
            }
        });

         */
        aboutusadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminDashBoardActivity.this, AboutUsActivity.class);
                startActivity(i);
            }
        });

        logoutadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminDashBoardActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
                Intent i=new Intent(AdminDashBoardActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
