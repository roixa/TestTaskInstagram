package com.roix.testtaskinstagram.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roix.testtaskinstagram.R;
import com.roix.testtaskinstagram.pojo.Comment;

import java.util.List;

/**
 * Created by u5 on 10/14/16.
 */
public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.CommentsViewHolder>{
    private List<Comment> comments;
    private Context context;

    public CommentsListAdapter(Context context,List<Comment> comments){
        this.context=context;
        this.comments=comments;
    }

    @Override
    public CommentsListAdapter.CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int pos) {
        holder.textView1.setText(comments.get(pos).getFrom().getUsername());
        holder.textView2.setText(comments.get(pos).getText());
    }

    @Override
    public int getItemCount() {
        if(comments!=null)
            return comments.size();
        return 0;
    }

    public static class CommentsViewHolder extends RecyclerView.ViewHolder{
        public TextView textView1;
        public TextView textView2;

        public CommentsViewHolder(View view) {
            super(view);
            textView1=(TextView)view.findViewById(R.id.text1);
            textView2=(TextView)view.findViewById(R.id.text2);

        }

    }

}
