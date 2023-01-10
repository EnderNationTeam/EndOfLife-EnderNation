package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import de.mxscha.en.endoflife.utils.manager.teleport.Teleport;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                try {
                    Location spawn = new ConfigLocationUtil("Spawn").loadLocation();
                    Teleport teleport = new Teleport(player, spawn).setSound(Sound.ENTITY_PLAYER_LEVELUP).setTeleportTime(3);
                    teleport.setBeforeMessage(Messages.PREFIX.get() + "§7Du wirst in §e" + teleport.getTeleportTime() + " Sekunden §7teleportiert!");
                    teleport.setAfterMessage(Messages.PREFIX.get() + "§7Du bist nun in der Spawn!");
                    teleport.teleport();
                } catch (Exception e) {
                    player.sendMessage(Messages.PREFIX.get() + "§cDer Spawn wurde nicht gesetzt!");
                }
            } else
                player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/spawn§c!");
        }
        return false;
    }
}