package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerHelpCommand implements CommandExecutor {

    /*
        - spawn
        - backpack
        - enderchest
        - tpa
        - tphere
        - money
        - pay
        - job
        - trade
     */

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            switch (args.length) {
                case 0:
                    player.sendMessage("                       " + Messages.PPREFIX.get() + "§69-Help[1/2]                       ");
                    player.sendMessage(" §8● §e/spawn §8» §7Teleportiere dich zum Spawn!");
                    player.sendMessage(" §8● §e/sethome §8» §7Setze dein Zuhause!");
                    player.sendMessage(" §8● §e/home §8» §7Teleportiere dich zu deinem Zuhause!");
                    player.sendMessage(" §8● §e/backpack §8» §7Öffne deinen Rucksack!");
                    player.sendMessage(" §8● §e/enderchest §8» §7Öffne deine Enderchest!");
                    player.sendMessage(" §8● §e/job §8» §7Öffne das Job Menu!");
                    player.sendMessage(" §8● §e/money §8» §7Frage deinen Kontostand ab!");
                    player.sendMessage(" §8● §e/pay §8» §7Bezahle jemandem Geld!");
                    player.sendMessage("                       " + Messages.PPREFIX.get() + "§9-Help[1/2]                       ");
                    break;
                case 1:
                    if (args[0].equals("2")) {
                        player.sendMessage("                       " + Messages.PPREFIX.get() + "§69-Help[2/2]                       ");
                        player.sendMessage(" §8● §e/tpa §8» §7Sende eine Teleportanfrage!");
                        player.sendMessage(" §8● §e/tpaccept §8» §7Nehme die Teleportanfrage an!!");
                        player.sendMessage(" §8● §e/tpahere §8» §7Sende eine Teleportanfrage!");
                        player.sendMessage(" §8● §e/tpahereaccept §8» §7Nehme die Teleportanfrage an!");
                        player.sendMessage(" §8● §e/trade §8» §7Tausche mit einem anderen Spieler!");
                        player.sendMessage("                       " + Messages.PPREFIX.get() + "§9-Help[2/2]                       ");
                    }
                    break;
            }
        }
        return false;
    }
}