package com.example.andrew_nguyen.smart_mirror.tools;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.andrew_nguyen.smart_mirror.R;

/**
 * Created by andrew_nguyen on 9/6/17.
 */

public class Icon_Translator {
    public Drawable translate(Context ctx, String icon_code, String description) {
        switch (icon_code) {
            case "01d":
                return ctx.getResources().getDrawable(R.drawable.sunny);

            case "01n":
                return ctx.getResources().getDrawable(R.drawable.starry);

            case "02d":
                return ctx.getResources().getDrawable(R.drawable.few_clouds);

            case "02n":
                return ctx.getResources().getDrawable(R.drawable.few_cloudsn);

            case "03d":
                return ctx.getResources().getDrawable(R.drawable.scattered_clouds);

            case "03n":
                return ctx.getResources().getDrawable(R.drawable.scattered_clouds);

            case "04d":
                return ctx.getResources().getDrawable(R.drawable.scattered_clouds);

            case "04n":
                return ctx.getResources().getDrawable(R.drawable.scattered_clouds);

            case "09d":
                return rain_check(description, ctx);

            case "09n":
                return rain_check(description, ctx);

            case "10d":
                return rain_check(description, ctx);

            case "10n":
                return rain_check(description, ctx);

            case "11d":
                return rain_check(description, ctx);

            case "11n":
                return rain_check(description, ctx);

            case "13d":
                return ctx.getResources().getDrawable(R.drawable.snow);

            case "13n":
                return ctx.getResources().getDrawable(R.drawable.snow);

            case "50d":
                if (description.trim().equals("tornado")) {
                    ctx.getResources().getDrawable(R.drawable.tornado);
                } else
                    return ctx.getResources().getDrawable(R.drawable.haze);
                break;
            case "50n":
                if (description.trim().equals("tornado")) {
                    ctx.getResources().getDrawable(R.drawable.tornado);
                } else
                    return ctx.getResources().getDrawable(R.drawable.hazen);
                break;
            default:
                return ctx.getResources().getDrawable(R.drawable.extreme);
        }
        return ctx.getResources().getDrawable(R.drawable.extreme);
    }

    public Drawable rain_check(String description, Context ctx) {
        if (description.trim().equals("heavy intensity drizzle rain") ||
                description.trim().equals("shower rain and drizzle") ||
                description.trim().equals("moderate rain") ||
                description.trim().equals("shower rain") ||
                description.trim().equals("light intensity shower rain")) {
            return ctx.getResources().getDrawable(R.drawable.medium_rain);
        } else if (description.trim().equals("heavy shower rain and drizzle") ||
                description.trim().equals("heavy intensity shower rain") ||
                description.trim().equals("ragged shower rain") ||
                description.trim().equals("heavy intensity rain") ||
                description.trim().equals("very heavy rain") ||
                description.trim().equals("extreme rain") ||
                description.trim().equals("shower drizzle")) {
            return ctx.getResources().getDrawable(R.drawable.heavy_rain);
        } else if (description.trim().equals("thunderstorm with light rain") ||
                description.trim().equals("thunderstorm with rain") ||
                description.trim().equals("light thunderstorm") ||
                description.trim().equals("thunderstorm") ||
                description.trim().equals("thunderstorm with light drizzle") ||
                description.trim().equals("thunderstorm with drizzle") ||
                description.trim().equals("thunderstorm with heavy drizzle")) {
            return ctx.getResources().getDrawable(R.drawable.light_thunderstorm);
        } else if (description.trim().equals("ragged thunderstorm") ||
                description.trim().equals("heavy thunderstorm")) {
            return ctx.getResources().getDrawable(R.drawable.medium_thunderstorm);
        } else {
            return ctx.getResources().getDrawable(R.drawable.light_rain);
        }
    }
}
