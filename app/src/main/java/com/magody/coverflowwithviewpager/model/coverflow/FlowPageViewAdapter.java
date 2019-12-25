package com.magody.coverflowwithviewpager.model.coverflow;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.magody.coverflowwithviewpager.MainActivity;
import com.magody.coverflowwithviewpager.R;
import com.magody.coverflowwithviewpager.model.Product;

import java.util.List;

public class FlowPageViewAdapter  extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

    private static final String TAG = "FlowPageViewAdapter";

    final static float BIG_SCALE = 1f;
    private final static float SMALL_SCALE = 0.7f;
    private final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private int currentPosition = 0;

    private Activity mActivity;

    private FragmentManager fragmentManager;
    private boolean isFirstTime;

    private float scale;

    private List<Product> productList;

    public FlowPageViewAdapter(Activity mActivity, FragmentManager fm, List<Product> productList) {

        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        this.fragmentManager = fm;

        this.mActivity = mActivity;

        this.productList = productList; //it can be whatever model you have

    }

    public void updateMonthImage(List<Product> product){
        productList=product;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {

            if (position == MainActivity.first_page)
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % MainActivity.sizeOfListElements;

        } catch (Exception e) {

            Log.e(TAG, "The error is " + e.getMessage());
        }
        //generate a fragment with the image and text of the object of the list of objects
        return ItemFragment.newInstance(scale, productList.get(position));
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            //count = MainActivity.sizeOfListElements * MainActivity.LOOPS;
            count = MainActivity.sizeOfListElements;
        } catch (Exception e) {
            Log.e(TAG, "The error is " + e.getMessage());
        }
        return count;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {

            if (positionOffset >= 0f && positionOffset <= 1f) {

                FlowPageViewLinearLayout current = getRootView(position);

                FlowPageViewLinearLayout next = getRootView(position + 1);


                ///when we scroll, then the actual image decreases his sizes and the next (left o rigth) increases his size
                float new_current_scale = BIG_SCALE - DIFF_SCALE * positionOffset;
                float new_next_scale = SMALL_SCALE + DIFF_SCALE * positionOffset;

                //Log.d(TAG, "Position: " + position +", Scale current: " + new_current_scale + ", Scale next: " + new_next_scale);

                current.setScaleBoth(new_current_scale);
                next.setScaleBoth(new_next_scale);
                getRootView(position+1).setAlpha(0.5f);
                getRootView(position-1).setAlpha(0.5f);
                getRootView(position).setAlpha(1f);
                getRootView(0).setAlpha(1f);
            }

            Log.e("cover", "1");


        } catch (Exception e) {

            Log.e(TAG, "The error is " + e.getMessage());

        }

    }
    @Override
    public void onPageSelected(int position) {
        //notifyDataSetChanged();
        Log.e("cover", "2");
    }
    @Override
    public void onPageScrollStateChanged(int state) {
//notifyDataSetChanged();
        Log.e("cover", "3");

    }
    @SuppressWarnings("ConstantConditions")

    private FlowPageViewLinearLayout getRootView(int position) {
        //return the fragment in the position equal to position
        return (FlowPageViewLinearLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.root_container);

    }



    private String getFragmentTag(int position) {

        return "android:switcher:" + ((MainActivity) mActivity).pager.getId() + ":" + position;

    }
}
