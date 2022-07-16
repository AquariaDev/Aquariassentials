package fr.viazel.aquaria.listeners;

import fr.viazel.aquaria.utils.ENV;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VanishPlayerJoin implements Listener {

    /*
        Permet de hide les gens vanished sur le serveur
     */
    @EventHandler
    public void playerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        for (Player pls : ENV.getVanishedPlayer()) {

            p.hidePlayer(pls);

        }
    }

    @EventHandler
    public void Playera(PlayerConnection e) {
        CraftPlayer p = e.getPlayer();

    }

}
