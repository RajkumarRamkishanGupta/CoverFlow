package com.magody.coverflowwithviewpager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.magody.coverflowwithviewpager.model.Product;
import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends AppCompatActivity {

    private ImageView imageView;

    private Button button;

    private static final String OBJECT = "object";

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imageView = (ImageView)findViewById(R.id.img);
        button = (Button)findViewById(R.id.btnClose);

        final Product product = (Product) getIntent().getSerializableExtra(OBJECT);

        Picasso.get().load(product.getUrlImage())
                .placeholder(R.drawable.ic_beach_access_green_24dp)
                .into(imageView);
        /*int drawbleResource = getIntent().getIntExtra(DRAWABLE_RESOURE, 0);

        imageView.setImageResource(drawbleResource);*/

        Log.d("IMAGEDETAIL", product.getUrlImage());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageDetailActivity.this, product.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });

    }
    @Override

    public void onBackPressed() {

        finish();
    }
}
