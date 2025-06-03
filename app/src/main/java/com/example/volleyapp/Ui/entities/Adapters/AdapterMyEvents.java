package com.example.volleyapp.Ui.entities.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleyapp.R;
import com.example.volleyapp.Ui.entities.ViewHolders.ViewHolderMyEvents;
import com.example.volleyapp.logic.Dto.EventDto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class AdapterMyEvents extends RecyclerView.Adapter<ViewHolderMyEvents> {

    Context context;
    List<EventDto> items;
    private OnItemClickListener listener;

    public AdapterMyEvents(Context context, List<EventDto> items,OnItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public ViewHolderMyEvents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderMyEvents(LayoutInflater.from(context).inflate(R.layout.event_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMyEvents holder, int position) {
        holder.ownerView.setText(items.get(position).getOwner().getLogin());
        holder.placeView.setText(items.get(position).getPlace());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.timeView.setText(Instant.ofEpochMilli(items.get(position).getTimeInMilles()).atZone(ZoneId.of("Europe/Moscow")).toLocalDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG,FormatStyle.SHORT)));
        }
        holder.itemView.setOnClickListener(view -> {
            if (listener!=null){
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
