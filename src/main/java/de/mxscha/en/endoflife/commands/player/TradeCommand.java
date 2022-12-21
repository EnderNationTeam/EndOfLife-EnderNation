package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.scoreboard.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.scoreboard.manager.item.ItemCreator;
import de.mxscha.en.endoflife.utils.scoreboard.manager.item.inventory.InventoryPropertys;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class TradeCommand implements CommandExecutor {

    private HashMap<Player, Player> request = new HashMap<>();
    private HashMap<Player, Player> trades = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            switch (args.length) {
                case 0:
                    player.sendMessage(Messages.PLS_USE.get() + "§e/trade §8[§espielername§8]");
                    break;
                case 1:
                    String targetsName = args[0];
                    Player target = Bukkit.getPlayer(targetsName);
                    if (target == null || !target.isOnline()) {
                         player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                    } else {
                        if (request.containsKey(player)) {
                            if (!request.containsValue(target)) {
                                request.put(player, target);
                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast §e" + target.getName() + "§7 eine Tauschanfrage gesendet!");
                                target.sendMessage(Messages.PREFIX.get() + "§7Du hast eine Tauschanfrage von §e" + player.getName() + " §7erhalten!");
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
                                component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/trade accept " + player.getName()));
                                target.sendMessage(E, N, D, O, F, L, I, f, e, middle, component,klammer);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        if (request.containsKey(player)) {
                                            player.sendMessage(Messages.PREFIX.get() + "§cDeine Tauschanfrage an §e" + target.getName() + " §cist abgelaufen!");
                                        }
                                    }
                                }.runTaskLater(EndoflifeCore.getInstance(), (20*60)*2);
                            } else
                                player.sendMessage(Messages.PREFIX.get() + "§cDu hast diesem Spieler bereits eine Tauschanfrage gesendet!");
                        } else {
                            request.put(player, target);
                            player.sendMessage(Messages.PREFIX.get() + "§7Du hast §e" + target.getName() + "§7 eine Tauschanfrage gesendet!");
                            target.sendMessage(Messages.PREFIX.get() + "§7Du hast eine Tauschanfrage von §e" + player.getName() + " §7erhalten!");
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
                            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/trade accept " + player.getName()));
                            target.sendMessage(E, N, D, O, F, L, I, f, e, middle, component,klammer);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (request.containsKey(player)) {
                                        player.sendMessage(Messages.PREFIX.get() + "§cDeine Tauschanfrage an §e" + target.getName() + " §cist abgelaufen!");
                                    }
                                }
                            }.runTaskLater(EndoflifeCore.getInstance(), (20*60)*2);
                        }
                    }
                    break;
                case 2:
                    if (args[0].equalsIgnoreCase("accept")) {
                        String players1Name = args[1];
                        Player player1 = Bukkit.getPlayer(players1Name);
                        if (player1 == null || !player1.isOnline()) {
                            player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                        } else {
                            if (request.containsKey(player1)) {
                                if (request.containsValue(player)) {
                                    request.remove(player1);
                                    Inventory trade = Bukkit.createInventory(null, 9*6, "§8» §6§lTauschinventar");
                                    InventoryPropertys.setupTrade(trade);
                                    InventoryPropertys.addCancelTradeButton(trade);
                                    trade.setItem(2, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §9Tauschitems von §e" + player1.getName(), player1).toItemStack());
                                    trade.setItem(6, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §9Tauschitems von §e" + player.getName(), player).toItemStack());
                                    trade.setItem(38, new ItemCreator(Material.RED_CONCRETE).setName("§8● §c" + player1.getName() + " ").toItemStack());
                                    trade.setItem(42, new ItemCreator(Material.RED_CONCRETE).setName("§8● §c bestätigen").toItemStack());
                                    player.openInventory(trade);
                                    player1.openInventory(trade);
                                    trades.put(player, player1);
                                } else
                                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast keine Tauschanfrage von diesem Spieler bekommen!");
                            }
                        }
                    }
                    break;
            }
        }
        return false;
    }
}
