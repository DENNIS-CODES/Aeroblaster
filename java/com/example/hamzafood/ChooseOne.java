package com.example.hamzafood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseOne extends AppCompatActivity {

    Button Chef,Customer,Admin;
    Intent intent;
    String type;
    ConstraintLayout bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);
//using uploaded images from drawable files
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.Background1),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.BeefWellington),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ChickenStewandRice),3000);
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


        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        bgimage = findViewById(R.id.back3);
        bgimage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        Chef = (Button)findViewById(R.id.chef);
        Admin = (Button)findViewById(R.id.delivery);
        Customer = (Button)findViewById(R.id.customer);


        Chef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemail  = new Intent(ChooseOne.this,Cheflogin.class);
                    startActivity(loginemail);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphone  = new Intent(ChooseOne.this,Chefloginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Register  = new Intent(ChooseOne.this,ChefRegistration.class);
                    startActivity(Register);
                }
            }
        });

        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("Email")){
                    Intent loginemailcust  = new Intent(ChooseOne.this,Login.class);
                    startActivity(loginemailcust);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphonecust  = new Intent(ChooseOne.this,Loginphone.class);
                    startActivity(loginphonecust);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Registercust  = new Intent(ChooseOne.this,Registration.class);
                    startActivity(Registercust);
                }

            }
        });

        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("Email")){
                    Intent loginemail = new Intent(ChooseOne.this,Admin_Login.class);
                    startActivity(loginemail);
                    finish();
                }
                if(type.equals("Phone")){
                    Intent loginphone  = new Intent(ChooseOne.this,Admin_Loginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Registerdelivery  = new Intent(ChooseOne.this,Admin_Registration.class);
                    startActivity(Registerdelivery);
                }

            }
        });

    }
}