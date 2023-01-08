package de.mxscha.en.endoflife.utils.manager.item.inventory;

import de.mxscha.en.endoflife.utils.manager.chat.TextColor;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import de.mxscha.en.endoflife.utils.manager.job.entity.Delivery;
import de.mxscha.en.endoflife.utils.manager.job.entity.ToolSmith;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.HashMap;

import static org.bukkit.Material.*;


public class InventoryOpener {

    private static Inventory JobInventory;
    private static Inventory ShopInventory;
    private static Inventory ShopInventoryItems;
    private static Inventory ShopInventoryKeys;
    private static Inventory ShopInventoryRolls;
    private static Inventory JobInventoryFarmer;
    private static Inventory JobInventoryFarmerInfo;
    private static Inventory JobInventoryLumberjack;
    private static Inventory JobInventoryButcher;
    private static Inventory JobInventoryMiner;
    private static Inventory JobInventoryFisher;
    private static Inventory AccepterInventory;
    private static Inventory SmithInventory;

    private static final HashMap<Integer, Inventory> IDs = new HashMap<>();

    public static void initInventorys() {
        JobInventory = Bukkit.createInventory(null, 9*5, "§8» §a§lJobs");
        JobInventoryFarmer = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8» §a§lFarmer");
        JobInventoryFarmerInfo = Bukkit.createInventory(null, 9*6, "§8» §a§lFarmer §8» §6Infos");
        JobInventoryLumberjack = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8» §2§lHolzfäller");
        JobInventoryButcher = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8» §d§lSchlachter");
        JobInventoryMiner = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8» §6§lMinenarbeiter");
        JobInventoryFisher = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8» §9§lFischer");
        ShopInventory = Bukkit.createInventory(null, 9*5, "§8» §6§lShop");
        ShopInventoryItems = Bukkit.createInventory(null, 9*5, "§8» §a§lItem Shop");
        ShopInventoryKeys = Bukkit.createInventory(null, 9*5, "§8» §5§lKey Shop");
        ShopInventoryRolls = Bukkit.createInventory(null, 9*5, "§8» §9§lRollen Shop");
        AccepterInventory = Bukkit.createInventory(null, 9*5, "§8» §6§lAbgabe");
        SmithInventory = Bukkit.createInventory(null, 9*6, ToolSmith.getInventoryName());

        setupInventory(JobInventory);
        setupInventory(ShopInventory);
        setupInventory(ShopInventoryItems);
        setupInventory(ShopInventoryKeys);
        setupInventory(ShopInventoryRolls);
        setupInventory(JobInventoryFarmer);
        setupInventory(JobInventoryLumberjack);
        setupInventory(JobInventoryButcher);
        setupInventory(JobInventoryMiner);
        setupInventory(JobInventoryFisher);
        setupInventory(JobInventoryFarmerInfo);
        setupInventory(AccepterInventory);
        setupInventory(SmithInventory);

        IDs.put(1, JobInventory);
        IDs.put(2, ShopInventory);
        IDs.put(3, ShopInventoryItems);
        IDs.put(4, ShopInventoryKeys);
        IDs.put(5, ShopInventoryRolls);
        IDs.put(6, JobInventoryFarmer);
        IDs.put(7, JobInventoryLumberjack);
        IDs.put(8, JobInventoryButcher);
        IDs.put(9, JobInventoryMiner);
        IDs.put(10, JobInventoryFisher);
        IDs.put(11, JobInventoryFarmerInfo);
        IDs.put(12, AccepterInventory);
        IDs.put(13, SmithInventory);
    }

    public static void open(Player player, int id) {
        if (IDs.containsKey(id)) {
            player.openInventory(IDs.get(id));
        }
    }

