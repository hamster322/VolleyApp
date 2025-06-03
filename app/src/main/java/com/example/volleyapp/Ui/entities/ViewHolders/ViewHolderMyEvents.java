package com.example.volleyapp.Ui.entities.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleyapp.R;

public class ViewHolderMyEvents extends RecyclerView.ViewHolder {
    public TextView ownerView,placeView,timeView;
    public ViewHolderMyEvents(@NonNull View itemView) {
        super(itemView);
        ownerView = itemView.findViewById(R.id.owner);
        placeView = itemView.findViewById(R.id.place);
        timeView = itemView.findViewById(R.id.time);
    }
}
