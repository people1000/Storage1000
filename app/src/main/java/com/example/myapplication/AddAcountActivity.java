package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AddAcountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acount);
    }

    //взять данные с поля логин, пароль
    //проверить, есть ли файл с названием как введенный логин
    //записать пароль в файл
    //потом при входе считывать с этого файла
    //файл называть по логину
    //в файле хранить пароль

    EditText login;
    EditText password;


    //Вы можете запросить состояния внешнего хранилища, вызвав getExternalStorageState(). Если возвращенное состояние равно MEDIA_MOUNTED, то вы можете читать и писать файлы. Например, следующие методы полезны для определения доступности хранилища:
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    //внешнее хранилище
    public void button_new_account(View view) throws IOException {
        login = findViewById(R.id.login_new);
        password = findViewById(R.id.password_new);

        //Получаем логин
        String loginInput = login.getText().toString();
        //Получаем пароль
        String passwordInput = password.getText().toString();
        //название файла - логин пользователя
        String FILE_NAME = loginInput + ".txt";

        //проверяем, доступно ли внешнее хранилище для записи и чтения
        if (isExternalStorageWritable() && isExternalStorageReadable()){
            System.out.println("Хранилище доступно");

            // путь к месту, где хранятся файлы
            String dir = getExternalFilesDir(null).toString();

            System.out.println("Путь к внешнему хранилищу "+dir);

            //проверяем, есть ли уже такой пользователь
            // вызываем метод поиска файлов с таким же названием
            if (findFiles(dir, FILE_NAME)){

                //поток для записи в файл
                FileOutputStream fos = null;

                try {
                    fos = new FileOutputStream(new File(dir,FILE_NAME));

                    System.out.println(new File(dir,FILE_NAME));

                    //записываем пароль
                    fos.write(passwordInput.getBytes());

                    Toast.makeText(this, "Пользователь сохранен", Toast.LENGTH_SHORT).show();
                }
                catch(IOException ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(ex.getMessage());
                }
                finally{
                    try{
                        if(fos!=null)
                            fos.close();
                    }
                    catch(IOException ex){
                        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else{
                login.setText("");
                password.setText("");
            }
        }
        else{
            System.out.println("Хранилище не доступно");
        }
    }


    //внутреннее хранилище
  /*  public void button_new_account(View view) throws IOException {

        login = findViewById(R.id.login_new);
        password = findViewById(R.id.password_new);

        //Получаем логин
        String loginInput = login.getText().toString();
        //Получаем пароль
        String passwordInput = password.getText().toString();

        //название файла - логин пользователя
        String FILE_NAME = loginInput + ".txt";

        //проверяем, есть ли уже такой пользователь

        // путь к месту, где хранятся файлы
        String dir = "/data/data/com.example.myapplication/files";
        // вызываем метод поиска файлов с таким же названием

        if (findFiles(dir, FILE_NAME)){

            //поток для записи в файл
            FileOutputStream fos = null;

            try {
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                //MODE_PRIVATE: файлы могут быть доступны только владельцу приложения (режим по умолчанию)
                //MODE_APPEND: данные могут быть добавлены в конец файла

                //записываем пароль
                fos.write(passwordInput.getBytes());
                Toast.makeText(this, "Пользователь сохранен", Toast.LENGTH_SHORT).show();
            }
            catch(IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
            finally{
                try{
                    if(fos!=null)
                        fos.close();
                }
                catch(IOException ex){
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            login.setText("");
            password.setText("");
        }

    }*/

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
            File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
            if (listFiles.length == 0) {
                System.out.println(dir + " не содержит файлов с расширением " + ext);
            }
            else {
                for (File f : listFiles) {
                    if (f.getName().equals(name_file)) {
                        Toast toast = Toast.makeText(this, "Пользователь уже существует", Toast.LENGTH_LONG);
                        toast.show();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Реализация интерфейса FileNameFilter
    public static class MyFileNameFilter implements FilenameFilter {
        private String ext;
        //чтобы игнорировать регистр расгирения
        public MyFileNameFilter(String ext){
            this.ext = ext.toLowerCase();
        }
        //поиск файлов с нужным расширением
        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(ext);
        }
    }
}