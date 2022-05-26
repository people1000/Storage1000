package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.StateAdapter.HomeActivity2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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

        //поиск файла с таким же названием, как логин
        //название файла - логин пользователя
        String FILE_NAME = loginInput + ".txt";

        //проверяем, есть ли уже такой пользователь

        // путь к месту, где хранятся файлы, внутренне хранилище
        //  String dir = "/data/data/com.example.myapplication/files";

        // путь к месту, где хранятся файлы (внешнее хранилище)
        String dir = getExternalFilesDir(null).toString();
        // вызываем метод поиска файлов с таким же названием

        //если есть такой файл, то нужно проверить правильность пароля. в файле текст - это пароль
        if (!findFiles(dir, FILE_NAME)){
            if (passwordInput.equals(passwordText(dir, FILE_NAME))){
                Intent intent = new Intent(this, HomeActivity2.class);
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
        else{
            Toast toast = Toast.makeText(this, "Такого пользователя не существует", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public String passwordText(String dir, String file_name){
        //поток для чтения из файла
        FileInputStream fin = null;
        String password = "";
        try {
            fin = new FileInputStream(new File(dir,file_name));
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            password = new String (bytes);
        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if (fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return password;
    }

    public void clickOnBack(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    private final static String USER_NAME = "user.txt";

    // поиск файла с таким же названием
    private boolean findFiles(String dir, String name_file) {
        //расширение
        String ext = ".txt";
        File file = new File(dir);
        //проверяем путь на правильность
        if (!file.exists()) {
            System.out.println(dir + " папки не существует");
        }
        //если путь верный
        else {
            File[] listFiles = file.listFiles(new AddAcountActivity.MyFileNameFilter(ext));
            if (listFiles.length == 0) {
                System.out.println(dir + " не содержит файлов с расширением " + ext);

            }
            else {
                for (File f : listFiles) {
                    if (f.getName().equals(name_file)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
