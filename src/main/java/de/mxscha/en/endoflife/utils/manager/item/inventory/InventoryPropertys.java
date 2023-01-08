package de.mxscha.en.endoflife.utils.manager.item.inventory;

import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;


public class InventoryPropertys {

    public static Integer getPriceFor(String tier) {
        return switch (tier) {
            case "Coal" -> 1;
            case "Copper" -> 2;
            case "Gold" -> 3;
            case "Diamond" -> 4;
            case "Emerald" -> 5;
            case "Amethyst" -> 6;
            case "Netherite" -> 7;
            default -> 0;
        };
    }


    public static void setOutline(Inventory inventory) {
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        for (int i = inventory.getSize()-9; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
    }

    public static void setupTrade(Inventory inventory) {
        setOutline(inventory);
        inventory.setItem(9, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(18, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(27, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(36, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
    }

    public static void fillWithGlass(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
    }

    public static void addBackButton(Inventory inventory) {
        inventory.setItem(inventory.getSize()-9, new ItemCreator(Material.REDSTONE).setName("§8● §cZurück").toItemStack());
    }

    public static void addExitButton(Inventory inventory) {
        inventory.setItem(inventory.getSize()-5, new ItemCreator(Material.REDSTONE).setName("§8● §cSchließen").toItemStack());
    }

    public static void addCancelTradeButton(Inventory inventory) {
        inventory.setItem(inventory.getSize()-9, new ItemCreator(Material.REDSTONE).setName("§8● §cAbbrechen").toItemStack());
    }

    public static void addAcceptButton(Inventory inventory) {
        inventory.setItem(inventory.getSize()-1, new ItemCreator(Material.GREEN_CONCRETE).setName("§8● §aAbschließen").toItemStack());
    }
}
