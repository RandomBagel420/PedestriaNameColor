package com.pedestriamc.namecolor.listeners;

import com.pedestriamc.namecolor.NameColor;
import com.pedestriamc.namecolor.user.User;
import com.pedestriamc.namecolor.user.UserUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class NameFormattingListener implements Listener {

    private final NameColor nameColor;
    private final UserUtil userUtil;

    public NameFormattingListener(@NotNull NameColor nameColor) {
        this.nameColor = nameColor;
        userUtil = nameColor.getUserUtil();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(@NotNull PlayerJoinEvent event) {
        if(!nameColor.getConfig().getBoolean("format-join-leave-messages", true)) {
            return;
        }

        String message = event.getJoinMessage();
        if(message == null || message.isBlank()) {
            return;
        }

        event.setJoinMessage(replaceName(message, event.getPlayer()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(@NotNull PlayerQuitEvent event) {
        if(!nameColor.getConfig().getBoolean("format-join-leave-messages", true)) {
            return;
        }

        String message = event.getQuitMessage();
        if(message == null || message.isBlank()) {
            return;
        }

        event.setQuitMessage(replaceName(message, event.getPlayer()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(@NotNull PlayerDeathEvent event) {
        if(!nameColor.getConfig().getBoolean("format-death-messages", true)) {
            return;
        }

        String message = event.getDeathMessage();
        if(message == null || message.isBlank()) {
            return;
        }

        Player player = event.getEntity();
        message = replaceName(message, player);

        Player killer = player.getKiller();
        if(killer != null) {
            message = replaceName(message, killer);
        }

        event.setDeathMessage(message);
    }

    @NotNull
    private String replaceName(@NotNull String message, @NotNull Player player) {
        User user = userUtil.getUser(player.getUniqueId());
        String displayName = user.getDisplayName();

        message = message.replace(player.getName(), displayName);

        return message;
    }
}
