package de.mxscha.en.endoflife.utils.manager.job;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.job.api.JobAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.C;
import org.w3c.dom.Text;

public class JobActionBarInfoManager {

    public static void send() {
        final JobAPI api = EndoflifeCore.getInstance().getJobAPI();
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    if (api.hasJob(player.getUniqueId()) && !api.getJob(player.getUniqueId()).equals(Job.UNEMPLOYED.getTranslation())) {
                        Component job = Component.text("");
                        switch (api.getJob(player.getUniqueId())) {
                            case "Farmer" -> job = Component.text("§a§lFarmer");
                            case "Holzfäller" -> job = Component.text("§2§lHolzfäller");
                            case "Schlachter" -> job = Component.text("§d§lSchlachter");
                            case "Minenarbeiter" -> job = Component.text("§6§lMinenarbeiter");
                            case "Fischer" -> job = Component.text("§9§lFischer");
                        }
                        Component partitionWall = Component.text(" §8| ");
                        Component level = Component.text("§lLevel", TextColor.fromCSSHexString("#BAFADE"));
                        Component arrow = Component.text(" §8» ");
                        Component JobLevel = Component.text( "§l"+ api.getJobLevel(player.getUniqueId()), TextColor.fromCSSHexString("#48E4A0"));
                        Component XP = Component.text("§lXP", TextColor.fromCSSHexString("#BAFADE"));
                        Component JobXP = Component.text("§l" + api.getJobXP(player.getUniqueId()), TextColor.fromCSSHexString("#2DD58C"));
                        Component MaxJobXP = Component.text("§l" + api.getJobMaxXP(player.getUniqueId()), TextColor.fromCSSHexString("#0FBE72"));
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
        int xp = api.getJobXP(player.getUniqueId());
        int maxXP = api.getJobMaxXP(player.getUniqueId());
        if (xp >= maxXP && maxXP != -1 && xp != -1) {
            api.levelUp(player.getUniqueId());
            player.sendMessage(Messages.PREFIX.get() + "§7Du bist aufgestiegen, Du bist nun Level §8["
                    + de.mxscha.en.endoflife.utils.manager.chat.TextColor.GREEN_2.get()
                    +api.getJobLevel(player.getUniqueId())+"§8]");
            api.setMaxXP(player.getUniqueId(), api.getJobMaxXP(player.getUniqueId())*2);
        }
    }
}
