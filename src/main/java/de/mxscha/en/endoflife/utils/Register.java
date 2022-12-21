package de.mxscha.en.endoflife.utils;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.commands.economy.JobsCommand;
import de.mxscha.en.endoflife.commands.economy.MoneyCommand;
import de.mxscha.en.endoflife.commands.economy.PayCommand;
import de.mxscha.en.endoflife.commands.player.*;
import de.mxscha.en.endoflife.commands.teleport.SpawnCommand;
import de.mxscha.en.endoflife.commands.teleport.TpaCommand;
import de.mxscha.en.endoflife.commands.teleport.TpacceptCommand;
import de.mxscha.en.endoflife.commands.world.BuildCommand;
import de.mxscha.en.endoflife.commands.world.SetupCommand;
import de.mxscha.en.endoflife.listener.player.*;
import de.mxscha.en.endoflife.listener.shop.ShopAreaListener;
import de.mxscha.en.endoflife.listener.shop.ShopListener;
import de.mxscha.en.endoflife.listener.world.SpawnAreaWeatherChangeListener;
import de.mxscha.en.endoflife.utils.scoreboard.manager.item.inventory.InventoryOpener;
import de.mxscha.en.endoflife.utils.scoreboard.manager.job.entity.Employer;
import de.mxscha.en.endoflife.utils.scoreboard.manager.job.entity.Delivery;
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
        pluginManager.registerEvents(new Delivery(), core);
        pluginManager.registerEvents(new VanishDamageListener(), core);
        pluginManager.registerEvents(new SpawnAreaWeatherChangeListener(), core);
        core.getCommand("setup").setExecutor(new SetupCommand());
        core.getCommand("spawn").setExecutor(new SpawnCommand());
        core.getCommand("enderchest").setExecutor(new EnderChestCommand());
        core.getCommand("backpack").setExecutor(new BackpackCommand());
        core.getCommand("gamemode").setExecutor(new GameModeCommand());
        core.getCommand("money").setExecutor(new MoneyCommand());
        core.getCommand("invsee").setExecutor(new InvseeCommand());
        core.getCommand("jobs").setExecutor(new JobsCommand());
        core.getCommand("build").setExecutor(new BuildCommand());
        core.getCommand("vanish").setExecutor(new VanishCommand());
        core.getCommand("pay").setExecutor(new PayCommand());
        core.getCommand("tpa").setExecutor(new TpaCommand());
        core.getCommand("tpaccept").setExecutor(new TpacceptCommand());
        core.getCommand("trade").setExecutor(new TradeCommand());
        core.getCommand("playerinfo").setExecutor(new AdminPlayerInfoCommand());
        addEntities();
        InventoryOpener.initInventorys();
    }

    private static void addEntities() {
        Employer.spawn();
        Delivery.spawnAccept();
    }
}