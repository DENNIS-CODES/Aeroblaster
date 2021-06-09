package com.example.hamzafood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import my.foodOn.app.R;

public class CustomerOrdersFragment extends Fragment {

    @Nullable//can be null
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customerorders,null);//In this context, Inflate means reading a layout XML
        getActivity().setTitle("Orders");
        return v;
    }
}