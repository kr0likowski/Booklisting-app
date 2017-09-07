package com.example.macos.booklisting_app;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by macos on 07.09.2017.
 */

public class BookAdapter extends ArrayAdapter<Book>{


        public BookAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public BookAdapter(Context context, int resource, List<Book> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.list_row_custom, null);
            }

            Book p = getItem(position);

            if (p != null) {
                ImageView tt1 = (ImageView) v.findViewById(R.id.BookImg);
                TextView tt2 = (TextView) v.findViewById(R.id.title);
                TextView tt3 = (TextView) v.findViewById(R.id.desc);

                if (tt1 != null) {
                    //tt1.setImageResource(p.getImageSrc());
                }

                if (tt2 != null) {
                    tt2.setText(p.getTitle());
                }

                if (tt3 != null) {
                    tt3.setText(p.getDesc());
                }
            }

            return v;
        }

    }


