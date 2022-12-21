package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.scoreboard.manager.chat.Messages;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class TpaCommand implements CommandExecutor {

    private static HashMap<Player, Player> request = new HashMap<>();
    private static ArrayList<Player> tpaSave = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            switch (args.length) {
                case 0:
                    player.sendMessage(Messages.PLS_USE.get() + "§e/tpa §8[§espielername§8]");
                    break;
                case 1:
                    String playerName = args[0];
                    try {
                        Player target = Bukkit.getPlayer(playerName);
                        if (target.isOnline()) {
                            if (target == player) {
                                player.sendMessage(Messages.PREFIX.get() + "§cDu darfst dir keine Teleportanfrage senden!");
                            } else {
                                if (!request.containsValue(target)){
                                    request.put(player, target);
                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast §e" + target.getName() + "§7 eine Teleportanfrage gesendet!");
                                    target.sendMessage(Messages.PREFIX.get() + "§7Du hast eine Teleportanfrage von §e" + player.getName() + " §7erhalten!");
                                    var E = new TextComponent("E"); E.setBold(true); E.setColor(net.md_5.bungee.api.ChatColor.of("#0D2CA6"));
                                    var N = new TextComponent("n"); N.setBold(true); N.setColor(net.md_5.bungee.api.ChatColor.of("#113BDF"));
                                    var D = new TextComponent("d"); D.setBold(true); D.setColor(net.md_5.bungee.api.ChatColor.of("#0738FB"));
                                    var O = new TextComponent("O"); O.setBold(true); O.setColor(net.md_5.bungee.api.ChatColor.of("#C1CBF4"));
                                    var F = new TextComponent("f"); F.setBold(true); F.setColor(net.md_5.bungee.api.ChatColor.of("#FFFFFF"));
                                    var L = new TextComponent("L"); L.setBold(true); L.setColor(net.md_5.bungee.api.ChatColor.of("#BAFADE"));
                                    var I = new TextComponent("i"); I.setBold(true); I.setColor(net.md_5.bungee.api.ChatColor.of("#48E4A0"));
                                    var f = new TextComponent("f"); f.setBold(true); f.setColor(net.md_5.bungee.api.ChatColor.of("#2DD58C"));
                                    var e = new TextComponent("e"); e.setBold(true); e.setColor(net.md_5.bungee.api.ChatColor.of("#0FBE72"));
                                    var middle = new TextComponent(" §8| §8[");
                                    var klammer = new TextComponent("§8]");
                                    var component = new TextComponent("Annehmen");
                                    component.setBold(true);
                                    component.setColor(net.md_5.bungee.api.ChatColor.of("#17F604"));
                                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Annehmen").color(ChatColor.of("#17F604")).create()));
                                    component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + player.getName()));
                                    target.sendMessage(E, N, D, O, F, L, I, f, e, middle, component,klammer);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            if (request.containsKey(player)) {
                                                player.sendMessage(Messages.PREFIX.get() + "§cDeine Teleportanfrage an §e" + target.getName() + " §cist abgelaufen!");
                                            }
                                        }
                                    }.runTaskLater(EndoflifeCore.getInstance(), 20*60);
                                } else
                                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast diesem Spieler bereits eine Teleportanfrage gesendet!");
                            }
                        } else
                            player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                    } catch (Exception exception) {
                        player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                    }
                    break;
            }
        }
        return false;
    }

    public static HashMap<Player, Player> getRequest() {
        return request;
    }

    public static ArrayList<Player> getTpaSave() {
        return tpaSave;
    }
}