package de.mxscha.endernationendoflife.utils.inventory;

import de.mxscha.endernationendoflife.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class InventoryPropertys {

    public static Integer getPriceFor(String thing) {
        switch (thing) {
            case "Coal": return 1;
            case "Copper": return 2;
            case "Gold": return 3;
            case "Diamond": return 4;
            case "Emerald": return 5;
            case "Amethyst": return 6;
            case "Netherite": return 7;
        }
        return 0;
    }

    public static void fillWithGlass(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
    }

    public static void addBackButton(Inventory inventory) {
        inventory.setItem(inventory.getSize()-5, new ItemCreator(Material.REDSTONE).setName("§8● §cZurück").toItemStack());
    }

    public static void addExitButton(Inventory inventory) {
        inventory.setItem(inventory.getSize()-5, new ItemCreator(Material.REDSTONE).setName("§8● §cSchließen").toItemStack());
    }
}
