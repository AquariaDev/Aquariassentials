package fr.viazel.aquaria.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().severe("§aVous devez être un joueur pour éxécuter cette commande");
            return false;
        }

        Player p = (Player) sender;

        if(!p.hasPermission("aqua.feed")) {
            p.sendMessage("§c» Vous n'avez pas la permission !");
            return false;
        }

        if(args.length >= 1) {

            Player target = Bukkit.getPlayer(args[0]);

            if(target == null) {
                p.sendMessage("§c» Le joueur §b" + args[0] + " §cn'existe pas !");
                return false;
            }

            feedPlayer(target);

            target.sendMessage("§a» Vous avez été rassasié par §b" + p.getName() + " §a!");
            p.sendMessage("§a» Vous avez rassasié §b" + target.getName() + " §a!");

            return true;
        }

        feedPlayer(p);

        p.sendMessage("§a» Vous êtes rassasié !");

        return false;

    }

    public void feedPlayer(Player p){
        p.setFoodLevel(20);
        p.setSaturation(20);
    }

}
