package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class TpaHereCommand implements CommandExecutor {

    private HashMap<Player, Player> request = new HashMap<>();
    private ArrayList<Player> tpaSave = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            switch (args.length) {
                case 0:
                    player.sendMessage(Messages.PLS_USE.get() + "§e/tpahere §8[§espielername§8] §7/ §8[§aaccept§8] §8[§espielername§8]");
                    break;
            }
        }
        return false;
    }
}