package de.mxscha.en.endoflife.utils.manager.item.clear;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class ClearLagManager {

    static BukkitTask clearLag;
    private static int items = 0;
    private static final ArrayList<Item> Aitems = new ArrayList<>();

    public static void start() {
        clearLag = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getWorlds().forEach(world -> {
                    world.getEntities().forEach(entity -> {
                        if (entity instanceof Item) {
                            if (!Aitems.contains(entity)) {
                                Aitems.add((Item) entity);
                                items++;
                            }
                        }
                    });
                });
                if (items > 1000) {
                    cancel();
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.sendMessage(Messages.PREFIX.get() + "§cIn §c§l15 Sekunden §cwerden alle Items auf dem Boden gelöscht!");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                    });
                    new BukkitRunnable() {
                        int seconds = 15;
                        @Override
                        public void run() {
                            switch (seconds) {
                                case 5, 4, 3, 2 -> Bukkit.getOnlinePlayers().forEach(player -> {
                                    player.sendMessage(Messages.PREFIX.get() + "§cIn §c§l" + seconds + " Sekundem §cwerden alle Items auf dem Boden gelöscht!");
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                                });
                                case 1 -> Bukkit.getOnlinePlayers().forEach(player -> {
                                    player.sendMessage(Messages.PREFIX.get() + "§cIn §c§l" + seconds + " Sekunde §cwerden alle Items auf dem Boden gelöscht!");
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                                });
                                case 0 -> {
                                    Bukkit.getOnlinePlayers().forEach(player -> {
                                        player.sendMessage(Messages.PREFIX.get() + "§cEs wurden alle Items auf dem Boden gelöscht!");
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                                    });
                                    Bukkit.getWorlds().forEach(world -> {
                                        world.getEntities().forEach(entity -> {
                                            if (entity instanceof Item item) {
                                                item.remove();
                                            }
                                        });
                                    });
                                    start();
                                    items = 0;
                                    cancel();
                                }
                            }
                            seconds--;
                        }
                    }.runTaskTimer(EndoflifeCore.getInstance(), 0, 20);
                }

                if (items > 2000) {
                    cancel();
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                    });
                    new BukkitRunnable() {
                        int seconds = 3;
                        @Override
                        public void run() {
                            switch (seconds) {
                                case 3, 2 -> Bukkit.getOnlinePlayers().forEach(player -> {
                                    player.sendMessage(Messages.PREFIX.get() + "§cIn §c§l" + seconds + " Sekundem §cwerden alle Items auf dem Boden gelöscht!");
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                                });
                                case 1 -> Bukkit.getOnlinePlayers().forEach(player -> {
                                    player.sendMessage(Messages.PREFIX.get() + "§cIn §c§l" + seconds + " Sekunde §cwerden alle Items auf dem Boden gelöscht!");
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                                });
                                case 0 -> {
                                    Bukkit.getOnlinePlayers().forEach(player -> {
                                        player.sendMessage(Messages.PREFIX.get() + "§cEs wurden alle Items auf dem Boden gelöscht!");
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                                    });
                                    Bukkit.getWorlds().forEach(world -> {
                                        world.getEntities().forEach(entity -> {
                                            if (entity instanceof Item item) {
                                                item.remove();
                                            }
                                        });
                                    });
                                    start();
                                    items = 0;
                                    cancel();
                                }
                            }
                            seconds--;
                        }
                    }.runTaskTimer(EndoflifeCore.getInstance(), 0, 20);
                }
            }
        }.runTaskTimer(EndoflifeCore.getInstance(), 0, 20);
    }

    public static void stop() {
        if (clearLag != null)
            clearLag.cancel();
    }
}
