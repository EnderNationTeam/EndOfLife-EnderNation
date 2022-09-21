package de.mxscha.endernationendoflife;

import de.mxscha.endernationendoflife.commands.JobsCommand;
import de.mxscha.endernationendoflife.commands.*;
import de.mxscha.endernationendoflife.commands.SpawnCommand;
import de.mxscha.endernationendoflife.listener.ChatListener;
import de.mxscha.endernationendoflife.listener.CrateListener;
import de.mxscha.endernationendoflife.listener.*;
import de.mxscha.endernationendoflife.listener.generall.ShopAreaListener;
import de.mxscha.endernationendoflife.listener.shop.ShopListener;
import de.mxscha.endernationendoflife.utils.inventory.InventoryOpener;
import de.mxscha.endernationendoflife.utils.jobs.Employer;
import de.mxscha.endernationendoflife.utils.jobs.delivery.Delivery;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Register {

    public static void init(EndoflifeCore core) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ShopAreaListener(), core);
        pluginManager.registerEvents(new CrateListener(), core);
        pluginManager.registerEvents(new ChatListener(), core);
        pluginManager.registerEvents(new JoinListener(), core);
        pluginManager.registerEvents(new QuitListener(), core);
        pluginManager.registerEvents(new ShopListener(), core);
        pluginManager.registerEvents(new SpawnCommand(), core);
        pluginManager.registerEvents(new Employer(), core);
        core.getCommand("setup").setExecutor(new SetupCommand());
        core.getCommand("spawn").setExecutor(new SpawnCommand());
        core.getCommand("enderchest").setExecutor(new EnderChestCommand());
        core.getCommand("backpack").setExecutor(new BackpackCommand());
        core.getCommand("gamemode").setExecutor(new GameModeCommand());
        core.getCommand("money").setExecutor(new MoneyCommand());
        core.getCommand("invsee").setExecutor(new InvseeCommand());
        core.getCommand("jobs").setExecutor(new JobsCommand());
        addEntities();
        InventoryOpener.initInventorys();
    }

    private static void addEntities() {
        Employer.spawn();
        Delivery.spawnAccept();
    }
}