package com.roix.testtaskinstagram.parralax;

import android.graphics.Rect;
import android.support.annotation.NonNull;

import com.roix.testtaskinstagram.pojo.Photo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by u5 on 10/12/16.
 */
public class PiecesManager {
    public static final int dim=5;

    public static List<Piece> generateEmptyPiecesScene(List<Photo> photos){
        ArrayList<Piece> ret=new ArrayList<>();
        List<Rect> rects=getSceneScheme();
        for(int i=0;i<photos.size()&&i<rects.size();i++){
            Photo p=photos.get(i);
            Rect r=rects.get(i);
            int d=getDepthByRect(r);
            ret.add(new Piece(p,r,d));
        }
        return ret;
    }

    /*
    * form instance of every element in relative coords, [0,5] in every axis
    * */
    private static List<Rect> getSceneScheme(){
        ArrayList<Rect> rects=new ArrayList<>();
        rects.add(new Rect(2,4,5,1));
        rects.add(new Rect(0,5,2,3));
        rects.add(new Rect(0,2,2,0));
        rects.add(new Rect(0,3,1,2));
        rects.add(new Rect(3,1,4,0));
        rects.add(new Rect(3,5,4,4));
        return rects;
    }

    private static int getDepthByRect(Rect rect){
        int lx=rect.left;
        int rx=rect.right;
        return rx-lx;
    }


}
