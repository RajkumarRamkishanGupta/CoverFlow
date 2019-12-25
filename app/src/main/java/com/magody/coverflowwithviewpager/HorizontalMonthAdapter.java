package com.magody.coverflowwithviewpager;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalMonthAdapter extends RecyclerView.Adapter<HorizontalMonthAdapter.MyViewHolder> {

    private List<CategoryModel> data;

    Context mContext;

    UpdateMonth context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSubCategories;
        //public CardView card_view;

        public MyViewHolder(View view) {
            super(view);
            tvSubCategories = view.findViewById(R.id.tvSubCategories);
            //card_view = (CardView) view.findViewById(R.id.card_view);

        }
    }




    public HorizontalMonthAdapter(MainActivity context,List<CategoryModel> arrayList) {
        this.mContext = context;
        data=arrayList;
        this.context=context;
    }

    public void updateYearOfMonth(List<CategoryModel> models){
        try {
            ArrayList<CategoryModel> monthHash = new ArrayList<>(models);
            data.clear();
            data = monthHash;
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_item_newview, parent, false);

        return new MyViewHolder(itemView);
    }

    private void selectCategory(String name) {
        for (CategoryModel model : data) {
            if (name.equals(model.getName())) {
                model.setSelected(true);
            } else {
                model.setSelected(false);
            }
        }
        notifyDataSetChanged();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {


            final CategoryModel model = data.get(position);
            holder.tvSubCategories.setText(model.getName());
            /*if (model.isSelected()) {
                holder.tvSubCategories.setTextColor(getResources().getColor(R.color.vorrafashion_pink_color));
                holder.card_view.setBackgroundColor(getResources().getColor(R.color.vorrafashion_text_color));
                productDiscriptionWebService(id, model.getId());
                // firstLoadDone=false;

            } else {
                holder.card_view.setBackgroundColor(getResources().getColor(R.color.white));
                holder.tvSubCategories.setTextColor(getResources().getColor(R.color.black));

            }
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //text.setText(holder.txtView.getText().toString());
                    //  Toast.makeText(MainActivity.this,,Toast.LENGTH_SHORT).show();
                    selectCategory(model.getName());
                    //productDiscriptionWebService(id,model.getId());

                }
            });*/

            if (model.isSelected()) {
                holder.tvSubCategories.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                //MainActivity.updateMonth(model.getId());
                //productDiscriptionWebService(id, model.getId(), Integer.toString(pageSize), Integer.toString(counter));
                // firstLoadDone=false;
                //modelId = model.getId();
            } else {
                holder.tvSubCategories.setTextColor(ContextCompat.getColor(mContext, R.color.black));

            }
            holder.tvSubCategories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //text.setText(holder.txtView.getText().toString());
                    //  Toast.makeText(MainActivity.this,,Toast.LENGTH_SHORT).show();
                    selectCategory(model.getName());
                    context.updateMonthOfImage(model.getId());
                    //productDiscriptionWebService(id,model.getId());

                }
            });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
