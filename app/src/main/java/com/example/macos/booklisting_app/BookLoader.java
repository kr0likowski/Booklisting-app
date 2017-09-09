package com.example.macos.booklisting_app;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by macos on 08.09.2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    String mUrl;
    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Book> loadInBackground() {
        List<Book> list = Utils.fetchData(mUrl);
        return list;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
