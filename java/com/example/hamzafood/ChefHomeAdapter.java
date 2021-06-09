package com.example.hamzafood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import my.foodOn.app.R;
import my.foodOn.app.UpdateDishModel;

public class ChefHomeAdapter extends RecyclerView.Adapter<ChefHomeAdapter.ViewHolder> {//Means you have a incomplete class and you are completing it through extending it

    private Context mcont;
    private List<UpdateDishModel> updateDishModelList;

    public ChefHomeAdapter(Context context , List<UpdateDishModel>updateDishModelList){
        this.updateDishModelList = updateDishModelList;
        this.mcont = context;
    }

    @NonNull
    @Override
    public ChefHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//This method is called right when the adapter is created and is used to initialize your ViewHolder(s).
        View view = LayoutInflater.from(mcont).inflate(R.layout.chefmenu_update_delete,parent,false);// Inflate means reading a layout XML (often given as parameter) to translate them in Java code.
        return new ChefHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefHomeAdapter.ViewHolder holder, int position) {// to display the data at the specified position. This method is used to update the contents of the itemView to reflect the item at the given position.

        final UpdateDishModel updateDishModel = updateDishModelList.get(position);
        holder.dishes.setText(updateDishModel.getDishes());// Sets the text that the TextView is to display
        updateDishModel.getRandomUID();
        holder.itemView.setOnClickListener(new View.OnClickListener() {//
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcont, UpdateDelete_Dish.class);
                intent.putExtra("updatedeletedish",updateDishModel.getRandomUID());
                mcont.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {//getItemCount () - returns The number of items currently available in adapter. 
        return updateDishModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dishes;

        public ViewHolder(@NonNull View itemView) {// A ViewHolder describes an item view and metadata about its place within the RecyclerView
            super(itemView);
            dishes = itemView.findViewById(R.id.dish_name);//It means binding the xml view with java using its id. 
        }
    }
}