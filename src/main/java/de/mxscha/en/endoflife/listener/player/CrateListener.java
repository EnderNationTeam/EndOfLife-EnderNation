package de.mxscha.en.endoflife.listener.player;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.crate.CrateManager;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import de.mxscha.en.endoflife.utils.manager.item.inventory.InventoryPropertys;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CrateListener implements Listener {

    List<ItemStack> items;
    private static final List<ItemStack> CoalItems = new ArrayList<>();

    private static HashMap<Player, Boolean> cooldown = new HashMap<>();

    public CrateListener() {
        registerItemsToCrates();
    }

    private void registerItemsToCrates() {
        // Kohle
        CoalItems.add(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8§lKohle Key").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        CoalItems.add(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8§lKohle Key").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        CoalItems.add(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§c§lKupfer Key").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        CoalItems.add(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§c§lKupfer Key").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        CoalItems.add(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§c§lKupfer Key").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        CoalItems.add(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§c§lKupfer Key").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        CoalItems.add(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§6§lGold Key").setCustomModelData(3).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        CoalItems.add(new ItemCreator(Material.DIAMOND).toItemStack());
        CoalItems.add(new ItemCreator(Material.IRON_INGOT).setAmount(new Random().nextInt(8)).toItemStack());
        CoalItems.add(new ItemCreator(Material.IRON_INGOT).setAmount(new Random().nextInt(5)).toItemStack());
        CoalItems.add(new ItemCreator(Material.GOLDEN_APPLE).toItemStack());
        CoalItems.add(new ItemCreator(Material.COOKED_BEEF).setAmount(new Random().nextInt(40)).toItemStack());
        CoalItems.add(new ItemCreator(Material.HONEY_BOTTLE).setAmount(new Random().nextInt(4)).toItemStack());
        CoalItems.add(new ItemCreator(Material.BREAD).setAmount(new Random().nextInt(34)).toItemStack());
        CoalItems.add(new ItemCreator(Material.FEATHER).setAmount(new Random().nextInt(7)).toItemStack());
        CoalItems.add(new ItemCreator(Material.SLIME_BALL).setAmount(new Random().nextInt(8)).toItemStack());
        CoalItems.add(new ItemCreator(Material.TURTLE_EGG).toItemStack());
        CoalItems.add(new ItemCreator(Material.SPRUCE_PLANKS).setAmount(new Random().nextInt(34)).toItemStack());
        CoalItems.add(new ItemCreator(Material.OAK_PLANKS).setAmount(new Random().nextInt(32)).toItemStack());
        CoalItems.add(new ItemCreator(Material.BAMBOO_SAPLING).toItemStack());
        CoalItems.add(new ItemCreator(Material.SPRUCE_SAPLING).toItemStack());
        CoalItems.add(new ItemCreator(Material.MANGROVE_PROPAGULE).toItemStack());
        CoalItems.add(new ItemCreator(Material.BIRCH_SAPLING).toItemStack());
        CoalItems.add(new ItemCreator(Material.GOLD_BLOCK).toItemStack());
        CoalItems.add(new ItemCreator(Material.IRON_BLOCK).toItemStack());
        CoalItems.add(new ItemCreator(Material.OBSIDIAN).setAmount(4).toItemStack());
        CoalItems.add(new ItemCreator(Material.LANTERN).setAmount(new Random().nextInt(11)).toItemStack());
        CoalItems.add(new ItemCreator(Material.WHITE_BANNER).setAmount(new Random().nextInt(4)).toItemStack());
        CoalItems.add(new ItemCreator(Material.BLACK_BANNER).setAmount(new Random().nextInt(3)).toItemStack());
        CoalItems.add(new ItemCreator(Material.SHROOMLIGHT).setAmount(new Random().nextInt(8)).toItemStack());
        CoalItems.add(new ItemCreator(Material.ORANGE_BANNER).setAmount(new Random().nextInt(4)).toItemStack());
        CoalItems.add(new ItemCreator(Material.BONE_MEAL).setAmount(new Random().nextInt(48)).toItemStack());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getClickedBlock() == null) return;
        Player player = event.getPlayer();
        Block crate = event.getClickedBlock();
        Block underCrate = crate.getRelative(BlockFace.DOWN);
        Block bottom = crate.getRelative(BlockFace.DOWN, 2);
        if (crate.getType() == Material.PLAYER_HEAD) {
            if (isBlockCrate(bottom)) {
                switch (underCrate.getType()) {
                    case COAL_BLOCK -> {
                        player.sendMessage(Messages.PREFIX.get() + "§cDie Lootboxen sind derzeit in Arbeit!");
                        String art = "coal";
                        /*
                        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                            event.setCancelled(true);

                            Inventory showcase;
                            if (CoalItems.size() <= 9*2) {
                                showcase = Bukkit.createInventory(null, 9*4, "§8● §8§lKohle Box");
                            } else {
                                showcase = Bukkit.createInventory(null, 9*6, "§8● §8§lKohle Box");
                            }
                            InventoryPropertys.setOutline(showcase);
                            for (ItemStack item : CoalItems) {
                                showcase.addItem(item);
                            }
                            player.openInventory(showcase);
                        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                            if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§8§lKohle Key")) {
                                player.sendMessage(Messages.PREFIX.get() + "§cNutze einen §0§lKohle Key §cum diese Box zu öffnen!");
                            } else {
                                if (canOpen(player)) {
                                    if (ItemFitInInventory(player)) {
                                        removeKey(player, "coal");
                                        new CrateManager(player, CoalItems, "§8● §8§lKohle Box");
                                        cooldown.put(player, true);
                                    } else
                                        player.sendMessage(Messages.PREFIX.get() + "§cBitte Leere dein Inventar!");
                                } else
                                    player.sendMessage(Messages.PREFIX.get() + "§cBitte öffne nur eine Kiste gleichzeitig!");
                            }
                        }
                         */
                    }
                    case WAXED_COPPER_BLOCK -> {
                        player.sendMessage(Messages.PREFIX.get() + "§cDie Lootboxen sind derzeit in Arbeit!");
                        String art = "copper";
                        /*
                        if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§c§lKupfer Key")) {
                            player.sendMessage(Messages.PREFIX.get().get() + "§cNutze einen §c§lKupfer Key §cum diese Box zu öffnen!");
                        } else {
                            if (canOpen(player)) {
                                items = new ArrayList<>();
                                items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                                items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                                items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                                removeKey(player, "copper");
                                new CrateManager(player, items, "§8● §c§lKupfer Box");
                                list.put(player, true);
                            } else
                                player.sendMessage(Messages.PREFIX.get().get() + "§cBitte öffne nur eine Kiste gleichzeitig!");
                        }
                         */
                    }
                    case GOLD_BLOCK -> {
                        player.sendMessage(Messages.PREFIX.get() + "§cDie Lootboxen sind derzeit in Arbeit!");
                        String art = "gold";
                        /*
                        if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§6§lGold Key")) {
                            player.sendMessage(Messages.PREFIX.get().get() + "§cNutze einen §6§lGold Key §cum diese Box zu öffnen!");
                        } else {
                            if (canOpen(player)) {
                                items = new ArrayList<>();
                                items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                                items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                                items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                                removeKey(player, "gold");
                                new CrateManager(player, items, "§8● §6§lGold Box");
                                list.put(player, true);
                            } else
                                player.sendMessage(Messages.PREFIX.get().get() + "§cBitte öffne nur eine Kiste gleichzeitig!");
                        }
                         */
                    }
                    case EMERALD_BLOCK -> {
                        player.sendMessage(Messages.PREFIX.get() + "§cDie Lootboxen sind derzeit in Arbeit!");
                        String art = "emerald";
                        /*
                        if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§a§lSmaragt Key")) {
                            player.sendMessage(Messages.PREFIX.get().get() + "§cNutze einen §a§lSmaragt Key §cum diese Box zu öffnen!");
                        } else {
                            if (canOpen(player)) {
                                items = new ArrayList<>();
                                items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                                items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                                items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                                removeKey(player, "emerald");
                                new CrateManager(player, items, "§8● §a§lSmaragt Box");
                                list.put(player, true);
                            } else
                                player.sendMessage(Messages.PREFIX.get().get() + "§cBitte öffne nur eine Kiste gleichzeitig!");
                        }
                         */
                    }
                    case DIAMOND_BLOCK -> {
                        player.sendMessage(Messages.PREFIX.get() + "§cDie Lootboxen sind derzeit in Arbeit!");
                        String art = "diamond";
                        /*
                            if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§b§lDiamand Key")) {
                                player.sendMessage(Messages.PREFIX.get().get() + "§cNutze einen §b§lDiamand Key §cum diese Box zu öffnen!");
                            } else {
                                if (canOpen(player)) {
                                    items = new ArrayList<>();
                                    items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                                    items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                                    items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                                    removeKey(player, "diamond");
                                    new CrateManager(player, items, "§8● §b§lDiamand Box");
                                    list.put(player, true);
                                } else
                                    player.sendMessage(Messages.PREFIX.get().get() + "§cBitte öffne nur eine Kiste gleichzeitig!");
                            }
                         */
                    }
                    case AMETHYST_BLOCK -> {
                        player.sendMessage(Messages.PREFIX.get() + "§cDie Lootboxen sind derzeit in Arbeit!");
                        String art = "amethyst";
                        /*
                            if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§d§lAmethyst Key")) {
                                player.sendMessage(Messages.PREFIX.get().get() + "§cNutze einen §d§lAmethyst Key §cum diese Box zu öffnen!");
                            } else {
                                if (canOpen(player)) {
                                    items = new ArrayList<>();
                                    items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                                    items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                                    items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                                    removeKey(player, "amethyst");
                                    new CrateManager(player, items, "§8● §d§lAmethyst Box");
                                    list.put(player, true);
                                } else
                                    player.sendMessage(Messages.PREFIX.get().get() + "§cBitte öffne nur eine Kiste gleichzeitig!");
                            }
                         */
                    }
                    case NETHERITE_BLOCK -> {
                        player.sendMessage(Messages.PREFIX.get() + "§cDie Lootboxen sind derzeit in Arbeit!");
                        String art = "netherite";
                        /*
                            if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§8§lNetherit Key")) {
                                player.sendMessage(Messages.PREFIX.get().get() + "§cNutze einen §8§lNetherit Key §cum diese Box zu öffnen!");
                            } else {
                                if (canOpen(player)) {
                                    items = new ArrayList<>();
                                    items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                                    items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                                    items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                                    removeKey(player, "netherite");
                                    new CrateManager(player, items, "§8● §8§lNetherit Box");
                                    list.put(player, true);
                                } else
                                    player.sendMessage(Messages.PREFIX.get().get() + "§cBitte öffne nur eine Kiste gleichzeitig!");
                            }
                         */
                    }
                }
            }
        }
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getView().getTitle().equals("§8● §8§lKohle Box")
                || event.getView().getTitle().equals("§8● §c§lKupfer Box")
                || event.getView().getTitle().equals("§8● §f§lEisen Box")
                || event.getView().getTitle().equals("§8● §6§lGold Box")
                || event.getView().getTitle().equals("§8● §b§lDiamand Box")
                || event.getView().getTitle().equals("§8● §a§lSmaragt Box")
                || event.getView().getTitle().equals("§8● §d§lAmethyst Box")
                || event.getView().getTitle().equals("§8● §8§lNetherit Box") ) {
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
        }
    }

    private void removeKey(Player player, String type) {
        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
        player.updateInventory();
    }

    private boolean isBlockCrate(Block under) {
        return under.getType() == Material.REINFORCED_DEEPSLATE;
    }

    public boolean ItemFitInInventory(Player player){
        int i = 0;
        ArrayList<ItemStack> items = new ArrayList<>();
        Inventory inventory = player.getInventory();
        for(ItemStack item : inventory.getContents()) {
            if(item == null) continue;
            if (!items.contains(item)) {
                items.add(item);
                i++;
            } else {
                i++;
            }
        }
        return i < 36;
    }

    private boolean canOpen(Player player) {
        return !cooldown.containsKey(player);
    }

    public static HashMap<Player, Boolean> getCooldown() {
        return cooldown;
    }
}