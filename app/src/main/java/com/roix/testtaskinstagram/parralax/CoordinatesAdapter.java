package com.roix.testtaskinstagram.parralax;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.roix.testtaskinstagram.Constants;
import com.roix.testtaskinstagram.ContentView;

/**
 * Created by u5 on 10/12/16.
 */
public class CoordinatesAdapter {
    private Canvas canvas;
    private Piece piece;
    private final double overlay=0.0;
    private double gx,gy,gz;//gravity vector [0,1]

    public CoordinatesAdapter(Canvas canvas,Piece piece,double gx,double gy,double gz){
        this.canvas=canvas;
        this.piece=piece;
        this.gx=gx;
        this.gy=gy;
        this.gz=gz;
    }

    /*
    * @TODO incorrect transforms
    * */
    public Rect getRectDst(){
        int sizeX=canvas.getWidth();
        int sizeY=canvas.getHeight();
        int stepX=sizeX/PiecesManager.dim;
        int stepY=sizeY/PiecesManager.dim;
        int realSizeX=piece.getPhoto().getImages().getStandardResolution().getWidth();
        int realSizeY=piece.getPhoto().getImages().getStandardResolution().getHeight();
        boolean isVertical=realSizeX<realSizeY;
        double overlayY=overlay*realSizeX/realSizeY;
        double overlayX=overlay*realSizeY/realSizeY;

        int left=(int)(piece.getRect().left-overlayX)*stepX;
        int right=(int)(piece.getRect().right+overlayX)*stepX;
        int top=(int)(piece.getRect().top+overlayY)*stepY;
        int bottom=(int)(piece.getRect().bottom-overlayY)*stepY;

        //consider difference beetven screen and photos aspect ratio
        int cutX=isVertical?((top-bottom)*realSizeX/realSizeY):0;
        int cutY=isVertical?0:((right-left)*realSizeY/realSizeX);
        cutX=cutX/4;
        cutY=cutY/4;

        int addX = (int)(gx*piece.getDepth()* Constants.PARRALAX_FACTOR);
        int addY = (int)(gy*piece.getDepth()* Constants.PARRALAX_FACTOR);

        return new Rect(left-addX+cutX,bottom+addY+cutY,right-addX-cutX,top+addY-cutY);
    }

    /*
    * @TODO incorrect transforms scale
    * */
    public Rect getRectSrc(){
        return new Rect(0,0,piece.getPhoto().getImages().getStandardResolution().getWidth(),piece.getPhoto().getImages().getStandardResolution().getHeight());
    }


}
