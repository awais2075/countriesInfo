package info.countries.countriesinfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import info.countries.countriesinfo.R;
import info.countries.countriesinfo._interface.ItemClickListener;
import info.countries.countriesinfo.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyHolder> implements Filterable {

    private Context context;
    private List<Country> countryList;
    private List<Country> filteredList;
    private ItemClickListener listener;


    public CountryAdapter(Context context, List<Country> countryList, ItemClickListener listener) {
        this.context = context;
        this.countryList = countryList;
        this.filteredList = countryList;
        this.listener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.bind(filteredList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new ItemFilter();
    }

    class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString().toLowerCase();
            if (charString.isEmpty()) {
                filteredList = countryList;
            } else {
                List<Country> fList = new ArrayList<>();
                for (int i = 0; i < countryList.size(); i++) {
                    String countryName = countryList.get(i).getName().toLowerCase();
                    String countryCapital = countryList.get(i).getCapital().toLowerCase();
                    if (countryName.contains(charString) || countryCapital.contains(charString)) {
                        fList.add(countryList.get(i));
                    }
                }
                filteredList = fList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            filteredList = (List<Country>) filterResults.values;
            notifyDataSetChanged();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View item_country;
        private ImageView imageView_country;
        private TextView textView_countryName;
        private TextView textView_capital;
        private TextView textView_region;

        public MyHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View view) {
            item_country = view.findViewById(R.id.item_country);
            imageView_country = view.findViewById(R.id.imageView_country);
            textView_countryName = view.findViewById(R.id.textView_name);
            textView_capital = view.findViewById(R.id.textView_capital);
            //textView_region = view.findViewById(R.id.textView_region);

            item_country.setOnClickListener(this);
        }

        void bind(Country country) {
            String alpha2Code = country.getAlpha2Code().toLowerCase();
            String imageUrl = "https://www.countryflags.io/" + alpha2Code + "/shiny/64.png";

            Glide.with(context)
                    .load(imageUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView_country);
            textView_countryName.setText(country.getName());
            textView_capital.setText(country.getCapital());
            //textView_region.setText(country.getRegion());
        }

        @Override
        public void onClick(View v) {
            listener.itemClicked(filteredList.get(getAdapterPosition()));
        }
    }
}
