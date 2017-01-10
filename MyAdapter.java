package com.example.hcd_fresher048.recyclerviewsearchdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable{
    private static final String TAG = MyAdapter.class.getSimpleName();
    private Context context;
    private MyOnClick myOnClick;
    private final List<Data> datas;

    private final List<Data> datasFiltered;
    private MyFilter myFilter;

    public MyAdapter(Context context, List<Data> datas) {
        this.datas = datas;
        this.context = context;
        this.datasFiltered = new ArrayList<>(datas);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    public void setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(datasFiltered.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return datasFiltered.size();
    }

    @Override
    public Filter getFilter() {
        if (myFilter == null) {
            myFilter = new MyFilter(this, datas);
        }
        return myFilter;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (myOnClick != null) {
                        myOnClick.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    interface MyOnClick {
        void onClick(int position);
    }

    class MyFilter extends Filter {
        private final MyAdapter myAdapter;
        private final List<Data> datas;
        private final List<Data> datasFiltered;

        MyFilter(MyAdapter myAdapter, List<Data> datas) {
            this.myAdapter = myAdapter;
            this.datas = new LinkedList<>(datas);
            this.datasFiltered = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            datasFiltered.clear();
            FilterResults filterResults = new FilterResults();

            if (charSequence.length() == 0) {
                datasFiltered.addAll(datas);
            } else {
                final String searchKey = charSequence.toString().toLowerCase().trim();
                for (final Data data : datas) {
                    if (data.getContent().toLowerCase().contains(searchKey)) {
                        datasFiltered.add(data);
                    }
                }
            }
            filterResults.values = datasFiltered;
            filterResults.count = datasFiltered.size();

            Log.d(TAG, "performFiltering: " + datasFiltered.size());
            for (int i = 0; i < datasFiltered.size(); i++) {
                Log.d(TAG, "performFiltering: " + i + ": " + datasFiltered.get(i).getContent());
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            myAdapter.datasFiltered.clear();
            myAdapter.datasFiltered.addAll((ArrayList<Data>) filterResults.values);
            this.myAdapter.notifyDataSetChanged();
        }
    }
}
