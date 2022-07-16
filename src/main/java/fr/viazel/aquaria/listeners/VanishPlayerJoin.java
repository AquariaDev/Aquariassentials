package fr.viazel.aquaria.listeners;

import fr.viazel.aquaria.utils.ENV;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VanishPlayerJoin implements Listener {

    public VanishPlayerJoin() {

    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        for (Player pls : ENV.getVanishedPlayer()) {

            p.hidePlayer(pls);

        }
    }

}
