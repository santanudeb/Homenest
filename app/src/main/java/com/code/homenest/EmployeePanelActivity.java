package com.code.homenest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeePanelActivity extends AppCompatActivity {

    Button btneplogin, btnepregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_panel);

        btneplogin=(Button)findViewById(R.id.btneplogin);
        btnepregister=(Button)findViewById(R.id.btnepregister);

        btneplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EmployeePanelActivity.this, EmployeeLoginActivity.class);
                startActivity(i);
            }
        });

        btnepregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EmployeePanelActivity.this, EmployeeRegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
