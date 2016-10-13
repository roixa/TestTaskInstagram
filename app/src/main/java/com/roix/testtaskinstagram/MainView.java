package com.roix.testtaskinstagram;

import com.roix.testtaskinstagram.pojo.Comment;
import com.roix.testtaskinstagram.pojo.CommentsResponse;
import com.roix.testtaskinstagram.pojo.Photo;
import com.roix.testtaskinstagram.pojo.User;

import java.util.List;

/**
 * Created by u5 on 10/11/16.
 */
public interface MainView {
    void showComments(Photo photo,CommentsResponse comments);
    void showSearchList(List<User> data);
    void prepareMediaParralax();
    void prepareMediaList(String username);
    void showIsProgressing(boolean isProgressing);
    void close();
}
