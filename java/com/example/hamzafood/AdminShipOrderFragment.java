package com.example.hamzafood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import my.foodOn.app.R;

public class AdminShipOrderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_Adminshiporder, null);//Inflate is a method which parses the first parameter view and puts it inside the second parameter ViewGroup.
        getActivity().setTitle("Ship Orders");//getActivity () in a Fragment returns the Activity the Fragment is currently associated with.
        return v;
    }
}