    private static void setupInventory(Inventory inventory) {
        if (inventory == JobInventory) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addExitButton(inventory);
            addItemsToJobInventory();
        }
        if (inventory == ShopInventory) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addExitButton(inventory);
            addItemsToShopInventory();
        }
        if (inventory == ShopInventoryItems) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToItemShopInventory();
        }
        if (inventory == ShopInventoryKeys) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToKeyShopInventory();
        }
        if (inventory == ShopInventoryRolls) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToRollShopInventory();
        }
        if (inventory == JobInventoryFarmer) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToFarmerJobInventory();
        }
        if (inventory == JobInventoryLumberjack) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToLumberjackJobInventory();
        }
        if (inventory == JobInventoryButcher) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToButcherJobInventory();
        }
        if (inventory == JobInventoryMiner) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToMinerJobInventory();
        }
        if (inventory == JobInventoryFisher) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToFisherJobInventory();
        }
        if (inventory == JobInventoryFarmerInfo) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToFarmerJobInfoInventory();
        }
        if (inventory == AccepterInventory) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addExitButton(inventory);
            addItemsToAccepterInventory();
        }
        if (inventory == SmithInventory) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addExitButton(inventory);
        }
    }

    private static String lost = "&x&f&f&0&0&0&0D&x&f&9&0&0&0&0u &x&f&4&0&0&0&0h&x&e&e&0&0&0&0a&x&e&8&0&0&0&0s&x&e&2&0&0&0&0t &x&d&d&0&0&0&0d&x&d&7&0&0&0&0e&x&d&1&0&0&0&0i&x&c&b&0&0&0&0n &x&c&6&0&0&0&0W&x&c&0&0&0&0&0e&x&b&a&0&0&0&0r&x&b&5&0&0&0&0k&x&a&f&0&0&0&0z&x&a&9&0&0&0&0e&x&a&3&0&0&0&0u&x&9&e&0&0&0&0g &x&9&8&0&0&0&0v&x&9&2&0&0&0&0e&x&8&c&0&0&0&0r&x&8&7&0&0&0&0l&x&8&1&0&0&0&0o&x&7&b&0&0&0&0r&x&7&5&0&0&0&0e&x&7&0&0&0&0&0n&x&6&a&0&0&0&0?";

    private static void addItemsToAccepterInventory() {
        AccepterInventory.setItem(20, new ItemCreator(Material.HOPPER_MINECART).setName("§8● " + TextColor.BLUE_3.get() + "§lSchnellabgabe")
                .setLore("§8» §7Verkaufe direkt alle verkaufbaren Items", "   §7aus deinem Inventar!").toItemStack());
        AccepterInventory.setItem(22, new ItemCreator(Material.CHEST_MINECART).setName("§8● " + TextColor.BLUE_2.get() + "§lAbgabe")
                .setLore("§8» §7Klicke um das Abgabe Menu zu öffnen!").toItemStack());
        AccepterInventory.setItem(24, new ItemCreator(Material.PAPER).setName("§8● " + TextColor.DARKER_BLUE.get() + "§lRechnung")
                .setLore("§8» §7Klicke um das Rechnungs Menu zu öffnen!").toItemStack());
    }

    private static void addItemsToJobInventory() {
        JobInventory.setItem(11, new ItemCreator(Material.DIAMOND_HOE).setName("§8● §a§lFarmer").setLore("§8» §7Baue verschiedene Arten von Essen an/ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Diamand Hacke mit §d§oEffizienz", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(12, new ItemCreator(Material.GOLDEN_AXE).setName("§8● §2§lHolzfäller").setLore("§8» §7Baue verschiedene Baum Arten ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Stein Axt mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(13, new ItemCreator(Material.STONE_SWORD).setName("§8● §d§lSchlachter").setLore("§8» §7Töte verschiedene Tiere", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Ein Holz Schwert mit §d§oSchärfe 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(14, new ItemCreator(Material.IRON_PICKAXE).setName("§8● §6§lMinenarbeiter").setLore("§8» §7Baue verschiedene Erze und Blöcke ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Eisen Spitzhacke mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(15, new ItemCreator(Material.FISHING_ROD).setName("§8● §9§lFischer").setLore("§8» §7Suche nach verschiedenen Fischen im Meer", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Angel mit §d§oGlück des Meeres", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").toItemStack());
        JobInventory.setItem(8, new ItemCreator(Material.BARRIER).setName("§8● §cKündigen").setLore("§8» §7Hier kannst du deinen Job §ckündigen§7!", "§8» §c§lInfo§8: §6Bitte halte dein Job Tool bereit!", "", "§8● " + ChatColor.translateAlternateColorCodes('&', lost), "§8» §7Wenn du ohne dein Werkzeug kündigen willst,", "   §f§7Musst du Schadensersatz bezahlen").toItemStack());
    }

    private static void addItemsToFarmerJobInventory() {
        JobInventoryFarmer.setItem(4, new ItemCreator(Material.STONE_HOE).setName("§8● §a§lFarmer").setLore("§8» §7Baue verschiedene Arten von Essen an/ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Diamand Hacke mit §d§oEffizienz", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventoryFarmer.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
        JobInventoryFarmer.setItem(32, new ItemCreator(Material.WRITABLE_BOOK).setName("§8● §6Infos").setLore("§7Klicke um Infos zu diesem Job anzusehen!").toItemStack());
    }

    private static void addItemsToFarmerJobInfoInventory() {
        JobInventoryFarmerInfo.setItem(4, new ItemCreator(Material.WRITABLE_BOOK).setName("§8● §6Infos").setLore("§8» §7Alle Infos zum Job §aFarmer§7!").toItemStack());
        Location location = new Location(Bukkit.getWorld("world"), 1, 1, 1);
        if (new ConfigLocationUtil("Accepter").loadLocation() != null)
            location = new ConfigLocationUtil("Accepter").loadLocation();
        JobInventoryFarmerInfo.setItem(18, new ItemCreator(Material.HOPPER_MINECART).setName("§8● §aAbgabe Ort").setLore("§8» §2X§8: §e"+Math.round(location.getX())+"§8, §2Y§8: §e"+Math.round(location.getY())+"§8, §2Z§8: §e"+Math.round(location.getZ()), "§8» §6/abgabe").toItemStack());
        JobInventoryFarmerInfo.setItem(27, new ItemCreator(Material.DIAMOND_HOE).setName("§8● §aFarmer Hacke").setLore("§7Baue Folgende Dinge Mit deiner §aFarmer Hacke §7ab!", "", "§6§lInfo§8: §7Bringe die Abgebauten Dinge zum Abgabeort", "        §7um eine Bohnuszahlung zu bekommen!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        makeGlassBorder(JobInventoryFarmerInfo);
        JobInventoryFarmerInfo.setItem(30, new ItemCreator(HONEY_BLOCK).setName("§8» §6Honig Block").setLore("  §7-§a " + Delivery.getFarmerMoney().get(HONEY_BLOCK) + "€","  §7-" + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(HONEY_BLOCK) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(31, new ItemCreator(PUMPKIN).setName("§8» §6Kürbis").setLore("  §7-§a " + Delivery.getFarmerMoney().get(PUMPKIN) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(PUMPKIN) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(32, new ItemCreator(POTATO).setName("§8» §eKartoffel").setLore("  §7-§a " + Delivery.getFarmerMoney().get(POTATO) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(POTATO) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(33, new ItemCreator(CARROT).setName("§8» §6Karotte").setLore("  §7-§a " + Delivery.getFarmerMoney().get(CARROT) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(CARROT) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(39, new ItemCreator(WHEAT).setName("§8» §aWeizen").setLore("  §7-§a " + Delivery.getFarmerMoney().get(WHEAT) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(WHEAT) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(40, new ItemCreator(MELON_SLICE).setName("§8» §2Melone").setLore("  §7-§a " + Delivery.getFarmerMoney().get(MELON_SLICE) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(MELON_SLICE) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(41, new ItemCreator(SUGAR_CANE).setName("§8» §aZuckerrohr").setLore("  §7-§a " + Delivery.getFarmerMoney().get(SUGAR_CANE) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(SUGAR_CANE) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(42, new ItemCreator(BEETROOT).setName("§8» §cRotebete").setLore("  §7-§a " + Delivery.getFarmerMoney().get(BEETROOT) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(BEETROOT) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(48, new ItemCreator(PUMPKIN_SEEDS).setName("§8» §eKürbiskerne").setLore("  §7-§a " + Delivery.getFarmerMoney().get(PUMPKIN_SEEDS) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(PUMPKIN_SEEDS) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(49, new ItemCreator(MELON_SEEDS).setName("§8» §eMelonenkerne").setLore("  §7-§a " + Delivery.getFarmerMoney().get(MELON_SEEDS) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(MELON_SEEDS) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(50, new ItemCreator(WHEAT_SEEDS).setName("§8» §eWeizensamen").setLore("  §7-§a " + Delivery.getFarmerMoney().get(WHEAT_SEEDS) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(WHEAT_SEEDS) + " XP").toItemStack());
        JobInventoryFarmerInfo.setItem(51, new ItemCreator(BEETROOT_SEEDS).setName("§8» §cRotebetesamen").setLore("  §7-§a " + Delivery.getFarmerMoney().get(BEETROOT_SEEDS) + "€","  §7- " + TextColor.GREEN_2.get() + Delivery.getFarmerXP().get(BEETROOT_SEEDS) + " XP").toItemStack());
    }

    private static void makeGlassBorder(Inventory inventory) {
        for (int i = 20; i < 26; i++) {
            inventory.setItem(i, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        inventory.setItem(29, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(34, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(38, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(43, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(47, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(52, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
    }

    private static void addItemsToLumberjackJobInventory() {
        JobInventoryLumberjack.setItem(4, new ItemCreator(Material.STONE_AXE).setName("§8● §2§lHolzfäller").setLore("§8» §7Baue verschiedene Baum Arten ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Stein Axt mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventoryLumberjack.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
    }

    private static void addItemsToButcherJobInventory() {
        JobInventoryButcher.setItem(4, new ItemCreator(Material.STONE_SWORD).setName("§8● §d§lSchlachter").setLore("§8» §7Töte verschiedene Tiere", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Ein Holz Schwert mit §d§oSchärfe 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventoryButcher.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
    }

    private static void addItemsToMinerJobInventory() {
        JobInventoryMiner.setItem(4, new ItemCreator(Material.STONE_PICKAXE).setName("§8● §6§lMinenarbeiter").setLore("§8» §7Baue verschiedene Erze und Blöcke ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Eisen Spitzhacke mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventoryMiner.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
    }

    private static void addItemsToFisherJobInventory() {
        JobInventoryFisher.setItem(4, new ItemCreator(Material.FISHING_ROD).setName("§8● §9§lFischer").setLore("§8» §7Suche nach verschiedenen Fischen im Meer", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Angel mit §d§oGlück des Meeres", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").toItemStack());
        JobInventoryFisher.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
    }

    private static void addItemsToShopInventory() {
        ShopInventory.setItem(20, new ItemCreator(Material.ENDER_CHEST).setName("§8» §a§lItem Shop").toItemStack());
        ShopInventory.setItem(22, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8» §5§lKey Shop").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventory.setItem(24, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8» §9§lRollen Shop").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
    }

    private static void addItemsToItemShopInventory() {
        // Item Shop Mechanics
    }

    private static void addItemsToKeyShopInventory() {
        ShopInventoryKeys.setItem(4, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §8§lKohle Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Coal")+"€", "§8» §7Nutze diesen Schlüssel um die §8§lKohle Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(11, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §c§lKupfer Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Copper")+"€", "§8» §7Nutze diesen Schlüssel um die §c§lKupfer Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(29, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §6§lGold Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Gold")+"€", "§8» §7Nutze diesen Schlüssel um die §6§lGold Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(3).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(33, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §b§lDiamand Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Diamond")+"€", "§8» §7Nutze diesen Schlüssel um die §b§lDiamand Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(4).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(40, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §a§lSmaragt Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Emerald")+"€", "§8» §7Nutze diesen Schlüssel um die §a§lSmaragt Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(2).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(15, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §d§lAmethyst Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Amethyst")+"€", "§8» §7Nutze diesen Schlüssel um die §d§lAmethyst Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(6).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(22, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §8§lNetherit Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Netherite")+"€", "§8» §7Nutze diesen Schlüssel um die §8§lNetherit Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(5).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
    }

    private static void addItemsToRollShopInventory() {
        ShopInventoryRolls.setItem(13, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8● §9Fliegen").setLore("§8» §aPreis§8: §c" + InventoryPropertys.getPriceFor("Flying")+"€", "§8» §7Nutze diese Rolle um §9Fliegen §7zu können!", "", "§8» §7Klicke zum Kaufen!").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryRolls.setItem(21, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8● §6Schneller Abbauen").setLore("§8» §aPreis§8: §c" + InventoryPropertys.getPriceFor("Mining")+"€", "§8» §7Nutze diese Rolle um schnller §6Abzubauen!", "", "§8» §7Klicke zum Kaufen!").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryRolls.setItem(23, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8● §5Nacht Sicht").setLore("§8» §aPreis§8: §c" + InventoryPropertys.getPriceFor("Night vision")+"€", "§8» §7Nutze diese Rolle um in §5Nacht §7zu sehen!", "", "§8» §7Klicke zum Kaufen!").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryRolls.setItem(31, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8● §2Glück").setLore("§8» §aPreis§8: §c" + InventoryPropertys.getPriceFor("Luck")+"€", "§8» §7Nutze diese Rolle um §2Glück §7zu haben!", "", "§8» §7Klicke zum Kaufen!").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
    }
}
