package fr.viazel.aquaria.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Random;

public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().severe("§aVous devez être un joueur pour éxécuter cette commande");
            return false;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("aqua.nick")) {
            p.sendMessage("§c» Vous n'avez pas la permission !");
            return false;
        }

        if (args.length == 0) {
            p.sendMessage("§c» Vous devez rajouter un nouveau pseudo !");
            return false;
        }

        if(args[0].equalsIgnoreCase("clear")) {

            if(args.length >= 2) {
                Player target = Bukkit.getPlayer(args[1]);

                if(target == null) {
                    p.sendMessage("§c» Le joueur §b" + args[1] + " §cn'existe pas !");
                    return false;
                }

                target.sendMessage("§a» Votre pseudo a été clear !");
                p.sendMessage("§a» Le pseudo de §b" + target.getDisplayName() + " §aa été clear !");

                changeName(target, target.getName());

                return false;
            }

            p.sendMessage(p.getName());

            changeName(p, p.getName());
            p.sendMessage("§a» Votre pseudo est clear !");
            return false;
        }

        if (args[0].length() > 10) {
            p.sendMessage("§c» Votre pseudo est supérieur à 10 charactères !");
            return false;
        }

        if(args.length >= 2) {

            Player target = Bukkit.getPlayer(args[1]);

            if(target == null) {
                p.sendMessage("§c» Le joueur §b" + args[1] + " §cn'existe pas !");
                return false;
            }

            changeName(target, args[0]);

            target.sendMessage("§a» Votre pseudo a été changé en §b" + args[0] + " §a!");
            p.sendMessage("§a» Vous avez modifé le pseudo de §b" + target.getName() + " §aen §b" + args[0] + " §a!");

            return true;
        }

        changeName(p, args[0]);

        p.sendMessage("§a» Votre nouveau pseudo est §b" + args[0] + " §a!");

        return false;
    }

    public static void changeName(Player p, String name) {

        p.setPlayerListName(name);
        p.setDisplayName(name);

        try {

            GameProfile gameProfile = ((CraftPlayer) p).getHandle().getProfile();

            Field field = gameProfile.getClass().getDeclaredField("name");

            field.setAccessible(true);

            field.set(gameProfile, name);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        VanishCommand.putThePlayerVanished(p);
        VanishCommand.putThePlayerUnvanished(p);

        changeSkin(p);

    }

    public static void changeSkin(Player p) {
        GameProfile profile = ((CraftPlayer) p).getProfile();
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;

        connection.sendPacket(new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, ((CraftPlayer) p).getHandle()));

        profile.getProperties().removeAll("textures");
//        profile.getProperties().put("textures", getSkin(p.getName()));

        connection.sendPacket(new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, ((CraftPlayer) p).getHandle()));

        p.setHealth(0);
        p.spigot().respawn();
    }

}