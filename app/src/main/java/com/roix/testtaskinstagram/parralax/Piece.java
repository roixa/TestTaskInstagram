package com.roix.testtaskinstagram.parralax;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.roix.testtaskinstagram.pojo.Photo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by u5 on 10/12/16.
 */
public class Piece implements Target{
    private Photo photo;
    private Rect rect;//rect in relative coords
    private int depth;
    private Bitmap bitmap=null;
    private boolean isLoaded=false;

    public Piece(Photo photo,Rect rect,int depth){
        this.photo=photo;
        this.rect=rect;
        this.depth=depth;
    }


    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        this.bitmap=bitmap;
        isLoaded=true;
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
