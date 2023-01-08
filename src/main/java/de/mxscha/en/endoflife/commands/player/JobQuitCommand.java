package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import de.mxscha.en.endoflife.utils.manager.job.Job;
import de.mxscha.en.endoflife.utils.manager.job.entity.Employer;
import de.mxscha.en.endoflife.utils.scoreboard.DefaultScoreboard;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JobQuitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            switch (args.length) {
                case 0:
                    player.sendMessage(Messages.PLS_USE.get() + "§e/job quit confirm");
                    break;
                case 2:
                    if (args[0].toLowerCase().equals("quit")) {
                        if (args[1].toLowerCase().equals("confirm")) {
                            EndoflifeCore.getInstance().getJobAPI().fireFromJob(player, Job.FARMER);
                            player.sendMessage(Messages.PREFIX.get() + "§7Du hast deinen Job als §aFarmer §cabgelegt§7!");
                            player.sendMessage(Messages.PREFIX.get() + "§cDu kannst erst in 20 Minuten einen neuen Job nehmen!");
                            player.sendMessage(Messages.PREFIX.get() + "§cDu hast 100€ Schadensersatz für dein Werkzeug gezahlt!");
                            EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player, 100);
                            new DefaultScoreboard(player).update();
                            if (!Employer.getCooldown().containsKey(player)) {
                                Employer.getCooldown().put(player, System.currentTimeMillis());
                            }
                        }
                    }
                    break;
            }
        }
        return false;
    }
}
