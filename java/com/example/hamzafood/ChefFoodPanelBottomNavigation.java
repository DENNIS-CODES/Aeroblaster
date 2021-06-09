package com.example.hamzafood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import my.foodOn.app.chefFoodPanel.ChefHomeFragment;
import my.foodOn.app.chefFoodPanel.ChefOrderFragment;
import my.foodOn.app.chefFoodPanel.ChefPendingOrderFragment;
import my.foodOn.app.chefFoodPanel.ChefProfileFragment;

public class ChefFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{//Means you have a incomplete class and you are completing it through extending it
//oncreate() gets called and savedInstanceState will be non-null if your Activity and it was terminated in a scenario (visual view) described above. Your app can then grab (catch) the data from savedInstanceState and regenerate your Activity Highly active question
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_food_panel__bottom_navigation);//At the run time device will pick up their layout based on the id given in setcontentview () method
        BottomNavigationView navigationView = findViewById(R.id.chef_bottom_navigation);//finds the view from the layout resource file that are attached with current Activity.
        navigationView.setOnNavigationItemSelectedListener(this);//If Destinations are more than 5 then use the Navigation Drawer. When the user taps on the icon it will change the top-level view accordingly
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.chefHome:
                fragment=new ChefHomeFragment();
                break;
            case R.id.PendingOrders:
                fragment=new ChefPendingOrderFragment();
                break;
            case R.id.Orders:
                fragment=new ChefOrderFragment();
                break;
            case R.id.chefProfile:
                fragment=new ChefProfileFragment();
                break;
        }
        return loadcheffragment(fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }
}