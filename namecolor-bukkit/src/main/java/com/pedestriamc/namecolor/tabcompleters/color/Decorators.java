package com.pedestriamc.namecolor.tabcompleters.color;

import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


sealed interface Decorators {

    @NotNull
    String text();

    boolean allows(@NotNull Permissible permissible);

    @NotNull
    static List<String> getAvailable(@NotNull Permissible permissible) {
        List<String> list = new ArrayList<>();
        for(Decorators color : Color.values()) {
            if(color.allows(permissible)) {
                list.add(color.text());
            }
        }

        for(Decorators style : Style.values()) {
            if(style.allows(permissible)) {
                list.add(style.text());
            }
        }

        return list;
    }

    enum Color implements Decorators {
        BLACK("black"),
        DARK_BLUE("darkblue"),
        DARK_GREEN("darkgreen"),
        DARK_AQUA("darkaqua"),
        DARK_RED("darkred"),
        DARK_PURPLE("darkpurple"),
        GOLD("gold"),
        GRAY("gray"),
        DARK_GRAY("darkgray"),
        BLUE("blue"),
        GREEN("green"),
        AQUA("aqua"),
        RED("red"),
        PINK("pink"),
        YELLOW("yellow"),
        WHITE("white");

        private final Permission permission;
        private final String color;

        Color(@NotNull String color) {
            this.permission = new Permission("namecolor.set.color." + color);
            this.color = color;
        }

        @Override
        @NotNull
        public String text() {
            return color;
        }

        @Override
        public boolean allows(@NotNull Permissible permissible) {
            return permissible.isOp() ||
                    permissible.hasPermission("*") ||
                    permissible.hasPermission("namecolor.*") ||
                    permissible.hasPermission("namecolor.set.*") ||
                    permissible.hasPermission("namecolor.set.color.*") ||
                    permissible.hasPermission(permission);
        }
    }

    enum Style implements Decorators {
        BOLD("bold"),
        ITALIC("italic"),
        UNDERLINE("underline"),
        STRIKE("strike"),
        MAGIC("magic");

        private final Permission permission;
        private final String style;

        Style(@NotNull String style) {
            this.permission = new Permission("namecolor.set.style." + style);
            this.style = style;
        }

        @Override
        public @NotNull String text() {
            return style;
        }

        @Override
        public boolean allows(@NotNull Permissible permissible) {
            return permissible.isOp() ||
                    permissible.hasPermission("*") ||
                    permissible.hasPermission("namecolor.*") ||
                    permissible.hasPermission("namecolor.set.*") ||
                    permissible.hasPermission("namecolor.set.style.*") ||
                    permissible.hasPermission(permission);
        }
    }

}
