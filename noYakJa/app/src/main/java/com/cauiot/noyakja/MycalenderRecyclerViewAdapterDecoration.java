package com.cauiot.noyakja;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MycalenderRecyclerViewAdapterDecoration extends RecyclerView.ItemDecoration {

    private final int offset;

    public MycalenderRecyclerViewAdapterDecoration(Context context) {
        this.offset = dpToPx(context, 1);
    }

    private int dpToPx(Context context, int dp) {

        return (int) TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view); //get item index
        int count = state.getItemCount(); //item count

        outRect.left = offset*2;
        outRect.right = offset*2;

        if(position == 0){
            outRect.top = offset*2;
            outRect.bottom = offset;
        }else if(position == count-1){
            outRect.top = offset;
            outRect.bottom = offset*2;
        }else{
            outRect.top = offset;
            outRect.bottom = offset;
        }

    }
}
