package com.cauiot.noyakja;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cauiot.noyakja.placeholder.GuardianContent.GuardianItem;
import com.cauiot.noyakja.databinding.FragmentGuardianBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link GuardianItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyGuardianRecyclerViewAdapter extends RecyclerView.Adapter<MyGuardianRecyclerViewAdapter.ViewHolder> {

    private final List<GuardianItem> mValues;

    public MyGuardianRecyclerViewAdapter(List<GuardianItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentGuardianBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public GuardianItem mItem;

        public ViewHolder(FragmentGuardianBinding binding) {
            super(binding.getRoot());
            mIdView = binding.guardianName;
            mContentView = binding.guardianPhone;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}