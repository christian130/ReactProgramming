/*
 * RxSamples- A java/apk RxSamples for android
 * Copyright (C) 2020 @christian130a
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.christian130.rxsamples.models.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.christian130.rxsamples.R;
import com.christian130.rxsamples.models.DTO.Post;

import java.util.ArrayList;
import java.util.List;

public class MainRecylerAdapter extends RecyclerView.Adapter<MainRecylerAdapter.MyAnotherClass> {
    private static final String TAG = "RecyclerAdapter";

    private List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public MyAnotherClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_list_item, null, false);
        return new MyAnotherClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAnotherClass holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void updatePost(Post post) {
        posts.set(posts.indexOf(post), post);
        notifyItemChanged(posts.indexOf(post));
    }
    public List<Post> getPosts(){
        return posts;
    }

    public class MyAnotherClass extends RecyclerView.ViewHolder {
        TextView title, numComments;
        ProgressBar progressBar;

        public MyAnotherClass(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            numComments = itemView.findViewById(R.id.num_comments);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
        public void bind(Post post){
            title.setText(post.getTitle());

            if(post.getComments() == null){
                showProgressBar(true);
                numComments.setText("");
            }
            else{
                showProgressBar(false);
                numComments.setText(String.valueOf(post.getComments().size()));
            }

            
            
        }

        private void showProgressBar(boolean b) {
            if(b) {
                progressBar.setVisibility(View.VISIBLE);
            }
            else{
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
