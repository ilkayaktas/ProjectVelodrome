package com.ilkayaktas.projectname.views.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iaktas on 26.05.2017.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private RecyclerView rv;
    private BaseActivity activity;
    private List<String> cardContentList;

    public RecyclerViewAdapter(BaseActivity activity) {
        this.activity = activity;
        this.cardContentList = new ArrayList<>();
    }

    public RecyclerViewAdapter(BaseActivity activity, List<String> cardContentList, RecyclerView rv) {
        this.activity = activity;
        this.cardContentList = cardContentList;
        this.rv = rv;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View convertView = inflater.inflate(R.layout.cardview_recycler, parent, false);

        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if(cardContentList.size() == 0 ) return;

        final String text = cardContentList.get(position);

        viewHolder.cardContent.setText(text);
        viewHolder.cardContent.setTypeface(activity.fontGothic);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv.removeView(viewHolder.cardView);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardContentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cv_recycler_card) CardView cardView;
        @BindView(R.id.tv_recycler_cardcontent) TextView cardContent;
        @BindView(R.id.b_recycler_button) Button button;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void addNewItem(String ticker){
        cardContentList.add(ticker);
        notifyItemInserted(cardContentList.size()-1);
    }

    public void clearAll() {
        int size = cardContentList.size();
        cardContentList.clear();
        notifyItemRangeRemoved(0, size);
    }


}
