package com.thinkmobiles.easyerp.presentation.managers;

import com.thinkmobiles.easyerp.R;

/**
 * Created by Lynx on 1/16/2017.
 */

public abstract class TagHelper {
    /**
     * "tag_bg_red
     * "tag_bg_pink
     * "tag_bg_orange
     * "tag_bg_yellow
     * "tag_bg_green_dark
     * "tag_bg_green_light
     * "tag_bg_blue_light
     * "tag_bg_blue_dark
     * "tag_bg_purple
     * "tag_bg_black
     * "tag_bg_grey
     */

    private static final String COLOR_RED = "bgRed";
    private static final String COLOR_PINK = "bgPink";
    private static final String COLOR_ORANGE = "bgOrange";
    private static final String COLOR_YELLOW = "bgYellow";
    private static final String COLOR_GREEN_DARK = "bgGreenDark";
    private static final String COLOR_GREEN_LIGHT = "bgGreenLight";
    private static final String COLOR_BLUE_LIGHT = "bgBlueLight";
    private static final String COLOR_BLUE_DARK = "bgBlueDark";
    private static final String COLOR_PURPLE = "bgPurple";
    private static final String COLOR_BLACK = "bgBlack";
    private static final String COLOR_GREY = "bgGrey";

    public static final String COLD = "Cold";
    public static final String MEDIUM = "Med";
    public static final String HOT = "Hot";

    public static final String PENDING = "Pending";
    public static final String NEW = "New";
    public static final String IN_PROGRESS = "In Progress";
    public static final String CANCELLED = "Cancelled";
    public static final String DONE = "Done";
    public static final String UNLINKED = "UnLinked";

    public static final String PAID = "Paid";
    public static final String UNPAID = "Unpaid";
    public static final String NOT_APPROVED = "Not Approved";
    public static final String UNPAID_NOT_APPROVED = "Unpaid / Not Approved";
    public static final String INVOICED = "Invoiced";
    public static final String PARTIALLY_PAID = "Partially Paid";
    public static final String DRAFT = "Draft";
    public static final String NEW_ORDER = "New Order";

    public static final String PRIORITY_NEW = "New";
    public static final String PRIORITY_SENT = "Sent";
    public static final String PRIORITY_INTERESTED = "Interested";
    public static final String PRIORITY_NOT_INTERESTED = "Not Interested";
    public static final String PRIORITY_NO_ANSWER = "No Answer";
    public static final String PRIORITY_DONT_CONTACT = "Don't Contact";

    public static final String OPPORTUNITY_NEW = "New";
    public static final String OPPORTUNITY_IN_PROGRESS = "In Progress";
    public static final String OPPORTUNITY_PENDING = "Pending";
    public static final String OPPORTUNITY_DONE = "Done";
    public static final String OPPORTUNITY_CANCELLED = "Cancelled";

    public static int getStatusColorRes(String status) {
        switch (status) {
            case PRIORITY_NEW:
                return R.color.color_status_new;
            case PRIORITY_SENT:
                return R.color.color_status_sent;
            case PRIORITY_INTERESTED:
                return R.color.color_status_interested;
            case PRIORITY_NOT_INTERESTED:
                return R.color.color_status_not_interested;
            case PRIORITY_NO_ANSWER:
                return R.color.color_status_no_answer;
            case PRIORITY_DONT_CONTACT:
                return R.color.color_status_do_not_contact;
            default:
                return R.color.color_status_do_not_contact;
        }
    }

    public static final String PAYMENT = "Payment";
    public static final String REFUND = "Refund";

    public static int getColorResIdByName(String colorName) {
        switch (colorName) {
            case COLOR_RED:
                return R.color.tag_bg_red;
            case COLOR_PINK:
                return R.color.tag_bg_pink;
            case COLOR_ORANGE:
                return R.color.tag_bg_orange;
            case COLOR_YELLOW:
                return R.color.tag_bg_yellow;
            case COLOR_GREEN_DARK:
                return R.color.tag_bg_green_dark;
            case COLOR_GREEN_LIGHT:
                return R.color.tag_bg_green_light;
            case COLOR_BLUE_LIGHT:
                return R.color.tag_bg_blue_light;
            case COLOR_BLUE_DARK:
                return R.color.tag_bg_blue_dark;
            case COLOR_PURPLE:
                return R.color.tag_bg_purple;
            case COLOR_BLACK:
                return R.color.tag_bg_black;
            case COLOR_GREY:
                return R.color.tag_bg_grey;

            case COLD:
                return R.color.color_chips_blue;
            case MEDIUM:
                return R.color.color_chips_orange;
            case HOT:
            case UNPAID:
                return R.color.color_chips_red;

            case DRAFT:
            case UNPAID_NOT_APPROVED:
            case UNLINKED:
            case PENDING:
            case NEW:
            case CANCELLED:
                return R.color.color_chips_grey;
            case REFUND:
            case PARTIALLY_PAID:
            case IN_PROGRESS:
                return R.color.color_chips_light_blue;
            case PAYMENT:
            case PAID:
            case DONE:
                return R.color.color_chips_green;
            default:
                return R.color.tag_bg_grey;
        }
    }

    public static int getOpportunityStatusColorRes(String status) {
        switch (status) {
            case OPPORTUNITY_NEW:
                return R.color.color_status_new;
            case OPPORTUNITY_IN_PROGRESS:
                return R.color.color_status_in_progress;
            case OPPORTUNITY_PENDING:
                return R.color.color_status_not_interested;
            case OPPORTUNITY_DONE:
                return R.color.color_chips_green;
            case OPPORTUNITY_CANCELLED:
                return R.color.color_status_do_not_contact;
            default:
                return R.color.color_status_do_not_contact;
        }
    }

    public static int getWorkflowColorRes(String workflow) {
        switch (workflow) {
            case PAID:
            case DRAFT:
            case INVOICED:
                return R.color.color_status_new;
            case PARTIALLY_PAID:
            case NEW_ORDER:
                return R.color.color_status_in_progress;
            case UNPAID:
            case CANCELLED:
                return R.color.color_status_not_interested;
            case NOT_APPROVED:
                return R.color.color_status_do_not_contact;
            default:
                return R.color.color_status_do_not_contact;
        }
    }
}
