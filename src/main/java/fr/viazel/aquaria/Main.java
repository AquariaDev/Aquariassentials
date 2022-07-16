package fr.viazel.aquaria;

import fr.viazel.aquaria.commands.InvseeCommand;
import fr.viazel.aquaria.commands.VanishCommand;
import fr.viazel.aquaria.listeners.VanishPlayerJoin;
import fr.viazel.aquaria.utils.ENV;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ENV env;

    @Override
    public void onEnable() {
        env = new ENV();
        getLogger().info("Plugin activated");
        PluginManager pm = Bukkit.getPluginManager();
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        pm.registerEvents(new VanishPlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin unloaded");

        for (Player pls : ENV.getVanishedPlayer()) {
            pls.sendMessage("§c» Le vanish a été enlevé, remettez vous en vanish si vous le souhaitez");
        }

    }

}