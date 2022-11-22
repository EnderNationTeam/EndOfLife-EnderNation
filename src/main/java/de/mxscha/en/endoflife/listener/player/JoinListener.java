package de.mxscha.en.endoflife.listener.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.job.JobActionBarInfoManager;
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
        addToMoneySystem(player);
        addToJobSystem(player);
        new DefaultScoreboard(player);
        PlayerTablist.setTablist(player);
        event.setJoinMessage(null);
    }

    private void addToMoneySystem(Player player) {
        EndoflifeCore.getInstance().getBackpackManager().initPlayer(player.getUniqueId());
        if(!EndoflifeCore.getInstance().getMoneyAPI().isUserExists(player.getUniqueId()))
            EndoflifeCore.getInstance().getMoneyAPI().initPlayer(player.getUniqueId());
    }

    private void addToJobSystem(Player player) {
        if (!EndoflifeCore.getInstance().getJobAPI().hasJob(player.getUniqueId()))
            EndoflifeCore.getInstance().getJobAPI().initPlayer(player.getUniqueId());
    }
}