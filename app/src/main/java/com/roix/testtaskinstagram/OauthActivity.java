package com.roix.testtaskinstagram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.roix.testtaskinstagram.pojo.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OauthActivity extends AppCompatActivity {


    private WebView webView;
    private ProgressDialog spinner;
    private static final String TAG="OauthActivity";
    private java.lang.String url = "https://api.instagram.com/oauth/authorize/?client_id=" + Constants.CLIENT_ID + "&redirect_uri=" + Constants.REDIRECT_URI + "&response_type=code&display=touch&scope=public_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webView=(WebView)findViewById(R.id.web_view);
        spinner = new ProgressDialog(this);
        spinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
        spinner.setMessage("Loading...");
        webView.loadUrl(url);
        webView.setWebViewClient(new OAuthWebViewClient());


    }

    private void startTokenRequest(String code){
        Call<TokenResponse> call=ServiceGenerator.createApiService().getAccessToken(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.REDIRECT_URI, Constants.AUTORISATION_CODE, code);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                //if(!response.isSuccessful()) return;
                Log.d(TAG, "token"+response.body().getAccessToken());
                Intent intent=new Intent(OauthActivity.this, MainActivity.class);
                intent.putExtra("access_token",response.body().getAccessToken());
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

            }
        });
    }

    private class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String request) {
            Log.d(TAG, "Redirecting URL " + url);

            if (url.startsWith(Constants.REDIRECT_URI)) {
                String urls[] = request.split("=");
                //request.getUrl().toString()
                //mListener.onCodeReceived(urls[1]);

                Log.d(TAG, "token " + urls[1]);

                //mListener.onComplete(urls[1]);
                //InstagramDialog.this.dismiss();
                return true;
            }


            return false;
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "Loading URL: " + url);

            super.onPageStarted(view, url, favicon);
            spinner.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            spinner.dismiss();

            String urls[] = url.split("=");

            Log.d(TAG, "onPageFinished code: " + urls[1]);
            startTokenRequest(urls[1]);

        }

    }



}
