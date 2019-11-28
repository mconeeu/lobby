package eu.mcone.lobby.story.utils;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreScoreboard;
import eu.mcone.coresystem.api.bukkit.scoreboard.MainScoreboard;
import eu.mcone.gamesystem.api.enums.Item;
import eu.mcone.gamesystem.api.game.player.GamePlayer;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import eu.mcone.lobby.items.manager.OfficeManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class JumpAndRunManager {


    public static ArrayList<Player> playjumpandrun = new ArrayList<>();


    public static void setPlayStripClubJumpAndRun(Player p) {
        GamePlayer gamePlayer = LobbyPlugin.getInstance().getGamePlayer(p.getUniqueId());
        if (!gamePlayer.hasItem(Item.HEAD_SECRET_STRIPCLUB)) {
            playjumpandrun.add(p);
            p.getInventory().clear();
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "jumpandrun_stripclub_spawn");
            p.sendMessage("§8[§7§l!§8] §fJump and Run §8» §7Du spielst nun das §eStrip Club§7 §fJump and Run§7. Zum §cbeenden §7die §fEisentür §7klicken!");
            p.getInventory().setItem(1, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§fJump and Run").lore("§cbeenden").create());
            p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus").create());
        } else {
            p.getInventory().clear();
            playjumpandrun.add(p);
            LobbyWorld.ONE_ISLAND.getWorld().teleport(p, "jumpandrun_stripclub_spawn");
            p.sendMessage("§8[§7§l!§8] §fJump and Run §8» §cDu hast dieses Jump and Run bereits gespielt. Du kannst es aber trotzdem wiederholen!");
            p.getInventory().setItem(1, new ItemBuilder(Material.IRON_DOOR, 1, 0).displayName("§fJump and Run").lore("§cbeenden").create());
            p.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus").create());
        }
    }

    public enum JumpAndRunList {
        STIPCLUB_KIRPHA("jumpandrun_stripclub_warp");


        @Getter
        private String spawnLocation;

        JumpAndRunList(final String spawnLocation) {
            this.spawnLocation = spawnLocation;
        }
    }


    public static void lobbyitems(Player p) {


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

