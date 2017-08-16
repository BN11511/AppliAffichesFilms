package com.example.bruno.appliaffichesfilms.adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bruno.appliaffichesfilms.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Bruno on 16/08/2017.
 */

public class ImageCaroussel extends android.support.v4.app.Fragment {




    public void setCount(int count) {
        this.count = count;
    }

    int count;

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    int position;
    ArrayList<String> images;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.image_layout, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.imageView2);

        Picasso.with(getActivity()).load(images.get(position)).into(img);
        return view;
    }

}

