package fr.viazel.aquaria.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TntCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().severe(ChatColor.translateAlternateColorCodes('&', "&cVous devez être un joueur pour éxécuter cette commande"));
            return false;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("aqua.feed")) {
            p.sendMessage("§c» Vous n'avez pas la permission !");
            return false;
        }

        if(!(args.length >= 1)) {
            p.sendMessage("§c» Vous devez rajouter un pseudo !");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {
            p.sendMessage("Non");
            return false;
        }

        target.setVelocity(new Vector(target.getLocation().getX(), target.getLocation().getY() * 10, target.getLocation().getZ()));

        p.sendMessage("§a» Tnt spawned !");

        return false;

    }
}
