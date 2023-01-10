package de.mxscha.en.endoflife.commands.world;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetupCommand implements CommandExecutor, TabCompleter {

    // setup set Region Shop 1,2

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.setup")) {
                switch (args.length) {
                    case 0:
                        player.sendMessage(" §8● §e/setup set spawn");
                        player.sendMessage(" §8● §e/setup set employer");
                        player.sendMessage(" §8● §e/setup set shop");
                        player.sendMessage(" §8● §e/setup set accepter");
                        player.sendMessage(" §8● §e/setup set smith");
                        player.sendMessage(" §8● §e/setup set rtp");
                        player.sendMessage(" §8● §e/setup set arenaSpawn");
                        player.sendMessage(" §8● §e/setup set region shop 1/2");
                        player.sendMessage(" §8● §e/setup set region arena void");
                        player.sendMessage(" §8● §e/setup set region arena pvp1");
                        player.sendMessage(" §8● §e/setup set region arena pvp2");
                        break;
                    case 1:
                        player.sendMessage(Messages.PREFIX + "§cBitte benutze §e/setup §cfür Hilfe");
                        break;
                    case 2:
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args[1].equalsIgnoreCase("spawn")) {
                                new ConfigLocationUtil(player.getLocation(),"Spawn").saveLocation();
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Spawn gesetzt!");
                            }
                            if (args[1].equalsIgnoreCase("arenaSpawn")) {
                                new ConfigLocationUtil(player.getLocation(),"ArenaSpawn").saveLocation();
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
                            if (args[1].equalsIgnoreCase("region")) {
                                if (args[2].equalsIgnoreCase("shop")) {
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

                                if (args[2].equalsIgnoreCase("arena")) {
                                    if (args[3].equalsIgnoreCase("void")) {
                                        new ConfigLocationUtil(player.getLocation(),"ArenaVoid").saveLocation();
                                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        player.sendMessage(Messages.PREFIX.get() + "§7Du hast die §aVoid Location §7für die §aArena Area §7gesetzt!");
                                    }
                                    if (args[3].equalsIgnoreCase("pvp1")) {
                                        new ConfigLocationUtil(player.getLocation(),"ArenaPvp1").saveLocation();
                                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        player.sendMessage(Messages.PREFIX.get() + "§7Du hast die §aPVP1 Location §7für die §aArena Area §7gesetzt!");
                                    }
                                    if (args[3].equalsIgnoreCase("pvp2")) {
                                        new ConfigLocationUtil(player.getLocation(),"ArenaPvp2").saveLocation();
                                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        player.sendMessage(Messages.PREFIX.get() + "§7Du hast die §aPVP2 Location §7für die §aArena Area §7gesetzt!");
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

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> arguments = new ArrayList<>();
        if (args.length == 1){
            arguments.add("set");
        }

        if (args.length == 2){
            arguments.add("spawn");
            arguments.add("arenaSpawn");
            arguments.add("employer");
            arguments.add("shop");
            arguments.add("accepter");
            arguments.add("smith");
            arguments.add("rtp");

            arguments.add("region");
        }

        if (args.length == 3 && args[1].equalsIgnoreCase("region")){
            arguments.add("shop");
            arguments.add("arena");
        }

        if (args.length == 4 && args[1].equalsIgnoreCase("region") && args[2].equalsIgnoreCase("shop")){
            arguments.add("1");
            arguments.add("2");
        }

        if (args.length == 4 && args[1].equalsIgnoreCase("region") && args[2].equalsIgnoreCase("arena")){
            arguments.add("void");
            arguments.add("pvp1");
            arguments.add("pvp2");
        }

        return arguments;
    }
}
