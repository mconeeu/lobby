/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.util;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.PlayerInventorySlot;
import eu.mcone.coresystem.api.core.player.Group;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.games.jumpnrun.JumpNRunGame;
import eu.mcone.lobby.api.player.HotbarItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.vanish.VanishManager;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.games.LobbyGames;
import eu.mcone.lobby.listener.VanishListener;
import eu.mcone.lobby.story.LobbyStory;
import lombok.Getter;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LobbyVanishManager implements VanishManager {

    @Getter
    private final Set<Player> silentLobbyPlayers;
    private final Map<Player, VanishPlayerVisibility> hiddenPlayers;

    public LobbyVanishManager(LobbyPlugin plugin) {
        this.silentLobbyPlayers = new HashSet<>();
        this.hiddenPlayers = new HashMap<>();

        plugin.registerEvents(new VanishListener(this));
        CoreSystem.getInstance().getVanishManager().registerVanishRule(5, (player, playerCanSee) -> {
            if (silentLobbyPlayers.contains(player)) {
                playerCanSee.clear();
                return;
            } else if (hiddenPlayers.containsKey(player) && !hiddenPlayers.get(player).equals(VanishPlayerVisibility.EVERYBODY)) {
                switch (hiddenPlayers.get(player)) {
                    case NOBODY:
                        playerCanSee.clear();
                        return;
                    case ONLY_VIPS: {
                        playerCanSee.removeIf(p -> !CoreSystem.getInstance().getCorePlayer(p).getMainGroup().standsAbove(Group.PREMIUMPLUS));
                    }
                }
            }

            playerCanSee.removeAll(silentLobbyPlayers);
        });
    }

    @Override
    public boolean setVanishPlayerVisibility(Player p, VanishPlayerVisibility target) {
        return setVanishPlayerVisibility(p, target, true);
    }

    @Override
    public boolean setVanishPlayerVisibility(Player p, VanishPlayerVisibility target, boolean notify) {
        if (silentLobbyPlayers.contains(p)) {
            throw new IllegalStateException("Could not change VanishTarget from " + p.getName() + " to " + target.name() + ". Player is in SilentLobby!");
        }

        if (!hiddenPlayers.containsKey(p) || !hiddenPlayers.get(p).equals(target)) {
            hiddenPlayers.put(p, target);
            CoreSystem.getInstance().getVanishManager().recalculateVanishes();

            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
            Sound.play(p, org.bukkit.Sound.LAVA_POP);
            p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);

            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(target.equals(VanishPlayerVisibility.EVERYBODY));

            if (LobbyGames.getInstance().getCurrentGame(p) instanceof JumpNRunGame) {
                p.getInventory().setItem(0, target.getItem());
            } else {
                p.getInventory().setItem(7, target.getItem());
            }

            if (notify) {
                Msg.sendSuccess(p, "Du hast die Spielersichtbarkeit auf ![" + target.getName() + "] geändert!");
            }

            return true;
        }

        return false;
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
            Msg.sendSuccess(p, "Du bist nun in der ![Privaten Lobby]. Hier bist du vollkommen ungestört!");

            if (LobbyGames.getInstance().getCurrentGame(p) instanceof JumpNRunGame) {
                p.getInventory().setItem(PlayerInventorySlot.HOTBAR_SLOT_1, HotbarItem.LOBBY_HIDER_UNAVAILABLE_SILENT_LOBBY);
                p.getInventory().setItem(PlayerInventorySlot.HOTBAR_SLOT_2, HotbarItem.SILENT_LOBBY_QUIT);
            } else {
                LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, LobbyPlugin.getInstance().getLobbyPlayer(p));
            }

            playSilentLobbyEffects(p);
        }
    }

    @Override
    public void quitSilentLobby(Player p) {
        if (silentLobbyPlayers.remove(p)) {
            CoreSystem.getInstance().getVanishManager().recalculateVanishes();
            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(true);
            Msg.send(p, "§7Du bist nun nicht mehr in der Privaten Lobby!");
            LobbyPlayer lobbyPlayer = LobbyPlugin.getInstance().getLobbyPlayer(p);

            lobbyPlayer.getCorePlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();

            if (!(LobbyGames.getInstance().getCurrentGame(p) instanceof JumpNRunGame)) {
                p.getInventory().setItem(7, VanishPlayerVisibility.EVERYBODY.getItem());

                LobbyPlugin.getInstance().getHotbarSettings().updateInventory(p, lobbyPlayer);

            } else {
                p.getInventory().setItem(1,
                        HotbarItem.SILENT_LOBBY_JOIN
                );

                p.getInventory().setItem(0, VanishPlayerVisibility.EVERYBODY.getItem());
            }

            playSilentLobbyEffects(p);
        }
    }

    @Override
    public boolean isInSilentLobby(Player p) {
        return silentLobbyPlayers.contains(p);
    }

    @Override
    public boolean isInOffice(Player p) {
        return LobbyStory.getInstance().getOfficeManager().isInOffice(p);
    }

    private static void playSilentLobbyEffects(Player p) {
        Sound.play(p, org.bukkit.Sound.GLASS);
        Sound.play(p, org.bukkit.Sound.EXPLODE);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 10);
        p.playEffect(p.getLocation(), Effect.VOID_FOG, 10);
    }

    public void playerLeaved(Player p) {
        silentLobbyPlayers.remove(p);
        hiddenPlayers.remove(p);
    }

}
