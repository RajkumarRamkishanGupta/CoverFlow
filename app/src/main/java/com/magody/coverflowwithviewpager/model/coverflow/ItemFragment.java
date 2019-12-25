package com.magody.coverflowwithviewpager.model.coverflow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.magody.coverflowwithviewpager.ImageDetailActivity;
import com.magody.coverflowwithviewpager.R;
import com.magody.coverflowwithviewpager.model.Product;
import com.squareup.picasso.Picasso;



public class ItemFragment extends Fragment {

    private static final String TAG = "ItemFragment";


    private static final String SCALE = "scale";
    private static final String OBJECT = "object";

    private int screenWidth;

    private int screenHeight;


    static ItemFragment newInstance(float scale, Product product) {

        ItemFragment fragment = new ItemFragment();

        Bundle args = new Bundle();
        args.putFloat(SCALE, scale);
        //send the object serialized
        args.putSerializable (OBJECT, product);

        fragment.setArguments(args);
        return fragment;

    }



    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWidthAndHeight();

    }



    @SuppressLint("SetTextI18n")

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container == null) {

            return null;

        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth / 4, screenHeight / 4);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_item, container, false);

        if(this.getArguments() != null){


            float scale = this.getArguments().getFloat(SCALE);
            final Product product = (Product) this.getArguments().getSerializable(OBJECT);




            TextView textView = linearLayout.findViewById(R.id.text);
            ImageView imageView = linearLayout.findViewById(R.id.pagerImg);
            imageView.setLayoutParams(layoutParams);

            if(product != null){
                textView.setText(product.getName());

                Picasso.get().load(product.getUrlImage())
                        .placeholder(R.drawable.ic_android_black_24dp)
                        .into(imageView);

                //set image alpha
                /*if (scale == 0.7f){
                    imageView.setAlpha(0.5f);
                }else {
                    imageView.setAlpha(1f);
                }*/

                //handling click event

                imageView.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {
                        //in this point we add the navigation to another activity o the clic objetive
                        Intent intent = new Intent(getActivity(), ImageDetailActivity.class);
                        intent.putExtra(OBJECT, product);
                        startActivity(intent);

                    }

                });

                Button button = linearLayout.findViewById(R.id.buttonFragmentItem);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), product.getName(), Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                Log.e(TAG, "No object passed as an argument");
            }



            FlowPageViewLinearLayout root = linearLayout.findViewById(R.id.root_container);

            root.setScaleBoth(scale);
        }else{

            Log.e(TAG, "No arguments passed");
        }


        return linearLayout;

    }



    /**

     * Get device screen width and height

     */

    private void getWidthAndHeight() {

        DisplayMetrics displaymetrics = new DisplayMetrics();

        if(getActivity() != null)
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        screenHeight = displaymetrics.heightPixels;

        screenWidth = displaymetrics.widthPixels;

    }
}
