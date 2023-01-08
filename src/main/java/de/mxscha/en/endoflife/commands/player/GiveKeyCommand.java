package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GiveKeyCommand implements @Nullable CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.givekey")) {
                switch (args.length) {
                    case 0:
                        player.sendMessage(Messages.PLS_USE.get() + "§e/givekey §8[§espielername§8] §8[§aTyp§8]");
                        break;
                    case 1:
                        player.sendMessage(Messages.PREFIX.get() + "§cBitte gebe einen Typ an!");
                        break;
                    case 2:
                        String targetName = args[0];
                        Player target = Bukkit.getPlayer(targetName);
                        if (target == null || !target.isOnline()) {
                            player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                        } else {
                            String type = args[1].toLowerCase();
                            switch (type) {
                                case "coal":
                                    giveKey(target, "coal");
                                    target.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §8§lKohle Key §7erhalten!");
                                    player.sendMessage(Messages.PREFIX.get() + "§9" + target.getName() + " §7hat einen §8§lKohle Key §7erhalten!");
                                    break;
                                case "copper":
                                    giveKey(target, "copper");
                                    target.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §c§lKupfer Key §7erhalten!");
                                    player.sendMessage(Messages.PREFIX.get() + "§9" + target.getName() + " §7hat einen §c§lKupfer Key §7erhalten!");
                                    break;
                                case "gold":
                                    giveKey(target, "gold");
                                    target.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §6§lGold Key §7erhalten!");
                                    player.sendMessage(Messages.PREFIX.get() + "§9" + target.getName() + " §7hat einen §6§lGold Key §7erhalten!");
                                    break;
                                case "diamond":
                                    giveKey(target, "diamond");
                                    target.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §b§lDiamand Key §7erhalten!");
                                    player.sendMessage(Messages.PREFIX.get() + "§9" + target.getName() + " §7hat einen §b§lDiamand Key §7erhalten!");
                                    break;
                                case "emerald":
                                    giveKey(target, "emerald");
                                    target.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §a§lSmaragt Key §7erhalten!");
                                    player.sendMessage(Messages.PREFIX.get() + "§9" + target.getName() + " §7hat einen §a§lSmaragt Key §7erhalten!");
                                    break;
                                case "amethyst":
                                    giveKey(target, "amethyst");
                                    target.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §d§lAmethyst Key §7erhalten!");
                                    player.sendMessage(Messages.PREFIX.get() + "§9" + target.getName() + " §7hat einen §d§lAmethyst Key §7erhalten!");
                                    break;
                                case "netherite":
                                    giveKey(target, "netherite");
                                    target.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §8§lNetherit Key §7erhalten!");
                                    player.sendMessage(Messages.PREFIX.get() + "§9" + target.getName() + " §7hat einen §8§lNetherit Key §7erhalten!");
                                    break;
                            }
                        }
                        break;
                }
            } else
                player.sendMessage(Messages.NO_PERM.get());
        }
        return false;
    }

    private void giveKey(Player player, String type) {
        switch (type) {
            case "coal" -> {
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8§lKohle Key").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
            }
            case "copper" -> {
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§c§lKupfer Key").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
            }
            case "gold" -> {
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§6§lGold Key").setCustomModelData(3).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
            }
            case "diamond" -> {
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§b§lDiamand Key").setCustomModelData(4).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
            }
            case "emerald" -> {
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§a§lSmaragt Key").setCustomModelData(2).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
            }
            case "amethyst" -> {
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§d§lAmethyst Key").setCustomModelData(6).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
            }
            case "netherite" -> {
                player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8§lNetherit Key").setCustomModelData(5).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
            }
        }
    }
}
