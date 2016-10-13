package com.roix.testtaskinstagram;

import com.roix.testtaskinstagram.pojo.CommentsResponse;
import com.roix.testtaskinstagram.pojo.TokenResponse;
import com.roix.testtaskinstagram.pojo.UserMediaResponse;
import com.roix.testtaskinstagram.pojo.UserSearchResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by u5 on 10/9/16.
 */
public interface InstagramApiModel {

    @FormUrlEncoded
    @POST("/oauth/access_token")
    Call<TokenResponse> getAccessToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                                       @Field("redirect_uri") String redirect_uri, @Field("grant_type") String grant_type,
                                       @Field("code") String code);

    @GET("/v1/tags/{tag-name}/media/recent")
    Call<ResponseBody> getMediaByTag(@Path("tag-name") String tagName, @Query("access_token") String accessToken,
                                   @Query("max_id") String maxId, @Query("min_id") String minId);


    @GET("/v1/users/search")
    Call<UserSearchResponse> userSearch(@Query("access_token") String accessToken, @Query("q") String name);


    @GET("/v1/users/{user-id}/media/recent")
    Call<UserMediaResponse> getMediaByUser(@Path("user-id") String userId, @Query("access_token") String accessToken,
                                      @Query("count") String count, @Query("max_id") String maxId, @Query("min_id") String minId);

    @GET("/v1/users/self/media/recent")
    Call<UserMediaResponse> getMediaBySelf(@Query("access_token") String accessToken,
                                           @Query("count") String count, @Query("max_id") String maxId, @Query("min_id") String minId);

    @GET("/v1/media/{media-id}/comments")
    Call<CommentsResponse> getCommentsByMediaId(@Path("media-id") String mediaId, @Query("access_token") String accessToken);

}
