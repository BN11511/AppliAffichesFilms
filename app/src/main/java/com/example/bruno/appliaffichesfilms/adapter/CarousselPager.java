package com.example.bruno.appliaffichesfilms.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Bruno on 16/08/2017.
 */

public class CarousselPager extends FragmentStatePagerAdapter  {

    ArrayList<String> images;

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }


    public CarousselPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        ImageCaroussel ic = new ImageCaroussel();
        ic.setPosition(position);
        ic.setImages(images);
        ic.setCount(getCount());
        return ic;
    }

    @Override
    public int getCount() {
        if(images == null)
            return 0;
        return images.size();
    }

    @Override public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

    }


}
