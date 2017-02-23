package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.LibraryInfo;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.LibraryDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 2/23/2017.
 */

public class LibraryVH extends RecyclerVH<LibraryDH> {

    private TextView tvLibraryName_VLIL;
    private TextView tvLibraryVersion_VLIL;
    private TextView tvLibraryAuthor_VLIL;
    private TextView tvLibraryLicense_VLIL;

    public LibraryVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        itemView.setOnClickListener(null);

        if(listener != null) {
            RxView.clicks(itemView)
                    .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> listener.onClick(itemView, getAdapterPosition(), getItemViewType()));
        }

        tvLibraryName_VLIL = findView(R.id.tvLibraryName_VLIL);
        tvLibraryVersion_VLIL = findView(R.id.tvLibraryVersion_VLIL);
        tvLibraryAuthor_VLIL = findView(R.id.tvLibraryAuthor_VLIL);
        tvLibraryLicense_VLIL = findView(R.id.tvLibraryLicense_VLIL);
    }

    @Override
    public void bindData(LibraryDH data) {
        LibraryInfo info = data.getLibraryInfo();
        tvLibraryName_VLIL.setText(info.name);
        tvLibraryVersion_VLIL.setText(info.version);
        tvLibraryAuthor_VLIL.setText(info.author);
        tvLibraryLicense_VLIL.setText(info.license);
    }
}
