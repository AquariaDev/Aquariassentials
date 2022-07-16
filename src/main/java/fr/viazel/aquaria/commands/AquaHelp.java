package fr.viazel.aquaria.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AquaHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().severe("§aVous devez être un joueur pour éxécuter cette commande");
            return false;
        }

        Player p = (Player) sender;
        p.sendMessage("§9-----------> §bAQUAHELP §9<-----------");
        p.sendMessage("§9-> §b/feed");
        p.sendMessage("§9-> §b/feed §c<Joueur>§f: §7Pour se nourrir");
        p.sendMessage("§9-> §b/invsee §c<Joueur>§f: §7Pour voir l'inventaire d'autre");
        p.sendMessage("§9-> §b/vanish§f: §7Pour se mettre invisible");
        p.sendMessage("§9---------------------------------");

        return false;
    }
}