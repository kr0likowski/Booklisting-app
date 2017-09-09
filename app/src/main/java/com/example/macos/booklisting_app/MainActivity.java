package com.example.macos.booklisting_app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<Book>> {
    private ListView listView;
    private SearchView searchView;
    private String url = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=3";
    private List<Book> BookArray;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);
        android.app.LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1,null,this);
        searchView = (SearchView)findViewById(R.id.searchview);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }



    @Override
    public android.content.Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        boolean isconnected = netinfo != null && netinfo.isConnectedOrConnecting();
        if(isconnected){
            return new BookLoader(getApplicationContext(),url);
        }else{
            //progressbar
            return null;
        }
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<Book>> loader, List<Book> data) {
        updateUi(data);
        //progressbar
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<Book>> loader) {
        adapter.clear();

    }
    private void updateUi(List<Book> list){
       adapter = new BookAdapter(getApplicationContext(),R.layout.list_row_custom,list);
        listView.setAdapter(adapter);
    }
}
