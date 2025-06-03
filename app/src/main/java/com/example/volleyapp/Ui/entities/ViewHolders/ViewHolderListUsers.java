package com.example.volleyapp.Ui.entities.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleyapp.R;

public class ViewHolderListUsers extends RecyclerView.ViewHolder {
    public TextView userView;
    public ViewHolderListUsers(@NonNull View itemView) {
        super(itemView);
        userView = itemView.findViewById(R.id.UserField);
    }
}
