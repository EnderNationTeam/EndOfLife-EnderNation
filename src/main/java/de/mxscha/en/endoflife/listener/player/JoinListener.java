package de.mxscha.en.endoflife.listener.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.commands.player.VanishCommand;
import de.mxscha.en.endoflife.utils.manager.home.Home;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import de.mxscha.en.endoflife.utils.scoreboard.DefaultScoreboard;
import de.mxscha.en.endoflife.utils.scoreboard.tablist.PlayerTablist;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        addPlayerToDataBase(player);
        new DefaultScoreboard(player).createScoreboard();
        PlayerTablist.setTablist(player);
        event.setJoinMessage(null);

        if (!player.hasPlayedBefore()) {
            player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8§lKohle Key").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
            Location location = new ConfigLocationUtil("Spawn").loadLocation();
            if (location == null) return;
            player.teleport(location);
        }

        // Vanish
        for(Player vanishPlayer : VanishCommand.getVanishedPlayers()) {
            player.hidePlayer(EndoflifeCore.getInstance(), vanishPlayer);
        }
    }

    private void addPlayerToDataBase(Player player) {
        EndoflifeCore.getInstance().getBackpackManager().initPlayer(player);
        EndoflifeCore.getInstance().getMoneyAPI().initPlayer(player);
        EndoflifeCore.getInstance().getJobAPI().initPlayer(player);
    }
}