package com.roix.testtaskinstagram.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.roix.testtaskinstagram.pojo.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by u5 on 10/11/16.
 */
public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {
    private List<Photo> photos=null;
    private Context context;
    private mOnItemClickListener listener;


    public PhotoListAdapter(Context context,mOnItemClickListener listener) {
        this.context=context;
        this.listener=listener;
    }

    public void setPhotos(List<Photo> photos){
        this.photos=photos;

    }

    @Override
    public PhotoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        ImageView iv = new ImageView(context);
        ViewHolder vh = new ViewHolder(iv);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {
        //holder.imageView.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(photos.get(pos),listener);
        Picasso.with(context).load(photos.get(pos).getImages().getLowResolution().getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(photos==null)return 0;
        return photos.size();
    }

    public interface mOnItemClickListener{
        void onClick(Photo photo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        private Photo photo;
        private mOnItemClickListener listener;

        public ViewHolder(ImageView imageView) {
            super(imageView);
            this.imageView=imageView;
        }

        public void setListener(Photo photo,mOnItemClickListener listener){
            imageView.setOnClickListener(this);
            this.photo=photo;
            this.listener=listener;
        }

        @Override
        public void onClick(View v) {
            if(listener!=null&&photo!=null)
                listener.onClick(photo);
        }
    }
}