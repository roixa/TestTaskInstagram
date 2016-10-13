package com.roix.testtaskinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.roix.testtaskinstagram.adapters.CommentsListAdapter;
import com.roix.testtaskinstagram.pojo.CommentsResponse;
import com.roix.testtaskinstagram.pojo.Photo;
import com.squareup.picasso.Picasso;

public class CommentsActivity extends AppCompatActivity {
    private ImageView  imageView;
    private TextView textView;
    private RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView=(ImageView)findViewById(R.id.image_view);
        textView=(TextView)findViewById(R.id.text_view);
        listView=(RecyclerView)findViewById(R.id.list_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listView.setLayoutManager(llm);

        String photoJson=getIntent().getStringExtra("photoJson");
        String commentsJson=getIntent().getStringExtra("commentsJson");;
        Log.i("CommentsActivity", " showComments photoJson" + photoJson + " commentsJson " + commentsJson);
        Photo photo=new Gson().fromJson(photoJson,Photo.class);
        CommentsResponse comments=new Gson().fromJson(commentsJson,CommentsResponse.class);

        textView.setText("comments " + comments.getData().size());
        Picasso.with(this).load(photo.getImages().getStandardResolution().getUrl()).into(imageView);
        CommentsListAdapter adapter=new CommentsListAdapter(this, comments.getData());
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
