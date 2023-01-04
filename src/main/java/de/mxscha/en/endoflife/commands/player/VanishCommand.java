package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {

    private static final ArrayList<Player> vanish = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.vanish")) {
                if (vanish.contains(player)) {
                    setUnVanished(player);
                } else {
                    setVanished(player);
                }
            } else
                player.sendMessage(Messages.NO_PERM.get());
        }
        return false;
    }

    private void setUnVanished(Player player) {
        vanish.remove(player);
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.setFlying(false);
            player.setAllowFlight(false);
        }
        player.sendMessage(Messages.SET_UNVANISHED.get());
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.showPlayer(EndoflifeCore.getInstance(), player);
        }
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
    }

    private void setVanished(Player player) {
        vanish.add(player);
        player.sendMessage(Messages.SET_VANISHED.get());
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.hidePlayer(EndoflifeCore.getInstance(), player);
        }
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.setFlying(true);
            player.setAllowFlight(true);
        }
        player.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(1200000000, 1));
    }

    public static ArrayList<Player> getVanishedPlayers() {
        return vanish;
    }
}