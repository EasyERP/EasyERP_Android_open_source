package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.AttachmentDH;

/**
 * Created by Lynx on 2/6/2017.
 */

public class AttachmentVH extends RecyclerVH<AttachmentDH> {

    private ImageView ivAttachmentIcon_VLIA;
    private TextView tvAttachmentName_VLIA;
    private TextView tvAttachmentType_VLIA;

    public AttachmentVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivAttachmentIcon_VLIA = findView(R.id.ivAttachmentIcon_VLIA);
        tvAttachmentName_VLIA = findView(R.id.tvAttachmentName_VLIA);
        tvAttachmentType_VLIA = findView(R.id.tvAttachmentType_VLIA);
    }

    @Override
    public void bindData(AttachmentDH data) {
        Drawable image;
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(data.getItem().name);
        String fileName;
        if(data.getItem().name.contains("."))
            fileName = data.getItem().name.substring(0, data.getItem().name.lastIndexOf("."));
        else
            fileName = data.getItem().name;
        if(!TextUtils.isEmpty(fileExtension)) {
            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
            final String[] mimeInfo = TextUtils.isEmpty(mimeType) ? new String[] {""} : mimeType.split("/");
            switch (mimeInfo[0]) {
                case "text":
                    image = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_mime_text);
                    break;
                case "image":
                    image = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_mime_image);
                    break;
                case "video":
                    image = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_mime_video);
                    break;
                case "audio":
                    image = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_mime_audio);
                    break;
                case "application":
                    image = ContextCompat.getDrawable(itemView.getContext(), mimeInfo[1].equalsIgnoreCase("pdf")
                            ? R.drawable.ic_mime_pdf : R.drawable.ic_mime_application);
                    break;
                default:
                    image = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_mime_application);
                    break;
            }
        } else {
            image = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_mime_application);
        }

        ivAttachmentIcon_VLIA.setImageDrawable(image);
        tvAttachmentName_VLIA.setText(fileName);
        tvAttachmentType_VLIA.setText(TextUtils.isEmpty(fileExtension)? "" : "." + fileExtension);
    }
}
