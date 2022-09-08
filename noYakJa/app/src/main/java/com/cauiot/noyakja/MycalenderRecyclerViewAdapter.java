package com.cauiot.noyakja;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cauiot.noyakja.placeholder.calenderContent.PlaceholderItem;
import com.cauiot.noyakja.databinding.FragmentCalenderBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MycalenderRecyclerViewAdapter extends RecyclerView.Adapter<MycalenderRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;

    public MycalenderRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentCalenderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) { //여기서 화면 값 설정
        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.checkMorning.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView checkMorning;
        public final TextView checkLunch;
        public final TextView checkDinner;

        public PlaceholderItem mItem;

        public ViewHolder(FragmentCalenderBinding binding) {
            super(binding.getRoot());
            mIdView = binding.date;
            checkMorning = binding.checkMorning;
            checkLunch = binding.checkLunch;
            checkDinner = binding.checkDinner;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + checkMorning.getText() + "'" + checkLunch.getText() + "'" + checkDinner.getText() + "'";
        }
    }
}