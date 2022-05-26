package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {
    private final static String FILE_NAME = "document.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        openText();
    }
    private File getExternalPath() {
        return new File(getExternalFilesDir(null), FILE_NAME);
    }

    public void openText(){
        TextView textView = findViewById(R.id.text);
        File file = getExternalPath();
        // если файл не существует, выход из метода
        if(!file.exists()) return;
        try(FileInputStream fin =  new FileInputStream(file)) {
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            System.out.println(file.getAbsolutePath());
            textView.setText(text);
        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void edit(View view) {
        Button save = findViewById(R.id.save_text);
        save.setVisibility(View.VISIBLE);
        EditText editor =  findViewById(R.id.editor);
        editor.setVisibility(View.VISIBLE);
    }
    // сохранение файла
    public void saveText(View view){
        try(FileOutputStream fos = new FileOutputStream(getExternalPath())) {
            EditText textBox = findViewById(R.id.editor);
            String text = textBox.getText().toString();
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
            textBox.setText("");
        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        openText();
        Button save = findViewById(R.id.save_text);
        save.setVisibility(View.INVISIBLE);
        EditText editor =  findViewById(R.id.editor);
        editor.setVisibility(View.INVISIBLE);
    }

    private final static String USER_NAME = "user.txt";

    public void checkEnter(){
        //поток для чтения из файла
        FileInputStream fin = null;
        //поле для вывода текста из файла
        TextView textView = findViewById(R.id.userName);
        try {
            fin = openFileInput(USER_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            textView.setText(text);
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
    }
}