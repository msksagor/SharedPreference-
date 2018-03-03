package com.example.fondn.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private  EditText name,mail;
    private Button submit,retrive;
    private TextView nameTextView,mailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.userEditTextNameID);
        mail = (EditText) findViewById(R.id.userEditTextMailID);

        submit = (Button) findViewById(R.id.submitButtonID);
        retrive = (Button) findViewById(R.id.RetriveButtonID);

        nameTextView = (TextView) findViewById(R.id.userTextID);
        mailTextView = (TextView) findViewById(R.id.mailTextID);

        submit.setOnClickListener(this);
        retrive.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.submitButtonID){
            String nameString  = name.getText().toString();
            String mailString = mail.getText().toString();
            if(nameString.equals("") && mailString.equals("")){
                Toast.makeText(getApplicationContext(), "please input data", Toast.LENGTH_SHORT).show();

            }
            else {
                //Store Data
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NameKey",nameString);
                editor.putString("MailKey",mailString);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Successfully Data Stored....", Toast.LENGTH_SHORT).show();

            }

        }
        else if(v.getId()==R.id.RetriveButtonID){
            //retrive data from SharedPreference
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
            if(sharedPreferences.contains("NameKey")&& sharedPreferences.contains("MailKey")){
                String nameStringRetrive = sharedPreferences.getString("NameKey","Data not found");
                String mailStringRetrive = sharedPreferences.getString("MailKey","Data not found");

                nameTextView.setText(nameStringRetrive);
                mailTextView.setText(mailStringRetrive);

            }

        }
    }
}
