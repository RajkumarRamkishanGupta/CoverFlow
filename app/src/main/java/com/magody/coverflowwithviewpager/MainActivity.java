package com.magody.coverflowwithviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.magody.coverflowwithviewpager.model.Product;
import com.magody.coverflowwithviewpager.model.coverflow.FlowPageViewAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements UpdateMonth{

    public final static int LOOPS = 10; //this allow the ciclic interaction, so in this way the last element is conected with the first one

    //LOOPS should be 1 when the list should only be seen once, otherwise it should be N if the list should be viewed N times
    //if LOOPS is 1, then the first page should be the middle of the size for esthetic and good presentation

    //We will appear in the repetition (loop) number (LOOPS/2)

    public FlowPageViewAdapter adapter;

    public ViewPager pager;
    RecyclerView horizontal_recycler_month, horizontal_recycler_year;
    HashMap<String,List<CategoryModel>> listHashMap;
    List<Product> productList;

    public static int sizeOfListElements; //ViewPager items size
    private HorizontalMonthAdapter horizontalMonthAdapter;
    private List<CategoryModel> studentDataList = new ArrayList<>();
    /**
     * You shouldn't define first page = 0.
     * Let define firstpage = 'number viewpager size' to make endless carousel
     */
    public static int first_page;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.mainViewPager);

        //set page margin between pages for viewpager
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = (metrics.widthPixels/2);
        pager.setPageMargin(-pageMargin);

        //create and set the adapter for the data (list of objects)

        productList = new ArrayList<>();
        productList.add(new Product("1", "https://images-na.ssl-images-amazon.com/images/I/81NI0UFz4zL._AC_SR300,300_.jpg"));
        productList.add(new Product("2", "https://images-na.ssl-images-amazon.com/images/I/91fM9mRIegL._AC_UL115_.jpg"));
        productList.add(new Product("3", "https://images-na.ssl-images-amazon.com/images/I/71KAWkgW9eL._AC_SR300,300_.jpg"));
        productList.add(new Product("1", "https://images-na.ssl-images-amazon.com/images/I/51cXcSbzGwL._AC_SR300,300_.jpg"));
        productList.add(new Product("2", "https://images-na.ssl-images-amazon.com/images/I/716SrHQrbjL._AC_SR300,300_.jpg"));
        productList.add(new Product("3", "https://images-na.ssl-images-amazon.com/images/I/71CYOoQWTFL._AC_SR300,300_.jpg"));
        productList.add(new Product("1", "https://images-na.ssl-images-amazon.com/images/I/71fSsXVDp6L._AC_UL115_.jpg"));
        productList.add(new Product("2", "https://images-na.ssl-images-amazon.com/images/I/91FeHhkSplL._AC_UY695_.jpg"));
        productList.add(new Product("3", "https://images-na.ssl-images-amazon.com/images/I/61+dtvrPhKL._AC_SR300,300_.jpg"));
        productList.add(new Product("1", "https://images-na.ssl-images-amazon.com/images/I/71wffEOZw8L._AC_SX425_.jpg"));

        ArrayList<Product> newProductList = new ArrayList<>();
        for (int i = 0; i <productList.size() ; i++) {
            if (productList.get(i).getName().equalsIgnoreCase("1"))
                newProductList.add(new Product("1",productList.get(i).getUrlImage()));
        }
        sizeOfListElements = newProductList.size();
        first_page = sizeOfListElements; //if its not the size of the list, then the loops keeps working but not for the first element if we scroll to the left at running the program


        adapter = new FlowPageViewAdapter(this, getSupportFragmentManager(), newProductList);
        pager.setPageTransformer(true, new ZoomOutPageTransformer(true));
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        pager.addOnPageChangeListener(adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(0);
        pager.setOffscreenPageLimit(sizeOfListElements);

        horizontal_recycler_month = findViewById(R.id.horizontal_recycler_month);
        horizontal_recycler_year = findViewById(R.id.horizontal_recycler_year);




       /* if (statusCode == 200) {
            if (response.length() != 0) {
                arrayList1 = new ArrayList<>();
                arrayList1.clear();
                JSONObject objDetail = response.getJSONObject(RESPONSE);
                JSONArray arrayDetail = objDetail.getJSONArray(SUBCATEGORIES);
                for (int y = 0; y < arrayDetail.length(); y++) {
                    JSONObject productDetailObj = (JSONObject) arrayDetail.get(y);
                    String Id = (productDetailObj.isNull(ID) || productDetailObj.getString(ID).equals("")) ? "" : productDetailObj.getString(ID);
                    String Name = (productDetailObj.isNull(NAME) || productDetailObj.getString(NAME).equals("")) ? "" : productDetailObj.getString(NAME);
                    boolean selected = (y == 0) ? true : false;
                    arrayList1.add(new CategoryModel(Name, Id, selected));
                }
                horizontalAdapter = new HorizontalAdapter(arrayList1);
                LinearLayoutManager horizontalLayoutManagaer
                        = new LinearLayoutManager(getMainActivity(), LinearLayoutManager.HORIZONTAL, false);
                horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
                horizontal_recycler_view.setAdapter(horizontalAdapter);
                horizontalAdapter.notifyDataSetChanged();
                            *//*    rankingAdapter = new RankingFragment.RankingAdapter(getMainActivity(), arrayList);
                                rankList.setAdapter(rankingAdapter);*//*

//                               resumeAdapter.notifyDataSetChanged();
                hideProgress();
            }*/

        List<CategoryModel> categoryModels = new ArrayList<>();
        categoryModels.add(new CategoryModel("2019", "1",true));
        categoryModels.add(new CategoryModel("2018", "2",false));
        categoryModels.add(new CategoryModel("2017", "3",false));
        /*categoryModels.add(new CategoryModel("2016", "4",false));
        categoryModels.add(new CategoryModel("2015", "5",false));
        categoryModels.add(new CategoryModel("2014", "6",false));
        categoryModels.add(new CategoryModel("2013", "7",false));
        categoryModels.add(new CategoryModel("2012", "8",false));*/


        listHashMap = new HashMap<>();
        ArrayList<CategoryModel> categoryJune = new ArrayList<>();
        categoryJune.add(new CategoryModel("jun", "1",true));
        categoryJune.add(new CategoryModel("jul", "2",false));
        categoryJune.add(new CategoryModel("apr", "3",false));

        ArrayList<CategoryModel> categoryApr = new ArrayList<>();
        categoryApr.add(new CategoryModel("apr", "1",true));
        //categoryApr.add(new CategoryModel("jul", "2",false));
        categoryApr.add(new CategoryModel("may", "3",false));

        ArrayList<CategoryModel> categoryAug = new ArrayList<>();
        categoryAug.add(new CategoryModel("aug", "1",true));
        categoryAug.add(new CategoryModel("jul", "2",false));
        categoryAug.add(new CategoryModel("dec", "3",false));
        listHashMap.put("1",categoryJune);
        listHashMap.put("2",categoryApr);
        listHashMap.put("3",categoryAug);

        horizontalMonthAdapter = new HorizontalMonthAdapter(MainActivity.this,listHashMap.get("1"));
        //horizontal_recycler_month.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        horizontal_recycler_month.setLayoutManager(layoutManager);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_month.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_month.setAdapter(horizontalMonthAdapter);
        horizontalMonthAdapter.notifyDataSetChanged();

        HorizontalAdapter horizontalAdapter = new HorizontalAdapter(categoryModels,this,listHashMap);
        LinearLayoutManager horizontalLayoutManagaer1
                = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_year.setLayoutManager(horizontalLayoutManagaer1);
        horizontal_recycler_year.setAdapter(horizontalAdapter);
        horizontalAdapter.notifyDataSetChanged();
    }

  /*  public void updateMonth(String id){
        HorizontalMonthAdapter horizontalAdapter = new HorizontalMonthAdapter(MainActivity.this,listHashMap.get(id));
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_month.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_month.setAdapter(horizontalAdapter);
        horizontalAdapter.notifyDataSetChanged();
    }*/

    @Override
    public void updateMonth(String id) {
          /*studentDataList.clear();
          studentDataList.addAll(monthHashMap);
          horizontalMonthAdapter.notifyDataSetChanged();*/
        /*ArrayList<CategoryModel> monthHash = new ArrayList<>();
        monthHash.addAll(listHashMap.get(id));
        horizontalMonthAdapter.updateYearOfMonth(monthHash);*/
        ArrayList<CategoryModel> monthHash = new ArrayList<>();
        monthHash.addAll(listHashMap.get(id));
        for (int i = 0; i < monthHash.size(); i++) {
            if (i==0){
                monthHash.get(i).setSelected(true);
            }else {
                monthHash.get(i).setSelected(false);
            }
        }
        //horizontalMonthAdapter = new HorizontalMonthAdapter(MainActivity.this,listHashMap.get(id));
        horizontalMonthAdapter = new HorizontalMonthAdapter(MainActivity.this,monthHash);
        //horizontal_recycler_month.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        horizontal_recycler_month.setLayoutManager(layoutManager);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_month.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_month.setAdapter(horizontalMonthAdapter);
        horizontalMonthAdapter.notifyDataSetChanged();




        //to update image
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i <productList.size() ; i++) {
            if (productList.get(i).getName().equalsIgnoreCase(monthHash.get(0).getId())) {
                products.add(new Product(productList.get(i).getName(), productList.get(i).getUrlImage()));
            }
        }

        sizeOfListElements = products.size();
        first_page = sizeOfListElements; //if its not the size of the list, then the loops keeps working but not for the first element if we scroll to the left at running the program


        adapter = new FlowPageViewAdapter(this, getSupportFragmentManager(), products);
        pager.setPageTransformer(true, new ZoomOutPageTransformer(true));
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        pager.addOnPageChangeListener(adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(0);
        pager.setOffscreenPageLimit(sizeOfListElements);
    }

    @Override
    public void updateMonthOfImage(String id) {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i <productList.size() ; i++) {
            if (productList.get(i).getName().equalsIgnoreCase(id)) {
                products.add(new Product(id, productList.get(i).getUrlImage()));
            }
        }

        sizeOfListElements = products.size();
        first_page = sizeOfListElements; //if its not the size of the list, then the loops keeps working but not for the first element if we scroll to the left at running the program


        adapter = new FlowPageViewAdapter(this, getSupportFragmentManager(), products);
        pager.setPageTransformer(true, new ZoomOutPageTransformer(true));
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        pager.addOnPageChangeListener(adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(0);
        pager.setOffscreenPageLimit(sizeOfListElements);

        //adapter.updateMonthImage(products);
    }
    
    //viewpager 2 coverflow
      //<!-- crousel -->
//     <com.saeed.infiniteflow.lib.FinitePagerContainer
//         android:id="@+id/pager_container"
//         android:layout_width="match_parent"
//         android:layout_height="wrap_content"
//         android:elevation="4dp"
//         android:scaleType="centerCrop"
//         android:layout_marginTop="150dp"
//         android:visibility="visible">

//         <androidx.viewpager2.widget.ViewPager2
//             android:id="@+id/view_pager"
//             android:layout_width="210dp"
//             android:layout_height="210dp"
//             android:layout_gravity="center"
//             android:orientation="horizontal" />

//     </com.saeed.infiniteflow.lib.FinitePagerContainer>
//     pager_container = findViewById(R.id.pager_container);
//         progressBar = findViewById(R.id.progressBar);
//         pager = pager_container.getViewPager();
//      recyclerPagerAdapter = new RecyclerPagerAdapter(MainActivity.this,user.getResult().getYears().get(0).getMonths().get(0).getCards());
//                                     pager.setAdapter(recyclerPagerAdapter);
//                                     //
//                                     pager.setOffscreenPageLimit(user.getResult().getYears().get(0).getMonths().get(0).getCards().size());
//                                     pager_container.setOverlapSlider(0f,0f,0.2f,0f);

}
