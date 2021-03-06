package ru.gdg_siberia.instant_app_tutorial.ui.screen.cities;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import ru.gdg_siberia.instant_app_tutorial.R;
import ru.gdg_siberia.instant_app_tutorial.domain.City;
import ru.gdg_siberia.instant_app_tutorial.ui.base.recycler.BindableViewHolder;
import ru.gdg_siberia.instant_app_tutorial.ui.base.recycler.RecyclerAdapter;

public class CitiesAdapter extends RecyclerAdapter<City> {

    interface OnClickListener {
        void onCityClick(String cityUrl);
    }

    private OnClickListener callback;

    public CitiesAdapter(Context context) {
        super(context);
    }

    @Override
    protected int itemViewResID() {
        return R.layout.city_list_item;
    }

    @Override
    protected BindableViewHolder<City> getItemHolder(View itemView) {
        return new CityViewHolder(itemView);
    }

    public void setOnClickListener(OnClickListener callback) {
        this.callback = callback;
    }

    private class CityViewHolder extends BindableViewHolder<City> {

        private View container;
        private TextView cityNameTv;

        CityViewHolder(View container) {
            super(container);
            this.container = container;
            this.cityNameTv = container.findViewById(R.id.city_name_tv);
        }

        @Override
        public void bind(City bindItem) {
            this.cityNameTv.setText(bindItem.getName());
            this.container.setOnClickListener(view -> {
                if (callback != null) {
                    callback.onCityClick(bindItem.getUrl());
                }
            });
        }
    }
}