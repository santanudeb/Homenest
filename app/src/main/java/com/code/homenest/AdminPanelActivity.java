package com.code.homenest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanelActivity extends AppCompatActivity {

    Button btnaplogin, btnapregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel_layout);

        btnaplogin=(Button)findViewById(R.id.btnaplogin);
        btnapregister=(Button)findViewById(R.id.btnapregister);

        btnaplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminPanelActivity.this, AdminLoginActivity.class);
                startActivity(i);
            }
        });

        btnapregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminPanelActivity.this, AdminRegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
