package com.roix.testtaskinstagram.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roix.testtaskinstagram.ContentView;
import com.roix.testtaskinstagram.MainActivity;
import com.roix.testtaskinstagram.R;
import com.roix.testtaskinstagram.adapters.PhotoListAdapter;
import com.roix.testtaskinstagram.pojo.Photo;

import java.util.List;


public class PhotoListFragment extends Fragment implements ContentView, PhotoListAdapter.mOnItemClickListener,SwipeRefreshLayout.OnRefreshListener {

    private List<Photo> photos;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout refreshLayout;
    private PhotoListAdapter adapter;

    private String TAG="PhotoListFragment";

    public PhotoListFragment() {
        Log.d(TAG, "PhotoListFragment()");

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_photo_list,container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        refreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setOnRefreshListener(this);
        layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);
        Log.d(TAG, "onCreateView end");

        adapter = new PhotoListAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void updatePhotos(List<Photo> photos) {
        this.photos=photos;
        Log.d(TAG, "pre setAdapter");
        adapter.setPhotos(photos);

        adapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh");
        MainActivity activity=(MainActivity)getActivity();
        activity.presenter.refresh();
    }


    @Override
    public void onClick(Photo photo) {
        MainActivity activity=(MainActivity)getActivity();
        activity.presenter.onMediaClick(photo);
    }
}
