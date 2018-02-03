package controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.babimaji.stayintouch.R;
import com.example.babimaji.stayintouch.model.Fellow;

import java.util.List;

import view.FellowsViewHolder;

public class FellowsAdapter extends RecyclerView.Adapter<FellowsViewHolder>{

    private List<Fellow> fellowList;

    public FellowsAdapter(List<Fellow> fellowList) {
        this.fellowList = fellowList;
    }

    @Override
    public FellowsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_layout, parent, false);
        return new FellowsViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(FellowsViewHolder holder, int position) {

        Fellow fellow = fellowList.get(position);
        holder.onBind(fellow);
    }

    @Override
    public int getItemCount() {
        return fellowList.size();
    }
}
