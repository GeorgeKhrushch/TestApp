package com.test.khrushch.testapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.khrushch.testapplication.R;
import com.test.khrushch.testapplication.preferences.SharedPrefsUtils;
import com.test.khrushch.testapplication.structure.CityInfo;
import com.test.khrushch.testapplication.ui.presenters.CloseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private List<CityInfo> cityInfoList;
    private CloseFragment  closeFragment;

    public CitiesAdapter(List<CityInfo> cityInfoList, CloseFragment closeFragment) {
        setHasStableIds(true);
        this.cityInfoList = cityInfoList;
        this.closeFragment = closeFragment;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_city, parent, false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        CityInfo info = cityInfoList.get(position);
        holder.textViewInfo.setText(info.getName());
        holder.itemView.setOnClickListener(v -> {
            saveSelectedCityId(holder.textViewInfo.getContext(), info.getId());
            closeFragment.close();
        });
    }

    private void saveSelectedCityId(Context context, int id){
        SharedPrefsUtils.saveLastCityId(context, id);
    }

    @Override
    public int getItemCount() {
        return cityInfoList.size();
    }

    @Override
    public long getItemId(int position) {
        return cityInfoList.get(position).getId();
    }

    class CityViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvListItemCityInfo) TextView textViewInfo;

        CityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
