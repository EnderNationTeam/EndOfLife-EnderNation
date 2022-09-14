package de.mxscha.endernationendoflife.utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.UUID;

public class ItemCreator {

    /*
        ItemCreator by Mxscha (NovaGames HeadDev):
        Version 1.8.9 - 1.19
        How to Use: new ItemCreator(MATERIAL).setName("Name").setLore("Lore").toItemStack();
                                                 |-> you can also add more...         |-> this is required
     */

    private ItemStack item;
    private ItemMeta itemMeta;
    private static final Base64 base64 = new Base64();

    // subID = 1.8.9 Builder -> creates the ItemStack
    public ItemCreator(Material material, short subID) {
        this.item = new ItemStack(material, 1, subID);
        this.itemMeta = this.item.getItemMeta();
    }

    // For newer versions -> creates the ItemStack
    public ItemCreator(Material material) {
        this(material, (short)0);
    }

    // adding an Enchantment to your Item
    public ItemCreator addEnchantment(Enchantment enchantment, int amount, boolean test) {
        this.itemMeta.addEnchant(enchantment, amount, test);
        return this;
    }

    // adding the player head texture to the player head -> requires PLAYER_HEAD
    public ItemCreator setSkull(String displayName, Player player, String... lore) {
        SkullMeta skullMeta = (SkullMeta) this.itemMeta;
        skullMeta.setDisplayName(displayName);
        skullMeta.setLore(Arrays.asList(lore));
        skullMeta.setOwningPlayer(Bukkit.getPlayer(player.getUniqueId()));
        this.itemMeta = skullMeta;
        return this;
    }

    // adding the player head texture to the player head -> requires PLAYER_HEAD
    public ItemCreator setSkull(String displayName, String player, String... lore) {
        SkullMeta skullMeta = (SkullMeta) this.itemMeta;
        skullMeta.setDisplayName(displayName);
        skullMeta.setLore(Arrays.asList(lore));
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(player));
        this.itemMeta = skullMeta;
        return this;
    }

    public ItemCreator createCustomSkull(String DisplayName, String url, String... lore) {
        /*
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }
        byte[] encodedData = base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        propertyMap.put("textures", new Property("textures", new String(encodedData)));
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        Reflections.getField(headMetaClass, "profile", GameProfile.class).set(headMeta, profile);
        headMeta.setDisplayName(DisplayName);
        headMeta.setLore(Arrays.asList(lore));
        this.itemMeta = headMeta;
        return this;
         */
        return null;
    }

    // setting the Name of the Item
    public ItemCreator setName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    // setting the Lore of the Item
    public ItemCreator setLore(String... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    // setting the Color of Leather Armor
    public ItemCreator setColor(Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) this.itemMeta;
        meta.setColor(color);
        return this;
    }

    // adding a PotionEffect to your Potion -> requires POTION
    public ItemCreator addPotionEffect(PotionEffectType effect, int duration, int amplifier, Color PotionColor) {
        PotionMeta meta = (PotionMeta) this.itemMeta;
        meta.addCustomEffect(new PotionEffect(effect, duration, amplifier), false);
        meta.setColor(PotionColor);
        return this;
    }

    // setting a PotionEffect to your Potion -> requires POTION
    public ItemCreator setPotionEffect(PotionEffectType effect, int duration, int amplifier, Color PotionColor) {
        PotionMeta meta = (PotionMeta) this.itemMeta;
        meta.addCustomEffect(new PotionEffect(effect, duration, amplifier), true);
        meta.setColor(PotionColor);
        return this;
    }

    // setting the item Unbreackable
    public ItemCreator setUnbreakable(boolean isUnbreakable) {
        this.itemMeta.setUnbreakable(isUnbreakable);
        return this;
    }

    // setting the Amount of your Item
    public ItemCreator setAmount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    // adding ItemFlags to your Item
    public ItemCreator addItemFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(itemFlag);
        return this;
    }

    // removing ItemFlags from your Item
    public ItemCreator removeItemFlag(ItemFlag itemFlag) {
        this.itemMeta.removeItemFlags(itemFlag);
        return this;
    }

    // creating the Item
    public ItemStack toItemStack() {
        this.item.setItemMeta(this.itemMeta);
        return this.item;
    }
}
