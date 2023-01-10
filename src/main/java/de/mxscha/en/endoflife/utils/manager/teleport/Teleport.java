package de.mxscha.en.endoflife.utils.manager.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Teleport {

    static Map<Player, BukkitTask> inTeleport = new HashMap();

    final Player player;
    final Location location;

    String beforeTeleportMessage = Messages.PREFIX.get() + "§7Du wirst in ein paar Sekunden teleportiert! §cBitte bewege dich nicht!";
    String afterTeleportMessage = Messages.PREFIX.get() + "§7Du hast dein Zeil erreicht!";
    Sound sound = Sound.ENTITY_PLAYER_LEVELUP;
    int teleportTime = 3;
    BukkitTask bukkitTask;

    public static Set<Player> getInTeleport() {
        return inTeleport.keySet();
    }

    public static void cancelTeleport(Player player) {
        player.sendMessage(Messages.PREFIX.get() + "§cTeleport abgebrochen!");
        inTeleport.get(player).cancel();
        inTeleport.remove(player);
    }

    public Teleport(Player player, Location location) {
        this.player = player;
        this.location = location;
    }

    public String getBeforeTeleportMessage() {
        return beforeTeleportMessage;
    }

    public String getAfterTeleportMessage() {
        return afterTeleportMessage;
    }

    public Sound getSound() {
        return sound;
    }

    public int getTeleportTime() {
        return teleportTime;
    }

    public Teleport setBeforeMessage(String beforeTeleportMessage) {
        this.beforeTeleportMessage = beforeTeleportMessage;
        return this;
    }

    public Teleport setAfterMessage(String afterTeleportMessage) {
        this.afterTeleportMessage = afterTeleportMessage;
        return this;
    }

    public Teleport setSound(Sound sound) {
        this.sound = sound;
        return this;
    }

    public Teleport setTeleportTime(int teleportTime) {
        this.teleportTime = teleportTime;
        return this;
    }

    public void teleport() {
        if(inTeleport.containsKey(player)) {
            player.sendMessage(Messages.PREFIX.get() + "§cDu kannst dich nicht teleportieren, da du bereits in einem Teleport bist!");
            return;
        }

        player.sendMessage(beforeTeleportMessage);
        player.sendMessage(Messages.PREFIX.get() + "§cBewege dich nicht!");
        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    player.teleport(location);
                } catch (IllegalArgumentException e) {
                    player.sendMessage(Messages.PREFIX.get() + "§cDu kannst dich nicht in diese Welt teleportieren!");
                    inTeleport.remove(player);
                    return;
                }

                inTeleport.remove(player);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                player.sendMessage(afterTeleportMessage);
            }
        }.runTaskLater(EndoflifeCore.getInstance(), 20 * teleportTime);

        inTeleport.put(player, bukkitTask);
    }


}
