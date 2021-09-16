/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventory.modify;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Msg;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.menu.MenuInventory;
import eu.mcone.coresystem.api.bukkit.inventory.modify.CoreInventoryInitializeEntry;
import eu.mcone.coresystem.api.bukkit.inventory.modify.CoreInventoryModifier;
import eu.mcone.coresystem.api.bukkit.inventory.profile.ProfilePlayerInventory;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.lobby.Lobby;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import eu.mcone.lobby.games.LobbyGames;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ProfilePlayerInventoryModifier implements CoreInventoryModifier {

    @Override
    public void onInitialize(CoreInventoryInitializeEntry entry) {
        entry.size(
                MenuInventory.getInvSize(ProfilePlayerInventory.MAX_ITEMS + 1)
        );
    }

    @Override
    public void onCreate(CoreInventory coreInv, Player p) {
        MenuInventory inv = (MenuInventory) coreInv;
        Player target = ((ProfilePlayerInventory) inv).getTarget();
        inv.setMaxItems(inv.getMaxItems()+2);

        inv.addMenuItem(new ItemBuilder(Material.LEATHER_HELMET, 1, 0).displayName("§f§lTragen").lore("§7§oTrage den Spieler auf", "§7§odeinem Kopf.", "", "§8» §f§nLinksklick§8 | §7§oEinladen").create(), e -> {
            p.closeInventory();

            LobbyPlayer lc = LobbyPlugin.getInstance().getLobbyPlayer(target);
            LobbySettings settings = lc.getSettings();

            if (settings.isStacking()) {
                if (!LobbyGames.getInstance().isPlaying(p)) {
                    if (!LobbyPlugin.getInstance().getVanishManager().isInSilentLobby(lc.bukkit())) {
                        if (!LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(lc.bukkit()).equals(VanishPlayerVisibility.NOBODY)
                                || !LobbyPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(lc.bukkit()).equals(VanishPlayerVisibility.ONLY_VIPS)
                        ) {
                            if (p.getLocation().distance(target.getLocation()) < 8) {

                                p.setPassenger(lc.bukkit());
                                CoreSystem.getInstance().createActionBar()
                                        .message("§f§oBenutze LSHIFT um abzusteigen")
                                        .send(lc.bukkit());
                                Msg.sendSuccess(lc.bukkit(), "Du wirst nun von ![" + p.getName() + "] getragen.");
                                Msg.sendError(p, "Schleiche um ![" + lc.bukkit().getName() + "] fallen zu lassen");

                                Lobby.getSystem().getStackingManager().stack(p, target);
                            } else {
                                p.closeInventory();
                                Msg.send(p, "§4Du bist zu weit weg um den Spieler zu tragen!");
                            }
                        } else {
                            Msg.sendError(p, "Der Spieler hat seine Spielersichtbarkeit ![nicht auf alle] geschaltet!");
                        }
                    } else {
                        Msg.sendError(p, "Der Spieler ist nicht mehr auf deiner Lobby!");
                    }
                } else {
                    Msg.sendError(p, "Der Spieler spielt gerade ein Lobby-Game!");
                }
            } else {
                Msg.send(p, "Der Spieler hat diese Funktion ausgeschaltet!");
            }
        });
    }

}
