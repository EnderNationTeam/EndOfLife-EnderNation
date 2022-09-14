package de.mxscha.endernationendoflife.commands;

import de.mxscha.endernationendoflife.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.gamemode")) {
                switch (args.length) {
                    case 0:
                    case 1:
                        if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(MessageManager.Prefix + "§7Du bist nun im §4§lSURVIVAL-MODE§7!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                        if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(MessageManager.Prefix + "§7Du bist nun im §4§lCREATIVE-MODE§7!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                        if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                            player.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(MessageManager.Prefix + "§7Du bist nun im §4§lADVENTURE-MODE§7!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                        if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                            player.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(MessageManager.Prefix + "§7Du bist nun im §4§lSPECTATOR-MODE§7!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                        break;
                    case 2:
                        try {
                            Player target = Bukkit.getPlayer(args[1]);
                            if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                                target.setGameMode(GameMode.SURVIVAL);
                                player.sendMessage(MessageManager.Prefix + "§e" + target.getName() + " §7ist nun im  §4§lSURVIVAL-MODE§7!");
                                target.sendMessage(MessageManager.Prefix + "§7Du bist nun im §4§lSURVIVAL-MODE§7!");
                                target.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                            if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                                target.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(MessageManager.Prefix + "§e" + target.getName() + " §7ist nun im §4§lCREATIVE-MODE§7!");
                                target.sendMessage(MessageManager.Prefix + "§7Du bist nun im §4§lCREATIVE-MODE§7!");
                                target.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                            if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                                target.setGameMode(GameMode.ADVENTURE);
                                player.sendMessage(MessageManager.Prefix + "§e" + target.getName() + " §7ist nun im §4§lADVENTURE-MODE§7!");
                                target.sendMessage(MessageManager.Prefix + "§7Du bist nun im §4§lADVENTURE-MODE§7!");
                                target.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                            if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                                target.setGameMode(GameMode.SPECTATOR);
                                player.sendMessage(MessageManager.Prefix + "§e" + target.getName() + " §7ist nun im §4§lSPECTATOR-MODE§7!");
                                target.sendMessage(MessageManager.Prefix + "§7Du bist nun im §4§lSPECTATOR-MODE§7!");
                                target.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                        } catch (Exception e) {
                            player.sendMessage(MessageManager.Prefix + "§4Dieser Spieler ist nicht Online!");
                        }
                        break;
                }
            } else
                player.sendMessage(MessageManager.NoPerm);
        }
        return false;
    }
}
