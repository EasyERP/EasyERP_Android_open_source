package com.thinkmobiles.easyerp.presentation.managers;

import android.graphics.Color;

/**
 * Created by Asus_Dev on 1/25/2017.
 */

public abstract class ColorGenerateManager {

    public static int[] generateGradientBetweenColors(final int countColors, final int colorFrom, final int colorTo) {
        final int[] colors = new int[countColors];

        int rStart = Color.red(colorFrom);
        int gStart = Color.green(colorFrom);
        int bStart = Color.blue(colorFrom);

        int rEnd = Color.red(colorTo);
        int gEnd = Color.green(colorTo);
        int bEnd = Color.blue(colorTo);

        if (countColors <= 1)
            return new int[] {colorFrom, colorTo};
        else {
            int redStep = (rEnd - rStart) / (countColors - 1);
            int greenStep = (gEnd - gStart) / (countColors - 1);
            int blueStep = (bEnd - bStart) / (countColors - 1);

            for (int i = 0; i < countColors; i++)
                colors[i] = Color.rgb(rStart + i * redStep, gStart + i * greenStep, bStart + i * blueStep);

            return colors;
        }
    }

}
