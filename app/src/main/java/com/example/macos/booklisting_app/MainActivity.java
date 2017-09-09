package com.example.macos.booklisting_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<Book>> {
    private ListView listView;
    private SearchView searchView;
    private TextView emptyView;
    private String url = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5";
    private List<Book> BookArray;
    private BookAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);
        emptyView = (TextView)findViewById(R.id.empty);
        listView.setEmptyView(emptyView);
        progressBar = (ProgressBar)findViewById(R.id.loading_spinner);
        getLoaderManager().initLoader(1,null,this);
        searchView = (SearchView)findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                url = "https://www.googleapis.com/books/v1/volumes?q={" + s + "}";
                restartLoader();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
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
            progressBar.setVisibility(View.GONE);
            emptyView.setText("No internet connection");
            return null;
        }
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<Book>> loader, List<Book> data) {
        progressBar.setVisibility(View.GONE);
        updateUi(data);
        emptyView.setText("No books found");
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<Book>> loader) {
        adapter.clear();

    }
    private void updateUi(final List<Book> list){
       adapter = new BookAdapter(getApplicationContext(),R.layout.list_row_custom,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String site = list.get(i).getUrl().toString();
                Intent d = new Intent(Intent.ACTION_VIEW);
                d.setData(Uri.parse(site));
                startActivity(d);
            }
        });
    }
    private void restartLoader(){
        getLoaderManager().restartLoader(1,null,this);
        progressBar.setVisibility(View.VISIBLE);
        emptyView.setText("");
    }
}
