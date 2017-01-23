package com.thinkmobiles.easyerp.presentation.utils;


import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Xml;

import com.thinkmobiles.easyerp.data.model.crm.leads.LeadTag;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.LeadAddress;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.LeadAttachment;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication_;
import com.thinkmobiles.easyerp.presentation.custom.RoundedBackgroundSpan;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.internal.Util;

public abstract class StringUtil {


    public static String getFullName(String first, String last) {
        return String.format(Locale.ENGLISH,
                "%s %s",
                TextUtils.isEmpty(first) ? "" : first,
                TextUtils.isEmpty(last) ? "" : last
        );
    }

    public static String getAddress(LeadAddress address) {
        StringBuilder builder = new StringBuilder();
        if (!TextUtils.isEmpty(address.zip))
            builder.append(address.zip).append(", ");
        if (!TextUtils.isEmpty(address.country))
            builder.append(address.country).append(", ");
        if (!TextUtils.isEmpty(address.state))
            builder.append(address.state).append(", ");
        if (!TextUtils.isEmpty(address.city))
            builder.append(address.city).append(", ");
        if (!TextUtils.isEmpty(address.street))
            builder.append(address.street).append(", ");
        int length = builder.length();
        if (length > 2) builder.replace(length - 3, length - 1, ", ");
        return  builder.toString();
    }

    public static String getField(String field, String holder) {
        return TextUtils.isEmpty(field) ? holder : field;
    }

    public static String getAttachments(ArrayList<LeadAttachment> attachments) {
        StringBuilder builder = new StringBuilder();
        for (LeadAttachment attachment : attachments) {
            builder.append(String.format(Locale.ENGLISH,
                    "<a href=\"%s\">%s</a><p>",
                    Constants.BASE_URL + "download/" + attachment.shortPas,
                    attachment.name));
        }
        int length = builder.length();
        return builder.delete(length - 4, length - 1).toString();
    }


    public static SpannableStringBuilder prepareTags(ArrayList<LeadTag> leadTags) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

        String between = " ";
        int tagStart = 0;

        for (LeadTag tag : leadTags) {
            // Append tag and space after
            stringBuilder.append(tag.name);
            stringBuilder.append(between);

            // Set span for tag
            RoundedBackgroundSpan tagSpan = new RoundedBackgroundSpan(EasyErpApplication_.getInstance(), TagHelper.getColorResIdByName(tag.color), Color.WHITE);
            stringBuilder.setSpan(tagSpan, tagStart, tagStart + tag.name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Update to next tag start
            tagStart += tag.name.length() + between.length();
        }
        return stringBuilder;
    }
}
