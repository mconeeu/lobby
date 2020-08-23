/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.core.player.Group;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.api.games.jumpnrun.JumpNRunGame;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.vanish.VanishManager;
import eu.mcone.lobby.games.LobbyGames;
import eu.mcone.lobby.listener.VanishListener;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class LobbyVanishManager implements VanishManager {

    private final Set<Player> silentLobbyPlayers;
    private final Map<Player, VanishPlayerVisibility> hiddenPlayers;

    public LobbyVanishManager(LobbyPlugin plugin) {
        this.silentLobbyPlayers = new HashSet<>();
        this.hiddenPlayers = new HashMap<>();

        plugin.registerEvents(new VanishListener(this));
        CoreSystem.getInstance().getVanishManager().registerVanishRule(5, (player, list) -> {
            if (silentLobbyPlayers.contains(player)) {
                list.clear();
                return;
            } else if (hiddenPlayers.containsKey(player) && !hiddenPlayers.get(player).equals(VanishPlayerVisibility.EVERYBODY)) {
                switch (hiddenPlayers.get(player)) {
                    case NOBODY: list.clear(); return;
                    case ONLY_VIPS: {
                        list.removeIf(p -> !CoreSystem.getInstance().getCorePlayer(p).getMainGroup().standsAbove(Group.PREMIUMPLUS));
                    }
                }
            }

            list.removeAll(silentLobbyPlayers);
        });
    }

    @Override
    public void setVanishPlayerVisibility(Player p, VanishPlayerVisibility target) {
        if (silentLobbyPlayers.contains(p)) {
            throw new IllegalStateException("Could not change VanishTarget from "+p.getName()+" to "+target.name()+". Player is in SilentLobby!");
        }

        hiddenPlayers.put(p, target);
        CoreSystem.getInstance().getVanishManager().recalculateVanishes();

        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
        p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
        p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);

        GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(target.equals(VanishPlayerVisibility.EVERYBODY));
        p.getInventory().setItem(0, target.getItem());
        LobbyPlugin.getInstance().getMessenger().sendSuccess(p, "Du hast die Spielersichtbarkeit auf !["+target.getName()+"] geändert!");
    }

    @Override
    public VanishPlayerVisibility getVanishPlayerVisibility(Player p) {
        return hiddenPlayers.getOrDefault(p, VanishPlayerVisibility.EVERYBODY);
    }

    @Override
    public void joinSilentLobby(Player p) {
        if (silentLobbyPlayers.add(p)) {
            hiddenPlayers.put(p, VanishPlayerVisibility.EVERYBODY);

            CoreSystem.getInstance().getVanishManager().recalculateVanishes();
            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(false);
            LobbyPlugin.getInstance().getMessenger().sendSuccess(p, "Du bist nun in der [Privaten Lobby]. Hier bist du vollkommen ungestört!");


            p.getInventory().setItem(0, HotbarItem.LOBBY_HIDER_UNAVAILABLE_SILENT_LOBBY);
            p.getInventory().setItem(2, null);
            if (LobbyGames.getInstance().getCurrentGame(p) instanceof JumpNRunGame) {
                p.getInventory().setItem(1, HotbarItem.SILENT_LOBBY_QUIT);
            }

            playSilentLobbyEffects(p);
        }
    }

    @Override
    public void quitSilentLobby(Player p) {
        if (silentLobbyPlayers.remove(p)) {
            CoreSystem.getInstance().getVanishManager().recalculateVanishes();
            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(true);
            LobbyPlugin.getInstance().getMessenger().send(p, "§7Du bist nun nicht mehr in der Privaten Lobby!");

            p.getInventory().setItem(
                    LobbyGames.getInstance().getCurrentGame(p) instanceof JumpNRunGame ? 1 : 2,
                    HotbarItem.SILENT_LOBBY_JOIN
            );
            p.getInventory().setItem(0, VanishPlayerVisibility.EVERYBODY.getItem());
        }
    }

    @Override
    public boolean isInSilentLobby(Player p) {
        return silentLobbyPlayers.contains(p);
    }

    private static void playSilentLobbyEffects(Player p) {
        p.playSound(p.getLocation(), Sound.GLASS, 1, 1);
        p.playSound(p.getLocation(), Sound.EXPLODE, 1, 1);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.VOID_FOG, 10);
    }

    public void playerLeaved(Player p) {
        silentLobbyPlayers.remove(p);
        hiddenPlayers.remove(p);
    }

}
