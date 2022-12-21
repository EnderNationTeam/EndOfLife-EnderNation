package de.mxscha.en.endoflife.listener.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.scoreboard.DefaultScoreboard;
import de.mxscha.en.endoflife.utils.scoreboard.tablist.PlayerTablist;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        addPlayerToDataBase(player);
        new DefaultScoreboard(player).createScoreboard();
        PlayerTablist.setTablist(player);
        event.setJoinMessage(null);
    }

    private void addPlayerToDataBase(Player player) {
        EndoflifeCore.getInstance().getBackpackManager().initPlayer(player);
        EndoflifeCore.getInstance().getMoneyAPI().initPlayer(player);
        EndoflifeCore.getInstance().getJobAPI().initPlayer(player);
    }
}