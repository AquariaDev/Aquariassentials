package fr.viazel.aquaria.commands;

import fr.viazel.aquaria.utils.ENV;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            Bukkit.getLogger().severe("§aVous devez être un joueur pour éxécuter cette commande");
            return false;
        }

        Player p = (Player) sender;

        // Vérifie si le player est vanish ou non, et effectue les fonctions appropriées
        if(ENV.getVanishedPlayer().contains(p)) {
            ENV.getVanishedPlayer().remove(p);
            putThePlayerUnvanished(p);
            p.sendMessage("§c» Vous n'êtes plus en vanish !");
        }else {
            ENV.getVanishedPlayer().add(p);
            putThePlayerVanished(p);
            p.sendMessage("§a» Vous êtes désormais en vanish !");
        }

        return false;
    }

    // Vanish le joueur
    public static void putThePlayerVanished(Player p) {
        for (Player pls : Bukkit.getOnlinePlayers()) {
            pls.hidePlayer(p);
        }
    }

    // UnVanish le joueur
    public static void putThePlayerUnvanished(Player p) {
        for (Player pls : Bukkit.getOnlinePlayers()) {
            pls.showPlayer(p);
        }
    }

}