package com.thinkmobiles.easyerp.presentation.utils;


import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StyleSpan;

import com.thinkmobiles.easyerp.data.model.crm.leads.TagItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Address;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.AttachmentItem;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.NoteItem;
import com.thinkmobiles.easyerp.presentation.EasyErpApplication_;
import com.thinkmobiles.easyerp.presentation.custom.RoundedBackgroundSpan;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;

import java.text.Format;
import java.util.ArrayList;
import java.util.Locale;

public abstract class StringUtil {

    private static final String CHANGED_FIELD_CREATION_DATE = "Creation Date";
    private static final String CHANGED_FIELD_CLOSE_DATE    = "Close Date";

    public static String getFullName(String first, String last) {
        return String.format(Locale.ENGLISH,
                "%s %s",
                TextUtils.isEmpty(first) ? "" : first,
                TextUtils.isEmpty(last) ? "" : last
        );
    }

    public static String getAddress(Address address) {
        StringBuilder builder = new StringBuilder();
        if (!TextUtils.isEmpty(address.street))
            builder.append(address.street).append(", ");
        if (!TextUtils.isEmpty(address.city))
            builder.append(address.city).append(", ");
        if (!TextUtils.isEmpty(address.state))
            builder.append(address.state).append(", ");
        if (!TextUtils.isEmpty(address.zip))
            builder.append(address.zip).append(", ");
        if (!TextUtils.isEmpty(address.country))
            builder.append(address.country).append(", ");
        int length = builder.length();
        if (length > 2) builder.replace(length - 3, length - 1, ", ");
        return  builder.toString();
    }

    public static String getField(String field, String holder) {
        return TextUtils.isEmpty(field) ? holder : field;
    }

    public static String getAttachments(ArrayList<AttachmentItem> attachments) {
        StringBuilder builder = new StringBuilder();
        for (AttachmentItem attachment : attachments) {
            builder.append(getAttachmentURL(attachment.shortPas, attachment.name))
                    .append("<br>");
        }
        int length = builder.length();
        return builder.delete(length - 4, length).toString();
    }

    public static String getAttachmentURL(String shortPas, String name) {
        return String.format(Locale.ENGLISH,
                "<a href=\"%s\">%s</a>",
                Constants.BASE_URL + "download/" + shortPas,
                name);
    }

    public static String getClickableUrl(String url, String name) {
        if(!url.startsWith("http://")) url = "http://" + url;
        return String.format(Locale.ENGLISH, "<a href=\"%s\">%s</a>", url, name);
    }

    public static String getNoteAction(NoteItem note) {
        if (note.task != null) {
            return "created task";
        } else if (note.history != null) {
            if (note.history.prevValue == null) {
                return "added";
            } else {
                return "changed";
            }
        } else {
            return "left a note";
        }
    }

    public static String getNoteMessage(NoteItem note) {
        StringBuilder builder = new StringBuilder();
        if (note.task != null) {
            if (!TextUtils.isEmpty(note.task.description))
                builder.append(note.task.description)
                        .append("<br>");
            builder.append("Assigned to ")
                    .append(note.task.assignedTo.fullName);
        } else if (note.history != null) {
            builder.append(note.history.changedField);
            if(note.history.changedField.equalsIgnoreCase(CHANGED_FIELD_CLOSE_DATE) || note.history.changedField.equalsIgnoreCase(CHANGED_FIELD_CREATION_DATE)) {
                String from = "";
                String to = "";
                if(!TextUtils.isEmpty(note.history.prevValue)) from = DateManager.getShortDate(note.history.prevValue);
                if(!TextUtils.isEmpty(note.history.newValue)) to = DateManager.getShortDate(note.history.newValue);
                if(note.history.prevValue != null) {
                    builder.append(" from ")
                            .append(from)
                            .append(" to");
                }
                builder.append(" ").append(to);
            } else {
                if(note.history.prevValue != null) {
                    builder.append(" from ")
                            .append(note.history.prevValue)
                            .append(" to");
                }
                builder.append(" ").append(note.history.newValue);
            }
        } else if (!TextUtils.isEmpty(note.note)){
            builder.append(note.note);
        }
        if (note.attachment != null) {
            builder.append(builder.length() > 0 ? "<p>" : "")
                    .append(StringUtil.getAttachmentURL(note.attachment.shortPas, note.attachment.name));
        }

        return Html.fromHtml(builder.toString()).toString();
    }



    public static SpannableStringBuilder prepareTags(ArrayList<TagItem> tagItems) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

        String between = "   ";
        int tagStart = 0;

        for (TagItem tag : tagItems) {
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

    public static SpannableStringBuilder getSpannedByUser(String userName) {
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append("by ");
        stringBuilder.append(userName);
        stringBuilder.setSpan(boldSpan, 3, stringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return stringBuilder;
    }

    public static String getFormattedPriceFromCent(Format formatter, Double price) {
        return getFormattedPriceFromCent(formatter, price, "$");
    }

    public static String getFormattedPriceFromCent(Format formatter, Double price, String prefix) {
        if (price == null) {
            price = 0d;
        }
        return String.format("%s %s", prefix, formatter.format(price / 100));
    }

    public static String getFormattedPrice(Format formatter, Double price) {
        return getFormattedPriceFromCent(formatter, price, "$");
    }

    public static String getFormattedPrice(Format formatter, Double price, String prefix) {
        if (price == null) {
            price = 0d;
        }
        return String.format("%s %s", prefix, formatter.format(price));
    }
}
