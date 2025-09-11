package com.pedestriamc.namecolor.tabcompleters.color;

import com.pedestriamc.namecolor.tabcompleters.AbstractTabCompleter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NameColorTabCompleter extends AbstractTabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!sender.isOp() && !sender.hasPermission("namecolor.*") && !sender.hasPermission("namecolor.set")) {
            return EMPTY;
        }

        return switch(args.length) {
            case 0, 1 -> {
                List<String> list = Decorators.getAvailable(sender);
                list.add("help");
                yield filter(list, args[0]);
            }
            case 2, 3, 4, 5, 6, 7 -> {
                if(sender.hasPermission("namecolor.set.style")) {
                    List<String> list = collect(Decorators.getAvailable(sender), getPlayerNames());
                    yield filter(list, args[args.length - 1]);
                }
                yield EMPTY;
            }

            default -> EMPTY;
        };
    }
}
