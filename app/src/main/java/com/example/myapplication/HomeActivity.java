package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ArrayList<Post> posts = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.list);
    // создаем адаптер
        PostAdapter adapter = new PostAdapter(this, posts);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        //из предыдущей активити получаем логин пользователя
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();
        //вписываем логин в текущего пользователя
        TextView people = findViewById(R.id.user);
        people.setText("Пользователь: " + name);
    }
    private void setInitialData(){
        posts.add(new Post ("Первый пост", "12.01.2022"));
        posts.add(new Post ("Второй пост", "15.01.2022"));
        posts.add(new Post ("Третий пост", "17.01.2022"));
        posts.add(new Post ("Четвертый пост", "21.01.2022"));
        posts.add(new Post ("Пятый пост", "25.01.2022"));
        posts.add(new Post ("Шестой пост", "30.01.2022"));
        posts.add(new Post ("Седьмой пост", "01.02.2022"));
        posts.add(new Post ("Восьмой пост", "10.02.2022"));
    }


    public void clickSignOut(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
