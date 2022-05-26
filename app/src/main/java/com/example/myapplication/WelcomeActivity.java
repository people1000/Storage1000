package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.StateAdapter.HomeActivity2;

import java.io.FileInputStream;
import java.io.IOException;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    //вход
    public void enter(View view) {
        Intent intent = new Intent(this,
                EnterActivity.class);
        startActivity(intent);
    }

    //регистрация
    public void buttonNewAccount(View view){
        Intent intent = new Intent(this,
                AddAcountActivity.class);
        startActivity(intent);
    }


  /*  public void file(View view) {
        // получаем введенный возраст
        Intent intent = new Intent(this,
                FileActivity.class);
        startActivity(intent);
    }*/

    private final static String USER_NAME = "user.txt";
    private String USER;

//войти как
//    public void checkEnter(){
//        //поток для чтения из файла
//        FileInputStream fin = null;
//        //поле для вывода текста из файла
//        TextView textView = findViewById(R.id.userName);
//        try {
//
//            fin = openFileInput(USER_NAME);
//            byte[] bytes = new byte[fin.available()];
//            fin.read(bytes);
//            String text = new String (bytes);
//            //если файл не пустой, значит вход ранее выполнялся
//            /*if (!text.isEmpty()) {*/
//                Button enter = findViewById(R.id.enterLikeUser);
//                ImageView avatar = findViewById(R.id.avatar);
//                enter.setVisibility(View.VISIBLE);
//                avatar.setVisibility(View.VISIBLE);
//                enter.setText("Войти как " + text);
//                USER = text;
//            /*}
//            // если файл пустой, значит не отображаем "Вход как ..."
//            else{
//                System.out.println("файл пустой");
//                return;
//            }*/
//        }
//        catch(IOException ex) {
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        finally{
//            try{
//                if (fin!=null)
//                    fin.close();
//            }
//            catch(IOException ex){
//                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
   /* public void enterLike(View view){
        Intent intent = new Intent(this,
                HomeActivity2.class);
        intent.putExtra("name", USER);
        startActivity(intent);
    }*/

}
