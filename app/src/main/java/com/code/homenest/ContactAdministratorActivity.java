package com.code.homenest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactAdministratorActivity extends AppCompatActivity {


    Button btnSend;
    EditText To,Subject,Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_administrator_layout);

        btnSend=(Button)findViewById(R.id.send_email_button);
        To=(EditText)findViewById(R.id.etTO);
        Subject=(EditText)findViewById(R.id.etSubject);
        Message=(EditText)findViewById(R.id.etMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = To.getText().toString(); //getting input and converting it to String and keeping it in a variable.
                String subject = Subject.getText().toString();
                String message = Message.getText().toString(); //we can also give raw data if we don't want input

                Intent email = new Intent(Intent.ACTION_SEND);
                //putExtra for puuting it in bundle
                //EXTRA_EMAIL is for putting email id, String[] for array of string, because we can put more than one email id.
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.setType("message/rfc822"); //email type for only sending message
                //Intent.createChooser let you choose sending email client like gmail
                startActivity(Intent.createChooser(email,"Select Email Client"));
            }
        });
    }
}
