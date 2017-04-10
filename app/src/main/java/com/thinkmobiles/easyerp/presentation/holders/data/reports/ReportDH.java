package com.thinkmobiles.easyerp.presentation.holders.data.reports;

import com.michenko.simpleadapter.RecyclerDH;
import com.thinkmobiles.easyerp.data.model.reports.general.Report;

/**
 * @author Michael Soyma (Created on 4/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class ReportDH extends RecyclerDH {

    private Report report;

    public ReportDH(Report report) {
        this.report = report;
    }

    public Report getReport() {
        return report;
    }
}
