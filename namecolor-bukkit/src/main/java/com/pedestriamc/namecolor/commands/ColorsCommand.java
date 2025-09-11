package com.pedestriamc.namecolor.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ColorsCommand implements CommandExecutor {

    private static final String BULLET = ChatColor.GRAY + " • ";
    private static final String ARROW = ChatColor.GRAY + " ➽ ";

    private static final String[] MESSAGE = new String[]{
            color("$8[$dNameColor$8] $7| $fColors"),
            entry(ChatColor.BLACK + "Black", "&0"),
            entry(ChatColor.DARK_BLUE + "Dark Blue", "&1"),
            entry(ChatColor.DARK_GREEN + "Dark Green", "&2"),
            entry(ChatColor.DARK_AQUA + "Dark Aqua", "&3"),
            entry(ChatColor.DARK_RED + "Dark Red", "&4"),
            entry(ChatColor.DARK_PURPLE + "Purple", "&5"),
            entry(ChatColor.GOLD + "Gold", "&6"),
            entry(ChatColor.GRAY + "Gray", "&7"),
            entry(ChatColor.DARK_GRAY + "Dark Gray", "&8"),
            entry(ChatColor.BLUE + "Blue", "&9"),
            entry(ChatColor.GREEN + "Green", "&a"),
            entry(ChatColor.AQUA + "Aqua", "&b"),
            entry(ChatColor.RED + "Red", "&c"),
            entry(ChatColor.LIGHT_PURPLE + "Pink", "&d"),
            entry(ChatColor.YELLOW + "Yellow", "&e"),
            entry(ChatColor.WHITE + "White", "&f"),
            color("$e$l(!) $r$fHEX codes also supported!")
    };

    @NotNull
    private static String color(@NotNull String in) {
        return ChatColor.translateAlternateColorCodes('$', in);
    }

    @NotNull
    private static String entry(@NotNull String color, @NotNull String code) {
        return color(BULLET + color + ARROW + ChatColor.WHITE + code);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        sender.sendMessage(MESSAGE);
        return true;
    }

}
