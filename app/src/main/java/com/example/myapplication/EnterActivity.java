package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.StateAdapter.HomeActivity2;

public class EnterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
    }
    public void enterCheck(View view) {
        EditText login = findViewById(R.id.login);
        EditText password = findViewById(R.id.password);
        String loginInput = login.getText().toString();
        String passwordInput = password.getText().toString();

        if (loginInput.equals("1") && (passwordInput.equals("1"))){
            Intent intent = new Intent(this, HomeActivity2.class);
            //           Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("name", loginInput);
            startActivity(intent);
        }
        else {
            Toast toast = Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_LONG);
            toast.show();
            login.setText("");
            password.setText("");
        }

    }
    public void clickOnBack(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
