package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.DatabaseAdapter;
import com.example.myapplication.StateAdapter.HomeActivity2;
import com.example.myapplication.StateAdapter.Post_inSecondAdapter;

public class EditPostActivity extends AppCompatActivity {
    private EditText nameBox;
    private EditText dateBox;
    private Button deleteButton;

    private DatabaseAdapter adapter;
    private long postId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        nameBox = findViewById(R.id.name);
        dateBox = findViewById(R.id.date);
        deleteButton = findViewById(R.id.delete);
        adapter = new DatabaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            postId = extras.getLong("id");
        }
        // если 0, то добавление
        if (postId > 0) {
            // получаем элемент по id из бд
            adapter.open();
            Post_inSecondAdapter user = adapter.getPost(postId);
            nameBox.setText(user.getName());
            dateBox.setText(user.getDate());
            adapter.close();
            deleteButton.setVisibility(View.VISIBLE);
        }
    }
    public void clickSavePost(View view){
        String name = nameBox.getText().toString();
        String date = dateBox.getText().toString();
        String img = "";
        Post_inSecondAdapter post = new Post_inSecondAdapter(postId, name, date, img);

        adapter.open();
        if (postId > 0) {
            adapter.update(post);
        } else {
            adapter.insert(post);
        }
        adapter.close();
        goBack();
    }
    public void clickDeletePost(View view){
        adapter.open();
        adapter.delete(postId);
        adapter.close();
        goBack();
    }

    public void clickOnBackInHome(View view){
        goBack();
    }

    private void goBack(){
        // переход к главной activity
        Intent intent = new Intent(this, HomeActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}