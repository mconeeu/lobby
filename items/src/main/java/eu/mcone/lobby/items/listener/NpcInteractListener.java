/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.listener;

import eu.mcone.coresystem.api.bukkit.event.npc.NpcInteractEvent;
import eu.mcone.gameapi.api.backpack.defaults.DefaultCategory;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.items.casino.CasinoMainInventory;
import eu.mcone.lobby.items.inventory.bank.BankCreateCardInventory;
import eu.mcone.lobby.items.inventory.bank.BankMenInventory;
import eu.mcone.lobby.items.inventory.office.ChauffeurInventory;
import eu.mcone.lobby.items.inventory.office.OfficeTraderInventory;
import eu.mcone.lobby.items.inventory.office.secretary.SecretaryInventory;
import eu.mcone.lobby.items.inventory.trader.TraderInventory;
import eu.mcone.lobby.items.manager.OfficeManager;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcInteractListener implements Listener {

    @EventHandler
    public void onNpcInteract(NpcInteractEvent e) {
        Player p = e.getPlayer();
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);

        if (e.getNpc().getData().getType().equals(EntityType.PLAYER) && e.getAction().equals(PacketPlayInUseEntity.EnumEntityUseAction.INTERACT)) {
            String npcName = e.getNpc().getData().getName();

            if (npcName.equalsIgnoreCase(StoryNPC.MERCHANT.getNpcName()) || npcName.equalsIgnoreCase(StoryNPC.MERCHANT2.getNpcName())
                    || npcName.equalsIgnoreCase(StoryNPC.OFFICE_TRADER.getNpcName())) {
                new TraderInventory(p);
            } else if (npcName.equalsIgnoreCase(StoryNPC.BANKMAN.getNpcName()) || npcName.equalsIgnoreCase(StoryNPC.BANKMAN2.getNpcName())) {
                if (!lp.hasLobbyItem(LobbyItem.BANKCARD) && !lp.hasLobbyItem(LobbyItem.BANKCARD_PREMIUM)) {
                    new BankCreateCardInventory(p);
                } else {
                    new BankMenInventory(p);
                }
            } else if (npcName.equalsIgnoreCase(StoryNPC.OFFICE_SELLER.getNpcName())) {
                new OfficeTraderInventory(p);
            } else if (npcName.equalsIgnoreCase(StoryNPC.OFFICE_PAGE.getNpcName())) {
                OfficeManager.joinOffice(p);
            } else if (npcName.equalsIgnoreCase(StoryNPC.ASSISTANT_1.getNpcName())
                    || npcName.equalsIgnoreCase(StoryNPC.ASSISTANT_2.getNpcName())
                    || npcName.equalsIgnoreCase(StoryNPC.ASSISTANT_3.getNpcName())) {
                new SecretaryInventory(p);
            } else if (npcName.equalsIgnoreCase(StoryNPC.CHAUFFEUR_1.getNpcName())
                    || npcName.equalsIgnoreCase(StoryNPC.CHAUFFEUR_2.getNpcName())
                    || npcName.equalsIgnoreCase(StoryNPC.CHAUFFEUR_3.getNpcName())) {
                new ChauffeurInventory(p);
            } else if (npcName.equalsIgnoreCase(StoryNPC.VENDOR.getNpcName())) {
                LobbyPlugin.getInstance().getBackpackManager().openBackpackSellInventory(DefaultCategory.PET.name(), p);
            } else if (npcName.equalsIgnoreCase(StoryNPC.CASINO.getNpcName())) {
                new CasinoMainInventory(p);
            }
        }
    }

    public enum StoryNPC {
        CASINO("casino"),
        MERCHANT("merchant"),
        MERCHANT2("merchant2"),
        BANKMAN("bankman"),
        BANKMAN2("bankman-central"),
        OFFICE_TRADER("officeTrader"),
        OFFICE_SELLER("officeSeller"),
        OFFICE_PAGE("officePage"),
        ASSISTANT_1("assistentin1"),
        ASSISTANT_2("assistentin2"),
        ASSISTANT_3("assistentin3"),
        CHAUFFEUR_1("Chauffeur"),
        CHAUFFEUR_2("Chauffeur1"),
        CHAUFFEUR_3("Chauffeur2"),
        VENDOR("vendor");

        @Getter
        private final String npcName;

        StoryNPC(final String npcName) {
            this.npcName = npcName;
        }
    }
}
