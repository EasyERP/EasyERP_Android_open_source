package com.thinkmobiles.easyerp.data.model;

import java.util.List;

/**
 * @author Michael Soyma (Created on 3/7/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class ResponseGetTotalItems<T> {

    public List<T> data;
    public int total;
}
