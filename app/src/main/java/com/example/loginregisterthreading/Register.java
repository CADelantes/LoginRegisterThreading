package com.example.loginregisterthreading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText firstName,lastName,userName,password,confirmPass;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = findViewById(R.id.SignupButton);
        firstName = findViewById(R.id.FirstNameEditText);
        lastName = findViewById(R.id.LastNameEditText);
        userName = findViewById(R.id.UsernameEditText);
        password = findViewById(R.id.PasswordEditText);
        confirmPass = findViewById(R.id.ConfirmPasswordEditText);

        signup.setOnClickListener(v -> {
            UserAccount user = new UserAccount();

            String FName = firstName.getText().toString().trim();
            String LName = lastName.getText().toString().trim();
            String UName = userName.getText().toString().trim();
            String Pass = password.getText().toString().trim();
            String CPass = confirmPass.getText().toString().trim();

            if(!FName.isEmpty()&&!LName.isEmpty()&&!UName.isEmpty()&&!Pass.isEmpty()&&CPass.equals(Pass)){
                user.addUser(FName,LName,UName,Pass,CPass);
                Toast.makeText(Register.this, "Successfully added " + UName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }else{
                Toast.makeText(Register.this, "Make sure there are no empty fields or password are the same", Toast.LENGTH_SHORT).show();
            }
        });
    }
}