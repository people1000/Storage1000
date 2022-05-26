package com.example.myapplication.StateAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Database.DatabaseAdapter;

import com.example.myapplication.EditPostActivity;
import com.example.myapplication.R;
import com.example.myapplication.WelcomeActivity;

import java.io.File;
import java.util.List;

public class HomeActivity2 extends AppCompatActivity {

    private ListView postList;
    String USER_NAME;

    private SecondAdapter adapter;

    //В методе onCreate() происходит создание объекта SQLiteOpenHelper. Сама инициализация объектов для работы с базой данных происходит в методе onResume(), который срабатывает после метода onCreate().
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        postList = findViewById(R.id.postsList);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

           /* // получаем выбранный пункт
                Post_inSecondAdapter selectedState = (Post_inSecondAdapter)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedState.getName(),
                        Toast.LENGTH_SHORT).show();*/
                Post_inSecondAdapter post = adapter.getItem(position);
                if (post != null) {
                    Intent intent = new Intent(getApplicationContext(), EditPostActivity.class);
                    intent.putExtra("id", post.getId());
                    startActivity(intent);
                }
            }
        };
        postList.setOnItemClickListener(itemListener);
    }
    @Override
    public void onResume() {
        super.onResume();
        inizialize(); //аполняем данные пользователя наверху странички

        DatabaseAdapter dataAdapter = new DatabaseAdapter(this);
        dataAdapter.open();

        List<Post_inSecondAdapter> posts = dataAdapter.getPosts();

        adapter = new SecondAdapter(this, R.layout.list_item, posts);
        postList.setAdapter(adapter);
        dataAdapter.close();
    }

    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void clickAdd(View view){
        Intent intent = new Intent(this, EditPostActivity.class);
        startActivity(intent);
    }

    public void inizialize(){
        //из предыдущей активити получаем логин пользователя
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();
        USER_NAME = name + ".txt";
        //вписываем логин в текущего пользователя
        TextView people = findViewById(R.id.user);
        people.setText("Пользователь: " + name);

        //getAvatar();
    }

   /* private final static String FILE_NAME = "document.txt";

    private File getExternalPath() {
        return new File(getExternalFilesDir(null), FILE_NAME);
    }

    public void openText(View view){

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
    }*/

    public void clickSignOut(View view) {
        //делаем видимым окно выхода
        findViewById(R.id.exit).setVisibility(View.VISIBLE);
    }

   /* public void clickAdd(View view) {
        Intent intent = new Intent(this, AddPostActivity.class);
        startActivity(intent);
    }*/

    public void exit_save_data(View view){
        //выходим и удаляем файл
//        deleteFile(USER_NAME); (для внутреннего хранилища)

        String dir = getExternalFilesDir(null).toString();
        new File(dir,USER_NAME).delete();

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void exit_not_save_data(View view){
        //просто выходим. Данные о пользователе остаются в памяти
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void getAvatar(){
        ImageView imageView = findViewById(R.id.avatar);
        imageView.setImageBitmap(BitmapFactory.decodeFile("/data/data/com.example.myapplication/files/avatarka.jpg"));
    }
}