package de.mxscha.endernationendoflife.listener;

import de.mxscha.endernationendoflife.EndoflifeCore;
import de.mxscha.endernationendoflife.utils.scoreboard.DefaultScoreboard;
import de.mxscha.endernationendoflife.utils.scoreboard.tablist.TablistManager;
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
        // new DefaultScoreboard(player);
        TablistManager.setTablist(player);
        event.setJoinMessage(null);
    }

    private void addToMoneySystem(Player player) {
        if(!EndoflifeCore.getInstance().getMoneyAPI().isUserExists(player.getUniqueId()))
            EndoflifeCore.getInstance().getMoneyAPI().initPlayer(player.getUniqueId());
    }

    private void addToJobSystem(Player player) {
        if (!EndoflifeCore.getInstance().getJobAPI().hasJob(player.getUniqueId()))
            EndoflifeCore.getInstance().getJobAPI().initPlayer(player.getUniqueId());
    }
}