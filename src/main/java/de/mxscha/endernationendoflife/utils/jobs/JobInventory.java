package de.mxscha.endernationendoflife.utils.jobs;

import de.mxscha.endernationendoflife.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

public class JobInventory {

    private Inventory inventory;

    public JobInventory(Player player) {
        inventory = Bukkit.createInventory(null, 9*5, "§8» §6§lJobs");
        fill();
        player.openInventory(inventory);
    }

    private void fill() {
        fillWithGlass(inventory);
        inventory.setItem(11, new ItemCreator(Material.DIAMOND_HOE)
                .setName("§8● §a§lFarmer")
                .setLore("§8» §7Baue verschiedene Arten von Essen an/ab",
                        "",
                        "§8» §6§lDu bekommst§8§l:",
                        "§8● §7Eine Diamand Hacke mit §d§oEffizienz",
                        "   §8» §8[§9Unzerstörbar§8]",
                        "   §8» §cAufstufbar!",
                        "§7Mindestlohn§8: §c60€/d",
                        "",
                        "§7Klicke für Mehr Info!")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        inventory.setItem(12, new ItemCreator(Material.STONE_AXE)
                .setName("§8● §2§lHolzfäller")
                .setLore("§8» §7Baue verschiedene Baum Arten ab",
                         "",
                         "§8» §6§lDu bekommst§8§l:",
                         "§8● §7Eine Stein Axt mit §d§oEffizienz 2",
                         "   §8» §8[§9Unzerstörbar§8]",
                         "   §8» §cAufstufbar!",
                        "§7Mindestlohn§8: §c60€/d",
                         "",
                         "§7Klicke für Mehr Info!")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        inventory.setItem(13, new ItemCreator(Material.WOODEN_SWORD)
                .setName("§8● §d§lSchlachter")
                .setLore("§8» §7Töte verschiedene Tiere",
                        "",
                        "§8» §6§lDu bekommst§8§l:",
                        "§8● §7Ein Holz Schwert mit §d§oSchärfe 2",
                        "   §8» §8[§9Unzerstörbar§8]",
                        "   §8» §cAufstufbar!",
                        "§7Mindestlohn§8: §c60€/d",
                        "",
                        "§7Klicke für Mehr Info!")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        inventory.setItem(14, new ItemCreator(Material.IRON_PICKAXE)
                .setName("§8● §6§lMinenarbeiter")
                .setLore("§8» §7Baue verschiedene Erze und Blöcke ab",
                        "",
                        "§8» §6§lDu bekommst§8§l:",
                        "§8● §7Eine Eisen Spitzhacke mit §d§oEffizienz 2",
                        "   §8» §8[§9Unzerstörbar§8]",
                        "   §8» §cAufstufbar!",
                        "§7Mindestlohn§8: §c60€/d",
                        "",
                        "§7Klicke für Mehr Info!")
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        inventory.setItem(15, new ItemCreator(Material.FISHING_ROD)
                .setName("§8● §9§lFischer")
                .setLore("§8» §7Suche nach verschiedenen Fischen im Meer",
                        "",
                        "§8» §6§lDu bekommst§8§l:",
                        "§8● §7Eine Angel mit §d§oGlück des Meeres",
                        "   §8» §8[§9Unzerstörbar§8]",
                        "   §8» §cAufstufbar!",
                        "§7Mindestlohn§8: §c60€/d",
                        "",
                        "§7Klicke für Mehr Info!")
                .toItemStack());
        addExitButton(inventory);
    }

    private void fillWithGlass(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
    }

    private void addExitButton(Inventory inventory) {
        int slot = inventory.getSize()-5;
        inventory.setItem(slot, new ItemCreator(Material.PLAYER_HEAD).createCustomSkull("§8● §cSchließen",
                "http://textures.minecraft.net/texture/beb588b21a6f98ad1ff4e085c552dcb050efc9cab427f46048f18fc803475f7").toItemStack());
    }
}
