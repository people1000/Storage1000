package com.example.myapplication.StateAdapter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Post;
import com.example.myapplication.R;

import java.util.ArrayList;

public class HomeActivity2 extends AppCompatActivity {

    ArrayList<Post_inSecondAdapter> posts = new ArrayList<Post_inSecondAdapter>();
    ListView postList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        //из предыдущей активити получаем логин пользователя
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();
        //вписываем логин в текущего пользователя
        TextView people = findViewById(R.id.user);
        people.setText("Пользователь: " + name);

        // начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
        postList = findViewById(R.id.postsList);
        // создаем адаптер1
        SecondAdapter adapter = new SecondAdapter(this, R.layout.list_item, posts);
        // устанавливаем адаптер
        postList.setAdapter(adapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                Post_inSecondAdapter selectedState = (Post_inSecondAdapter)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedState.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        postList.setOnItemClickListener(itemListener);
    }
    private void setInitialData(){
        posts.add(new Post_inSecondAdapter ("Первый пост", "12.01.2022", R.drawable.second));
        posts.add(new Post_inSecondAdapter ("Второй пост", "15.01.2022", R.drawable.first));
        posts.add(new Post_inSecondAdapter ("Третий пост", "17.01.2022",  R.drawable.firth));
        posts.add(new Post_inSecondAdapter ("Четвертый пост", "21.01.2022",  R.drawable.five));
        posts.add(new Post_inSecondAdapter ("Пятый пост", "25.01.2022",  R.drawable.fourth));
        posts.add(new Post_inSecondAdapter ("Шестой пост", "30.01.2022",  R.drawable.first));
        posts.add(new Post_inSecondAdapter ("Седьмой пост", "01.02.2022",  R.drawable.second));
        posts.add(new Post_inSecondAdapter ("Восьмой пост", "10.02.2022",  R.drawable.firth));}
}