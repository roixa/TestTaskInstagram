package com.roix.testtaskinstagram;

import android.util.Log;
import android.widget.Switch;

import com.roix.testtaskinstagram.pojo.CommentsResponse;
import com.roix.testtaskinstagram.pojo.Photo;
import com.roix.testtaskinstagram.pojo.User;
import com.roix.testtaskinstagram.pojo.UserMediaResponse;
import com.roix.testtaskinstagram.pojo.UserSearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by u5 on 10/11/16.
 */
public class MainPresenter{
    private MainView mainView;
    private ContentView contentView;
    private InstagramApiModel api;

    public enum State{
        Parralax,
        MediaList,
        Comments
    }
    private State state;
    private User user;
    private String accessToken;



    public MainPresenter(MainView mainView,String accessToken){
        this.mainView=mainView;
        this.accessToken=accessToken;
        api=ServiceGenerator.createApiService();
    }

    public void init(){
        state=State.Parralax;
        mainView.prepareMediaParralax();
        sendSelfMediaRequest();
    }

    public void updateContentView(ContentView contentView){
        this.contentView=contentView;
    }

    public void backButtonPressed(){
        if(state==State.MediaList){
            state=State.Parralax;
            mainView.prepareMediaParralax();
            sendSelfMediaRequest();
        }
        else if(state==State.Parralax){
            mainView.close();
        }

    }

    public void searchQueueChanged(String queue){
        api.userSearch(accessToken, queue).enqueue(new Callback<UserSearchResponse>() {
            @Override
            public void onResponse(Call<UserSearchResponse> call, Response<UserSearchResponse> response) {
                if (response.isSuccessful()) {
                    mainView.showSearchList(response.body().getData());
                }
                else Log.i("@@@","not isSuccessful "+response.code()+response.raw());

            }

            @Override
            public void onFailure(Call<UserSearchResponse> call, Throwable t) {

            }
        });
    }

    public void userChoosed(User user){
        this.user=user;
        state=State.MediaList;
        mainView.prepareMediaList(user.getUsername());
        sendUserMediaRequest();
    }

    public void onMediaClick(final Photo photo){
        mainView.showIsProgressing(true);
        Log.i("@@@","onMediaClick "+photo.getId());
        api.getCommentsByMediaId(photo.getId(),accessToken).enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {
                mainView.showIsProgressing(false);
                if(response.isSuccessful())
                    mainView.showComments(photo,response.body());
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {

            }
        });
    }

    public void refresh(){
        switch (state){
            case MediaList:
                sendUserMediaRequest();
                break;
            case Comments:

                break;
        }
    }

    private void sendUserMediaRequest(){
        mainView.showIsProgressing(true);
        api.getMediaByUser(user.getId(), accessToken, "", "", "").enqueue(new Callback<UserMediaResponse>() {
            @Override
            public void onResponse(Call<UserMediaResponse> call, Response<UserMediaResponse> response) {
                mainView.showIsProgressing(false);
                if(response.isSuccessful())
                    contentView.updatePhotos(response.body().getData());
            }

            @Override
            public void onFailure(Call<UserMediaResponse> call, Throwable t) {

            }
        });
    }

    private void sendSelfMediaRequest(){
        mainView.showIsProgressing(true);
        api.getMediaBySelf(accessToken, "", "", "").enqueue(new Callback<UserMediaResponse>() {
            @Override
            public void onResponse(Call<UserMediaResponse> call, Response<UserMediaResponse> response) {
                mainView.showIsProgressing(false);
                if(response.isSuccessful())
                    //Log.d("@@@",response.body().getData().size()+"");
                    contentView.updatePhotos(response.body().getData());
            }

            @Override
            public void onFailure(Call<UserMediaResponse> call, Throwable t) {

            }
        });

    }



}
