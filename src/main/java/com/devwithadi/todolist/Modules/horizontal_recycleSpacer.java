package com.devwithadi.todolist.Modules;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class horizontal_recycleSpacer extends RecyclerView.ItemDecoration {
    final  int Space;

    public horizontal_recycleSpacer(int space) {
        Space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right=Space;

    }
}


