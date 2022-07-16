package fr.viazel.aquaria.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ENV {

    private static ArrayList<Player> vanishedPlayer;

    public ENV() {
        vanishedPlayer = new ArrayList<>();
    }

    public static ArrayList<Player> getVanishedPlayer() {
        return vanishedPlayer;
    }

}