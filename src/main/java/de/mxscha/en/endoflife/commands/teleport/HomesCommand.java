package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.home.Home;
import de.mxscha.en.endoflife.utils.manager.home.HomeList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

/*
 * Writer: Keksgauner
 * Time: 09.01.2023
 */
public class HomesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            var E = Component.text("E").color(TextColor.fromHexString("#0D2CA6")).decorate(TextDecoration.BOLD);
            var N = Component.text("n").color(TextColor.fromHexString("#113BDF")).decorate(TextDecoration.BOLD);
            var D = Component.text("d").color(TextColor.fromHexString("#0738FB")).decorate(TextDecoration.BOLD);
            var O = Component.text("O").color(TextColor.fromHexString("#C1CBF4")).decorate(TextDecoration.BOLD);
            var F = Component.text("f").color(TextColor.fromHexString("#FFFFFF")).decorate(TextDecoration.BOLD);
            var L = Component.text("L").color(TextColor.fromHexString("#BAFADE")).decorate(TextDecoration.BOLD);
            var I = Component.text("i").color(TextColor.fromHexString("#48E4A0")).decorate(TextDecoration.BOLD);
            var f = Component.text("f").color(TextColor.fromHexString("#2DD58C")).decorate(TextDecoration.BOLD);
            var e = Component.text("e").color(TextColor.fromHexString("#0FBE72")).decorate(TextDecoration.BOLD);
            var middle = Component.text(" §8| ");

            var content = Component.text("§7Homes: ");

            TextComponent textComponent = Component.text().build();
            textComponent = textComponent.append(E).append(N).append(D).append(O).append(F).append(L).append(I).append(f).append(e).append(middle).append(content);
            HomeList homes = EndoflifeCore.getInstance().getHomeManager().getHomes(player);
            for(Home home : homes.getList()) {
                textComponent = textComponent.append(
                        Component.text(home.getName())
                                .clickEvent(ClickEvent.runCommand("/home " + home.getName()))
                                .hoverEvent(HoverEvent.showText(
                                        Component.text("Klick zum Teleport").color(TextColor.color(0xFF0000))
                                ))
                                .color(TextColor.color(0x52E280))
                );

                // check if last
                if (homes.getList().size() != homes.getList().indexOf(home) + 1) {
                    textComponent = textComponent.append(Component.text(", "));
                }
            }

            if (homes.getList().isEmpty()) {
                textComponent = textComponent.append(Component.text("§7Keine Homes vorhanden"));
            }

            player.sendMessage(player, textComponent);
        }

        return false;
    }

    private void teleportToSpawn(Player player, Home home) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(home.getLocation());
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                player.sendMessage(Messages.PREFIX.get() + "§7Du bist nun bei deinem Zuhause!");
            }
        }.runTaskLater(EndoflifeCore.getInstance(), 20*3);
    }
}
