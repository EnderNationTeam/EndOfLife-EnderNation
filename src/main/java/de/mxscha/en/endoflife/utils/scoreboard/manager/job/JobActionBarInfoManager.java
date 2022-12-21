package de.mxscha.en.endoflife.utils.scoreboard.manager.job;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.scoreboard.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.scoreboard.manager.job.api.JobAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class JobActionBarInfoManager {

    public static void send() {
        final JobAPI api = EndoflifeCore.getInstance().getJobAPI();
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    if (api.hasJob(player) && !api.getJob(player).equals(Job.UNEMPLOYED.getTranslation())) {
                        Component job = Component.text("");
                        switch (api.getJob(player)) {
                            case "Farmer" -> job = Component.text("§a§lFarmer");
                            case "Holzfäller" -> job = Component.text("§2§lHolzfäller");
                            case "Schlachter" -> job = Component.text("§d§lSchlachter");
                            case "Minenarbeiter" -> job = Component.text("§6§lMinenarbeiter");
                            case "Fischer" -> job = Component.text("§9§lFischer");
                        }
                        Component partitionWall = Component.text(" §8| ");
                        Component level = Component.text("§lLevel", TextColor.fromCSSHexString("#BAFADE"));
                        Component arrow = Component.text(" §8» ");
                        Component JobLevel = Component.text( "§l"+ api.getJobLevel(player), TextColor.fromCSSHexString("#48E4A0"));
                        Component XP = Component.text("§lXP", TextColor.fromCSSHexString("#BAFADE"));
                        Component JobXP = Component.text("§l" + api.getJobXP(player), TextColor.fromCSSHexString("#2DD58C"));
                        Component MaxJobXP = Component.text("§l" + api.getJobMaxXP(player), TextColor.fromCSSHexString("#0FBE72"));
                        Component slash = Component.text(" §8/ ");
                        Component actionBar = Component.text("").append(job).append(partitionWall).append(level).append(arrow).append(JobLevel).append(partitionWall).append(XP).append(arrow).append(JobXP).append(slash).append(MaxJobXP);
                        checkLvLUp(player);
                        player.sendActionBar(actionBar);

                    }
                });
            }
        }.runTaskTimer(EndoflifeCore.getInstance(), 0, 20);
    }

    private static void checkLvLUp(Player player) {
        final JobAPI api = EndoflifeCore.getInstance().getJobAPI();
        int xp = api.getJobXP(player);
        int maxXP = api.getJobMaxXP(player);
        if (xp >= maxXP && maxXP != -1 && xp != -1) {
            api.levelUp(player);
            player.sendMessage(Messages.PREFIX.get() + "§7Du bist aufgestiegen, Du bist nun Level §8["
                    + de.mxscha.en.endoflife.utils.scoreboard.manager.chat.TextColor.GREEN_2.get()
                    +api.getJobLevel(player)+"§8]");
            int currentMaxXP = api.getJobMaxXP(player);
            int NextLevelMaxXP = currentMaxXP+(currentMaxXP/4);
            api.setMaxXP(player, NextLevelMaxXP);
        }
    }
}
