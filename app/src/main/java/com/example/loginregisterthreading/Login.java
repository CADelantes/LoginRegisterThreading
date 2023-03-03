package com.example.loginregisterthreading;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText user, pass;
    Button signin;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.MessageTextView);
        signin = findViewById(R.id.SigninButton);
        user = findViewById(R.id.UserEditText);
        pass = findViewById(R.id.PassEditText);

        signin.setOnClickListener(v -> {
            UserAccount userAcc = new UserAccount();

            if(!user.getText().toString().trim().isEmpty()&&!pass.getText().toString().trim().isEmpty()){
                userAcc.searchUser(user.getText().toString(), pass.getText().toString(), this);
            }else{
                Toast.makeText(Login.this,"Make sure there are no empty fields", Toast.LENGTH_SHORT).show();
            }
        });

        message.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this,Register.class);
            startActivity(intent);
        });
    }
}