package de.mxscha.en.endoflife.commands.world;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    // setup set Region Shop 1,2

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.setup")) {
                switch (args.length) {
                    case 0:
                        player.sendMessage("test");
                        break;
                    case 1:
                        player.sendMessage("test2");
                        break;
                    case 2:
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args[1].equalsIgnoreCase("spawn")) {
                                new ConfigLocationUtil(player.getLocation(),"Spawn").saveLocation();
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Spawn gesetzt!");
                            }
                            if (args[1].equalsIgnoreCase("employer")) {
                                new ConfigLocationUtil(player.getLocation(),"Employer").saveLocation();
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Arbeitsgeber gesetzt!");
                            }
                            if (args[1].equalsIgnoreCase("shop")) {
                                new ConfigLocationUtil(player.getLocation(),"Shop").saveLocation();
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Shop gesetzt!");
                            }
                            if (args[1].equalsIgnoreCase("accepter")) {
                                new ConfigLocationUtil(player.getLocation(),"Accepter").saveLocation();
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Abgabeort gesetzt!");
                            }
                            if (args[1].equalsIgnoreCase("smith")) {
                                new ConfigLocationUtil(player.getLocation(),"ToolSmith").saveLocation();
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Tool Smith gesetzt!");
                            }
                            if (args[1].equalsIgnoreCase("rtp")) {
                                new ConfigLocationUtil(player.getLocation(),"RandomTeleport").saveLocation();
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Random Teleport gesetzt!");
                            }
                        }
                        break;
                    case 3:

                    case 4:
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args[1].equalsIgnoreCase("Region")) {
                                if (args[2].equalsIgnoreCase("Shop")) {
                                    if (args[3].equalsIgnoreCase("1")) {
                                        new ConfigLocationUtil(player.getLocation(),"ShopRegion1").saveLocation();
                                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        player.sendMessage(Messages.PREFIX.get() + "§7Du hast die §a1 Location §7für die §aShop Area §7gesetzt!");
                                    } else if (args[3].equalsIgnoreCase("2")) {
                                        new ConfigLocationUtil(player.getLocation(),"ShopRegion2").saveLocation();
                                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        player.sendMessage(Messages.PREFIX.get() + "§7Du hast die §a2 Location §7für die §aShop Area §7gesetzt!");
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            } else
                player.sendMessage(Messages.NO_PERM.get());
        }
        return false;
    }
}