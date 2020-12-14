package com.code.homenest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserDashBoardActivity extends AppCompatActivity {

    Button addcomplaints, contactadmin, aboutus, logout, questionsanswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_dash_board_layout);

        addcomplaints=(Button)findViewById(R.id.btn_add_complaints);
        contactadmin=(Button)findViewById(R.id.btn_contact_administrator);
        questionsanswers=(Button)findViewById(R.id.btn_questions_answers);
        aboutus=(Button)findViewById(R.id.btn_about_us);
        logout=(Button)findViewById(R.id.btn_log_out);

        addcomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserDashBoardActivity.this, AddComplaintsActivity.class);
                startActivity(i);
            }
        });

        contactadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserDashBoardActivity.this, ContactAdministratorActivity.class);
                startActivity(i);
            }
        });

        questionsanswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserDashBoardActivity.this, QuestionsAnswersActivity.class);
                startActivity(i);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserDashBoardActivity.this, AboutUsActivity.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserDashBoardActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
                Intent i=new Intent(UserDashBoardActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
