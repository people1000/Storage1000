package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Post> posts;

    PostAdapter(Context context, List<Post> states) {
        this.posts = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder holder, int position) {
        Post state = posts.get(position);
        holder.imgView.setImageResource(state.getImg());
        holder.nameView.setText(state.getName());
        holder.dateView.setText(state.getDate());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgView;
        final TextView nameView, dateView;
        ViewHolder(View view){
            super(view);
            imgView = view.findViewById(R.id.img);
            nameView = view.findViewById(R.id.name);
            dateView = view.findViewById(R.id.date);
        }
    }
}
