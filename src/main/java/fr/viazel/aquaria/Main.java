package fr.viazel.aquaria;

import fr.viazel.aquaria.commands.*;
import fr.viazel.aquaria.listeners.VanishPlayerJoin;
import fr.viazel.aquaria.utils.ENV;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ENV env;


    /*
        Ce enable permet d'instancier env, et donc de préparer les variables d'environnement :)
        ainsi que les commandes et les listeners tmtc
     */
    @Override
    public void onEnable() {
        env = new ENV();
        getLogger().info("Plugin activated");
        PluginManager pm = Bukkit.getPluginManager();
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("aquahelp").setExecutor(new AquaHelp());
        getCommand("nick").setExecutor(new NickCommand());
        getCommand("tnt").setExecutor(new TntCommand());
        pm.registerEvents(new VanishPlayerJoin(), this);
    }

    /*
        Ce disable est la pour prévenir les gens vanished qu'ils ne sont plus vanish
     */
    @Override
    public void onDisable() {
        getLogger().info("Plugin unloaded");

        for (Player pls : ENV.getVanishedPlayer()) {
            pls.sendMessage("§c» Le vanish a été enlevé, remettez vous en vanish si vous le souhaitez");
        }

    }

}