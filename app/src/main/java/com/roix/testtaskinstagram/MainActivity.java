package com.roix.testtaskinstagram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.google.gson.Gson;
import com.roix.testtaskinstagram.fragments.ParralaxFragment;
import com.roix.testtaskinstagram.fragments.PhotoListFragment;
import com.roix.testtaskinstagram.pojo.CommentsResponse;
import com.roix.testtaskinstagram.pojo.Photo;
import com.roix.testtaskinstagram.pojo.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, SearchView.OnSuggestionListener,SearchView.OnQueryTextListener{

    public MainPresenter presenter;
    private SimpleCursorAdapter cursorAdapter;
    private ProgressDialog spinner;
    private List<User> suggestions;

    private MenuItem searchViewMenuItem;
    private PhotoListFragment photoListFragment;
    private ParralaxFragment parralaxFragment;

    private void populateAdapter() {
        MatrixCursor c = new MatrixCursor(new String[]{ BaseColumns._ID, "userName" });
        for (int i=0; i<suggestions.size(); i++) {
            c.addRow(new Object[] {i, suggestions.get(i).getUsername()});
        }
        cursorAdapter.changeCursor(c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String accessToken=getIntent().getStringExtra("access_token");
        final String[] from = new String[] {"userName"};
        final int[] to = new int[] {android.R.id.text1};
        cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spinner = new ProgressDialog(this);
        spinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
        spinner.setMessage("Loading...");

        presenter=new MainPresenter(this,accessToken);
        presenter.init();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchViewMenuItem =menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(searchViewMenuItem);
        searchView.setSuggestionsAdapter(cursorAdapter);
        searchView.setOnSuggestionListener(this);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            presenter.backButtonPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        presenter.backButtonPressed();
        //super.onBackPressed();
    }

    @Override
    public void showComments(Photo photo,CommentsResponse comments) {
        String photoJson=new Gson().toJson(photo);
        String commentsJson=new Gson().toJson(comments);
        Intent intent=new Intent(MainActivity.this,CommentsActivity.class);
        intent.putExtra("photoJson",photoJson);
        intent.putExtra("commentsJson",commentsJson);
        startActivity(intent);
    }

    @Override
    public void showSearchList(List<User> data) {
        this.suggestions=data;
        populateAdapter();
    }

    @Override
    public void prepareMediaParralax() {
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if(parralaxFragment==null)
            parralaxFragment=new ParralaxFragment();
        presenter.updateContentView(parralaxFragment);
        getFragmentManager().beginTransaction().replace(R.id.fragment_layout, parralaxFragment).commit();
    }

    @Override
    public void prepareMediaList(String username) {
        getSupportActionBar().setTitle(username);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(photoListFragment==null)
            photoListFragment=new PhotoListFragment();
        presenter.updateContentView(photoListFragment);

        getFragmentManager().beginTransaction().replace(R.id.fragment_layout,photoListFragment).commit();

    }

    @Override
    public void showIsProgressing(boolean isProgressing) {
        if(isProgressing)spinner.show();
        else spinner.dismiss();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        presenter.searchQueueChanged(newText);
        return false;
    }

    @Override
    public boolean onSuggestionSelect(int position) {
        return true;
    }

    @Override
    public boolean onSuggestionClick(int position) {
        presenter.userChoosed(suggestions.get(position));
        searchViewMenuItem.collapseActionView();
        return true;
    }
}
