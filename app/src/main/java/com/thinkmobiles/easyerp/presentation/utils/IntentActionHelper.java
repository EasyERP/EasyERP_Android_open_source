package com.thinkmobiles.easyerp.presentation.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author Michael Soyma (Created on 2/27/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public abstract class IntentActionHelper {

    public static final String FORMAT_SKYPE             = "skype:%s";
    public static final String FORMAT_LINKEDIN_PROFILE  = "https://www.linkedin.com/profile/view?id=%s";
    public static final String FORMAT_LINKEDIN_COMPANY  = "https://www.linkedin.com/company/%s/";
    public static final String FORMAT_FACEBOOK          = "https://www.facebook.com/%s/";
    public static final String FORMAT_YOUTUBE           = "https://www.youtube.com/user/%s/";
    public static final String FORMAT_TWITTER           = "https://twitter.com/%s/";

    public static final String FORMAT_MARKET            = "market://details?id=%s";
    public static final String FORMAT_LINK_MARKET       = "https://play.google.com/store/apps/details?id=%s";

    public static void callViewIntent(final Context context, final String uriPath, final String alternativeUriPath) {
        final Uri uri = Uri.parse(uriPath);
        final Uri uriAlternative = TextUtils.isEmpty(alternativeUriPath) ? null : Uri.parse(alternativeUriPath);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(sendIntent);
        } catch (ActivityNotFoundException e) {
            if (uriAlternative != null)
                context.startActivity(new Intent(Intent.ACTION_VIEW, uriAlternative));
            else Toast.makeText(context, "You don't have the application to perform this operation", Toast.LENGTH_SHORT).show();
        }
    }

    public static void callSendEmailIntent(final Context context, final String email, final String subject) {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("vnd.android.cursor.item/email");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
        if (subject != null)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        context.startActivity(emailIntent);
    }

    public static void callDialIntent(final Context context, final String phoneNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
        try {
            context.startActivity(dialIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "You don't have the application to perform this operation", Toast.LENGTH_SHORT).show();
        }
    }
}
