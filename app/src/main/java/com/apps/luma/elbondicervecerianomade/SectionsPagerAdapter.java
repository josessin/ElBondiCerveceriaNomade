package com.apps.luma.elbondicervecerianomade;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jrepetto on 27/10/2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<BitmapDrawable> mFragmentTitle = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(Fragment fragment, BitmapDrawable title) {
        mFragments.add(fragment);
        mFragmentTitle.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        BitmapDrawable icono;
        switch (position) {
            case 0:
                icono = mFragmentTitle.get(position);
                break;
            case 1:
                icono = mFragmentTitle.get(position);
                break;
            default:
                icono = null;
                break;
        }
        SpannableStringBuilder sb = new SpannableStringBuilder(""); // space added before text for convenience
        try {
            icono.setBounds(0, 0, icono.getIntrinsicWidth(), icono.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(icono, DynamicDrawableSpan.ALIGN_BOTTOM);
            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return sb;
    }
}
