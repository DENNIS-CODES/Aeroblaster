package com.example.hamzafood;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import my.foodOn.app.R;

public class ChefProfileFragment extends Fragment {
    //We are using a static instance of a class that extends Application in android.

    Button postDish;
    ConstraintLayout backimg;
    //setting background images


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chef_prifile,null);
        getActivity().setTitle("Post Dish");
//uploading images in drawable file
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.Background2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ChickenStewandRice),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ChickenTikkaMasala),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.CornishPasty),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.EnglishPancakes),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.EtonMess),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.FishandChips),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.FreshJuice),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.FreshSalad),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.MushroomSoup),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.RoastDinner),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ShepherdsPie),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.SteakandKidneyPie),3000);
//setting animations 
        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        backimg = v.findViewById(R.id.background1);
        backimg.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        postDish =  (Button)v.findViewById(R.id.post_dish);

        postDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), my.grabngo.app.chefFoodPanel.chef_postDish.class));
            }
        });

        return v;
    }
}