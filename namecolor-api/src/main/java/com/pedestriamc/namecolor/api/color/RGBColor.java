package com.pedestriamc.namecolor.api.color;

import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.util.Locale;

public final class RGBColor {

    private RGBColor() {}

    /**
     * Returns a color String formatted §#RRGGBB
     * @param color The Color
     * @return A String
     */
    @NotNull
    public static String of(@NotNull Color color) {
        return ChatColor.COLOR_CHAR + hex(color);
    }

    @NotNull
    public static String of(@NotNull ChatColor color) {
        return of(color.getColor());
    }

    // https://stackoverflow.com/questions/3607858/convert-a-rgb-color-value-to-a-hexadecimal-string
    @NotNull
    private static String hex(@NotNull Color color) {
        String buf = Integer.toHexString(color.getRGB());
        return "#" + buf.substring(buf.length() - 6).toUpperCase(Locale.ROOT);
    }

}
