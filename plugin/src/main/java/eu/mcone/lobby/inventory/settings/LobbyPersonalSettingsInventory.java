package eu.mcone.lobby.inventory.settings;

import eu.mcone.coresystem.api.bukkit.facades.Sound;
import eu.mcone.coresystem.api.bukkit.inventory.settings.Setting;
import eu.mcone.coresystem.api.bukkit.inventory.settings.SettingsInventory;
import eu.mcone.coresystem.api.bukkit.inventory.settings.settings.BooleanSetting;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.items.LobbyItem;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.api.player.settings.JoinPlayerVisibility;
import eu.mcone.lobby.api.player.settings.LobbySettings;
import eu.mcone.lobby.api.player.settings.SpawnPoint;
import eu.mcone.lobby.api.player.settings.SpawnVillage;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyPersonalSettingsInventory extends SettingsInventory {

    public LobbyPersonalSettingsInventory(Player p) {
        super("§8» §c§lPersönlich", p, e -> new LobbySettingsInventory(p));
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(p);
        GamePlayer gp = lp.getGamePlayer();
        LobbySettings settings = lp.getSettings();

        JoinPlayerVisibility[] visibilities = p.hasPermission("lobby.silenthub")
                ? JoinPlayerVisibility.values()
                : new JoinPlayerVisibility[]{JoinPlayerVisibility.NO_PREFERENCE, JoinPlayerVisibility.PLAYERHIDER};
        addSetting(new Setting<>(
                new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).displayName("§f§lJoinTyp").create(),
                (player, result) -> {
                    settings.setJoinPlayerVisibility(result);
                    setSettings(p, lp);
                },
                player -> settings.getJoinPlayerVisibility(),
                visibilities
        ));

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.LEATHER_HELMET, 1, 0).displayName("§f§lTragen lassen").create(), null)
                        .onChoose((player, result) -> {
                            settings.setStacking(result);
                            setSettings(p, lp);
                        })
                        .optionFinder(player -> settings.isStacking())
                        .setEnabledDescription("§7§oAndere Spieler können dich", "§7§ohin und her tragen")
                        .setDisabledDescription("§7§oAndere Spieler können dich nicht", "§7§ohin und her tragen")
                        .create()
        );

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).displayName("§f§lErhalte Rang Schuhe").create(), null)
                        .onChoose((player, result) -> {
                            settings.setRankBoots(result);
                            if (result) {
                                LobbyPlugin.getInstance().getBackpackManager().setRankBoots(player);
                            } else {
                                player.getInventory().setBoots(null);
                            }

                            setSettings(p, lp);
                        })
                        .optionFinder(player -> settings.isRankBoots())
                        .setEnabledDescription("§7§oKlicke um keine Rang Schuhe zu erhalten", "§7§owenn du den Server beitritts!")
                        .setDisabledDescription("§7§oKlicke um Rang Schuhe zu erhalten", "§7§owenn du den Server beitritts!")
                        .create()
        );

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§f§lGadgets").create(), null)
                        .onChoose((player, result) -> {
                            gp.getSettings().setEnableGadgets(result);
                            setSettings(p, lp);
                        })
                        .optionFinder(player -> gp.getSettings().isEnableGadgets())
                        .setEnabledDescription("§7§oKlicke um die Gadgets", "§7§ofür dich selbst zu deaktivieren", "§7§odann erhälst du keine Gadgets", "§7§oEffekte mehr und du selbst kannst", "§7§okeine mehr ausrüsten")
                        .setDisabledDescription("§7§oKlicke um die Gadgets für dich zu aktivieren", "§7§ound um die Effekte der anderen zu sehen")
                        .create()
        );

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName("§f§lTauschen").create(), null)
                        .onChoose((player, result) -> {
                            gp.getSettings().setEnableTraiding(result);
                            setSettings(p, lp);
                        })
                        .optionFinder(player -> gp.getSettings().isEnableTraiding())
                        .setEnabledDescription("§7§oKlicke um das Tauschen von", "§7§oRucksack Items zu deaktivieren")
                        .setDisabledDescription("§7§oKlicke um das Tauschen von", "§7§oRucksack Items zu aktivieren")
                        .create()
        );

        addSetting(
                new BooleanSetting(new ItemBuilder(Material.PAPER, 1, 0).displayName("§f§lLobbyGames Einladungen").create(), null)
                        .onChoose((player, result) -> {
                            settings.setLobbyGamesInvite(result);
                            setSettings(p, lp);
                        })
                        .optionFinder(player -> settings.isLobbyGamesInvite())
                        .setEnabledDescription("§7§oAndere Spieler können dich", "§7§ozu LobbyGames einladen")
                        .setDisabledDescription("§7§oAndere Spieler können dich nicht", "§7§ozu LobbyGames einladen")
                        .create()
        );

        SpawnPoint[] spawnPoints = lp.hasLobbyItem(LobbyItem.OFFICE_CARD_BRONZE) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_SILVER) || lp.hasLobbyItem(LobbyItem.OFFICE_CARD_GOLD)
                ? SpawnPoint.values()
                : new SpawnPoint[]{SpawnPoint.SPAWN, SpawnPoint.LAST_LOCATION};
        addSetting(new Setting<>(
                new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§f§lSpawn Ort").create(),
                (player, result) -> {
                    settings.setSpawnPoint(result);
                    setSettings(p, lp);
                },
                player -> settings.getSpawnPoint(),
                spawnPoints
        ));

        addSetting(new Setting<>(
                new ItemBuilder(Material.LONG_GRASS, 1, 0).displayName("§f§lHaupt-Dorf").create(),
                (player, result) -> {
                    settings.setSpawnVillage(result);
                    setSettings(p, lp);
                },
                player -> settings.getSpawnVillage(),
                SpawnVillage.values()
        ));

        openInventory();
    }

    private void setSettings(Player p, LobbyPlayer lp) {
        lp.saveData();
        Sound.click(p);
    }
}
