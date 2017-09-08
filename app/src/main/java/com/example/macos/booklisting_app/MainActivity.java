package com.example.macos.booklisting_app;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private ListView listView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new BookAdapter(this,R.layout.list_row_custom,createArray()));
//        searchView = (SearchView)findViewById(R.id.searchview);

    }

    public ArrayList<Book> createArray(){
        ArrayList<Book> bookArrayList = new ArrayList<>();
        //bookArrayList.add(new Book("","Android Programming","By Dr Dre"));
        return bookArrayList;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {

        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }
}
