package fr.viazel.aquaria.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            Bukkit.getLogger().severe("§aVous devez être un joueur pour éxécuter cette commande");
            return false;
        }

        Player p = (Player) sender;

        // Vérifie si un joueur n'a pas été ajouté
        if(args.length == 0) {
            p.sendMessage("§c» Vous devez rajoutez le pseudo du joueur afin d'inspecter son inventaire");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        // Si le player n'existe pas, alors on envoie un message d'erreur
        if(target == null) {
            p.sendMessage("§c» Le joueur §b" + args[0] + " §cn'existe pas !");
            return false;
        }

        // Copie l'inv du target et l'ouvre au player
        Inventory inv = target.getInventory();

        p.openInventory(inv);

        p.sendMessage("§a» Ouverture de l'inventaire de §b" + target.getName());

        return false;
    }
}