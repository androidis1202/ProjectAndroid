package com.example.foodgo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.foodgo.Entity.Category;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragement extends Fragment {

    private List<Category> categoryList;
    private ListViewAdapter myAdapter;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listview, container, false);
        listView = rootView.findViewById(R.id.list_view);
        categoryList = new ArrayList<>();
        myAdapter = new ListViewAdapter( getActivity(), R.layout.listview_layout, categoryList);
        categoryList.add(new Category(1, "Drink Menu", R.drawable.drink));
        categoryList.add(new Category(2,"Fast Food Menu", R.drawable.fastfood));
        categoryList.add(new Category(3,"Soup Menu", R.drawable.soup));
        categoryList.add(new Category(4, "Main Meal Menu",R.drawable.mainmenu));
        listView.setAdapter(myAdapter);

        return rootView;
    }


}
