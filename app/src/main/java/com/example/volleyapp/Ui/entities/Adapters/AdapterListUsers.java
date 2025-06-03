package com.example.volleyapp.Ui.entities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleyapp.R;
import com.example.volleyapp.Ui.entities.ViewHolders.ViewHolderListUsers;
import com.example.volleyapp.logic.Dto.UserDto;

import java.util.List;

public class AdapterListUsers extends RecyclerView.Adapter<ViewHolderListUsers> {
    Context context;
    List<UserDto> items;

    public AdapterListUsers(Context context, List<UserDto> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolderListUsers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderListUsers(LayoutInflater.from(context).inflate(R.layout.view_for_list_users,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListUsers holder, int position) {
        holder.userView.setText(items.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
