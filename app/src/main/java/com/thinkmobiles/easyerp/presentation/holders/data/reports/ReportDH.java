package com.thinkmobiles.easyerp.presentation.holders.data.reports;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.reports.general.Report;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;

/**
 * @author Michael Soyma (Created on 4/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class ReportDH extends RecyclerDH {

    private Report report;
    private UserInfo currentUser;

    public ReportDH(Report report, UserInfo currentUser) {
        this.report = report;
        this.currentUser = currentUser;
    }

    public Report getReport() {
        return report;
    }

    public boolean isFavorite(final String reportId) {
        return currentUser.favorite != null
                && currentUser.favorite.reports != null
                && currentUser.favorite.reports.contains(reportId);
    }
}
