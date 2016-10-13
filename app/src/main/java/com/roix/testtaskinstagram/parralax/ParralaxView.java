package com.roix.testtaskinstagram.parralax;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.roix.testtaskinstagram.R;
import com.roix.testtaskinstagram.pojo.Photo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by u5 on 10/12/16.
 */
public class ParralaxView extends View {
    private boolean isActive=false;
    private List<Piece>  pieces;
    private Context context;
    private Paint paint;
    private String TAG="ParralaxView";
    SensorModule sensor;
    Bitmap tempBitmap;

    public ParralaxView(Context context) {
        super(context);
        this.context=context;
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        tempBitmap= BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        sensor=new SensorModule(context);
        Log.d(TAG,"ParralaxView(Context context) {");
    }

    public void activate(List<Photo> photos){
        pieces=PiecesManager.generateEmptyPiecesScene(photos);
        for(final Piece piece:pieces){
            Picasso.with(context).load(piece.getPhoto().getImages().getStandardResolution().getUrl()).into(piece);
        }
        Log.d(TAG,"isActive true");
        isActive=true;
    }

    public void deactivate(){
        for(Piece piece:pieces)
            Picasso.with(context).cancelRequest(piece);
    }

    public void drawScene(Canvas canvas){
        for(Piece piece:pieces){
            if(!piece.isLoaded())continue;
            CoordinatesAdapter adapter=new CoordinatesAdapter(canvas,piece,sensor.gx(),sensor.gy(),sensor.gz());
            Bitmap bitmap=piece.getBitmap();
            Rect rectDst=adapter.getRectDst();
            Rect rectScr=adapter.getRectSrc();
            canvas.drawBitmap(bitmap, rectScr, rectDst, paint);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isActive)
            drawScene(canvas);
        invalidate();


    }


}
