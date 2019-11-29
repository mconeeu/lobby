package eu.mcone.lobby.story.jumpnrun;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.util.CoreTitle;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.api.enums.JumpNRun;
import eu.mcone.lobby.api.player.LobbyPlayer;
import eu.mcone.lobby.pets.LobbyPets;
import eu.mcone.lobby.story.LobbyStory;
import eu.mcone.lobby.story.listener.PlayerCommandPreprocessEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class JumpAndRunManager {

    public static final CoreTitle title = CoreSystem.getInstance().createTitle().fadeIn(1).fadeOut(1).stay(3).title("§cNicht überspringen!").subTitle("§4Du wurdest zurück telepotiert");

    @Getter
    private Set<JumpNRunPlayer> currentlyPlaying = new HashSet<>();

    public JumpAndRunManager(LobbyPlugin plugin) {
        plugin.registerEvents(
                new PlayerCommandPreprocessEvent()
        );

        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (JumpNRunPlayer jnrPlayer : currentlyPlaying) {
                Calendar calendarTime = Calendar.getInstance(TimeZone.getTimeZone("CEST"));
                calendarTime.setTimeInMillis(System.currentTimeMillis() - (jnrPlayer.getTime() * 1000));

                CoreSystem.getInstance().createActionBar().message("§7§oDeine Zeit:§f"
                        + (calendarTime.get(Calendar.HOUR) > 0 ? " " + calendarTime.get(Calendar.HOUR) + "h" : "")
                        + (calendarTime.get(Calendar.MINUTE) > 0 ? " " + calendarTime.get(Calendar.MINUTE) + "m" : "")
                        + (calendarTime.get(Calendar.SECOND) > 0 ? " " + calendarTime.get(Calendar.SECOND) + "s" : ""))
                        .send(jnrPlayer.getPlayer());
            }
        }, 80, 20);
    }

    public void setStart(Player p, JumpNRun jumpNRun) {
        if (!isCurrentlyPlaying(p)) {
            CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
            LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(corePlayer.getUuid());

            currentlyPlaying.add(new JumpNRunPlayer(p, jumpNRun, System.currentTimeMillis() / 1000));
            jumpandrunItems(p);
            LobbyPets.getInstance().despawnPet(p);
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jumpNRun.getStartLocation());


            if (!lp.hasJumpnrunMade(jumpNRun)) {
                p.sendMessage("§8[§7§l!§8] §fJump and Run §8» §7Du spielst nun das §e" + jumpNRun.getJumpandrunname() + "§7 §7Jump and Run. Zum §cbeenden §7die §fEisentür §7klicken!");
            } else {
                p.sendMessage("§8[§7§l!§8] §fJump and Run §8» §cDu hast dieses Jump and Run bereits gespielt kannst es aber trotzdem wiederholen. Deshalb erhälst du auch keine Belohnung mehr!");
            }
        }
    }


    public void setCheckpoint(Player p, int checkpoint) {
        JumpNRunPlayer jnrPlayer = getCurrentlyPlaying(p);

        if (jnrPlayer != null) {
            if (checkpoint <= jnrPlayer.getCheckpoint() + 1) {
                if (checkpoint == jnrPlayer.getCheckpoint() + 1) {
                    jnrPlayer.setCheckpoint(checkpoint);
                    LobbyPlugin.getInstance().getMessager().send(p, "§2Du hast den §a" + checkpoint + ". Checkpoint§2 erreicht!");
                }
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§4Du hast einen Checkpoint übersprungen und wurdest zu deinem letzten!");
                title.send(p);
                if (checkpoint == 0) {
                    LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jnrPlayer.getJumpNRun().getStartLocation());
                } else {
                    p.teleport(jnrPlayer.getJumpNRun().getCheckpoints()[checkpoint - 1]);
                }
            }
        }
    }


    public void tpToCheckpoint(Player p) {
        JumpNRunPlayer jnrPlayer = getCurrentlyPlaying(p);

        if (jnrPlayer != null) {
            if (jnrPlayer.getCheckpoint() == 0) {
                LobbyWorld.ONE_ISLAND.getWorld().teleport(p, jnrPlayer.getJumpNRun().getStartLocation());
            } else {
                p.teleport(jnrPlayer.getJumpNRun().getCheckpoints()[jnrPlayer.getCheckpoint() - 1]);
            }
        }
    }


    public void setCancel(Player p) {
        if (isCurrentlyPlaying(p)) {
            LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
            LobbyStory.getInstance().getJumpAndRunManager().lobbyitems(p);
            removePlaying(p);
            LobbyPlugin.getInstance().getMessager().send(p, "§cDu hast das Jump and Run §oerfolgreich §cbeendet!");
        }
    }


    public void setFinish(Player p) {
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);
        LobbyPlayer lp = LobbyPlugin.getInstance().getLobbyPlayer(corePlayer.getUuid());

        if (isCurrentlyPlaying(p)) {
            JumpNRunPlayer jnrPlayer = getCurrentlyPlaying(p);
            JumpNRun jumpNRun = jnrPlayer.getJumpNRun();
            int checkpoint = jnrPlayer.getCheckpoint();
            long bestTime = lp.getBestJumpNRunTime(jumpNRun);
            long time = System.currentTimeMillis() / 1000 - jnrPlayer.getTime();

            Calendar calendarTime = Calendar.getInstance(TimeZone.getTimeZone("CEST"));
            calendarTime.setTimeInMillis(time * 1000);

            if (checkpoint == jumpNRun.getCheckpoints().length) {
                if (lp.hasJumpnrunMade(jumpNRun)) {
                    LobbyPlugin.getInstance().getMessager().send(p, "§2Du hast das Jump and Run §aerfolgreich§2 wiederholt! Du hast §f"
                            + (calendarTime.get(Calendar.HOUR) > 0 ? " " + calendarTime.get(Calendar.HOUR) + " Stunden" : "")
                            + (calendarTime.get(Calendar.MINUTE) > 0 ? " " + calendarTime.get(Calendar.MINUTE) + " Minuten" : "")
                            + (calendarTime.get(Calendar.SECOND) > 0 ? " " + calendarTime.get(Calendar.SECOND) + " Sekunden" : "")
                            + "§2 gebraucht!");


                    Calendar calendarBestTime = Calendar.getInstance(TimeZone.getTimeZone("CEST"));
                    calendarBestTime.setTimeInMillis(bestTime * 1000);

                    if (time < bestTime && bestTime != -1) {
                        LobbyPlugin.getInstance().getMessager().send(p, "§aDu hast deinen alten Rekord von §7§o"
                                + (calendarBestTime.get(Calendar.HOUR) > 0 ? " " + calendarBestTime.get(Calendar.HOUR) + " Stunden" : "")
                                + (calendarBestTime.get(Calendar.MINUTE) > 0 ? " " + calendarBestTime.get(Calendar.MINUTE) + " Minuten" : "")
                                + (calendarBestTime.get(Calendar.SECOND) > 0 ? " " + calendarBestTime.get(Calendar.SECOND) + " Sekunden" : "")
                                + "§a geknackt! §oHerzlichen Glückwunsch");
                    } else if (bestTime != -1) {
                        LobbyPlugin.getInstance().getMessager().send(p, "§7Dein Rekord ist weiterhin §f§o"
                                + (calendarBestTime.get(Calendar.HOUR) > 0 ? " " + calendarBestTime.get(Calendar.HOUR) + " Stunden" : "")
                                + (calendarBestTime.get(Calendar.MINUTE) > 0 ? " " + calendarBestTime.get(Calendar.MINUTE) + " Minuten" : "")
                                + (calendarBestTime.get(Calendar.SECOND) > 0 ? " " + calendarBestTime.get(Calendar.SECOND) + " Sekunden" : "")
                                + "§7!");
                    }
                } else {
                    corePlayer.addCoins(100);
                    LobbyPlugin.getInstance().getMessager().send(p, "§2Du hast das Jump and Run §aerfolgreich §2zum erten Mal fertig gespielt! Du hast §f"
                            + (calendarTime.get(Calendar.HOUR) > 0 ? " " + calendarTime.get(Calendar.HOUR) + " Stunden" : "")
                            + (calendarTime.get(Calendar.MINUTE) > 0 ? " " + calendarTime.get(Calendar.MINUTE) + " Minuten" : "")
                            + (calendarTime.get(Calendar.SECOND) > 0 ? " " + calendarTime.get(Calendar.SECOND) + " Sekunden" : "")
                            + "§2 gebraucht! §8[§a+100 Coins§8]");
                }

                LobbyStory.getInstance().getJumpAndRunManager().lobbyitems(p);
                LobbyWorld.ONE_ISLAND.getWorld().teleportSilently(p, "spawn");
                lp.setJumpnrunBestTime(jumpNRun, time < bestTime || bestTime == -1 ? time : bestTime);
                removePlaying(p);
            } else {
                LobbyPlugin.getInstance().getMessager().send(p, "§4Du hast einen Checkpoint übersprungen und wurdest zu deinem letzten!");

                if (checkpoint == 0) {
                    p.teleport(jumpNRun.getStartPlateLocation());
                } else {
                    p.teleport(jumpNRun.getCheckpoints()[checkpoint - 1]);
                }
            }
        }
    }


    public void jumpandrunItems(Player p) {
        p.getInventory().clear();

        p.setGameMode(GameMode.SURVIVAL);
        p.getActivePotionEffects().clear();
        p.removePotionEffect(PotionEffectType.INVISIBILITY);

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);

        p.getInventory().setItem(1, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§fJump and Run").lore("§cbeenden").create());
        p.getInventory().setItem(8, new ItemBuilder(Material.INK_SACK, 1, 1).displayName("§c§lZurück zum Checkpoint").create());
        p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus").create());
    }

    private JumpNRunPlayer getCurrentlyPlaying(Player p) {
        for (JumpNRunPlayer player : currentlyPlaying) {
            if (player.getPlayer().equals(p)) {
                return player;
            }
        }
        return null;
    }

    public boolean isCurrentlyPlaying(Player p) {
        return getCurrentlyPlaying(p) != null;
    }

    private void removePlaying(Player p) {
        if (getCurrentlyPlaying() != null) {
            currentlyPlaying.remove(getCurrentlyPlaying(p));
        }
    }

    public void lobbyitems(Player p) {
        p.getInventory().clear();


        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setGameMode(GameMode.SURVIVAL);
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        p.getActivePotionEffects().clear();

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);


        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

        p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus").create());
        p.getInventory().setItem(1, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§3§lLobby-Wechsler §8» §7§oWähle deine Lobby").create());

        p.getInventory().setItem(4, new ItemBuilder(Material.COMPASS, 1, 0).displayName("§3§lNavigator §8» §7§oWähle einen Spielmodus").create());

        if (p.hasPermission("system.bungee.nick")) {
            p.getInventory().setItem(6, cp.isNicked() ?
                    new ItemBuilder(Material.NAME_TAG, 1, 0).displayName("§a§lNicken §8» §7§oAktiviert").lore("§7§oKlicke zum deaktivieren").create() :
                    new ItemBuilder(Material.NAME_TAG, 1, 0).displayName("§c§lNicken §8» §7§oDeaktiviert").lore("§7§oKlicke zum aktivieren").create()
            );
        }

        p.getInventory().setItem(7, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName("§3§lRucksack §8» §7§oZeige deine gesammelten Items an").create());

        if (p.hasPermission("lobby.silenthub")) {
            p.getInventory().setItem(2, new ItemBuilder(Material.TNT, 1, 0).displayName("§6§lPrivate Lobby §8» §7§oBetrete deine eigene Private Lobby").create());
        }

        p.getInventory().setItem(8, new Skull(p.getName(), 1).toItemBuilder().displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde").create());

        switch (cp.getMainGroup()) {
            case PREMIUM:
                p.getInventory().setBoots(Item.PREMIUM_BOOTS.getItemStack());
                break;
            case PREMIUMPLUS:
                p.getInventory().setBoots(Item.PREMIUM_PLUS_BOOTS.getItemStack());
                break;
            case YOUTUBER:
                p.getInventory().setBoots(Item.YOUTUBER_BOOTS.getItemStack());
                break;
            case JRSUPPORTER:
                p.getInventory().setBoots(Item.JR_SUPPORTER_BOOTS.getItemStack());
                break;
            case SUPPORTER:
                p.getInventory().setBoots(Item.SUPPORTER_BOOTS.getItemStack());
                break;
            case MODERATOR:
                p.getInventory().setBoots(Item.MODERATOR_BOOTS.getItemStack());
                break;
            case SRMODERATOR:
                p.getInventory().setBoots(Item.SR_MODERATOR_BOOTS.getItemStack());
                break;
            case BUILDER:
                p.getInventory().setBoots(Item.BUILDER_BOOTS.getItemStack());
                break;
            case DEVELOPER:
                p.getInventory().setBoots(Item.DEVELOPER_BOOTS.getItemStack());
                break;
            case ADMIN:
                p.getInventory().setBoots(Item.ADMIN_BOOTS.getItemStack());
                break;
        }
    }
}

