package com.example.hamzafood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import my.foodOn.app.R;

public class ChefOrderFragment extends Fragment {
//Means you have a incomplete class and you are completing it through extending it 
    @Nullable//Nullable Denotes that a parameter, field or method return value can be null.
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//is called by Android once the Fragment should inflate a view. 
        View v = inflater.inflate(R.layout.fragment_chef_orders,null);
        getActivity().setTitle("New Orders");
        return v;
    }
}