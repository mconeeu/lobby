/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.items.command;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.command.CoreCommand;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.items.inventory.chest.ChestInfoInventory;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChestCMD extends CoreCommand {

    public ChestCMD() {
        super("chest", null, "chests");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        if (args.length == 0 && sender instanceof Player) {
            Player p = (Player) sender;

            LobbyPlugin.getInstance().getMessager().send(p, "§7Du hast momentan §d" + LobbyPlugin.getInstance().getGamePlayer(p).getChests() + " Kisten§7!");
            new ChestInfoInventory(p);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
        } else if (sender.hasPermission("lobby.chests")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {
                    CoreSystem.getInstance().getMessager().send(sender, "§4Bitte benutze: §c/chest <add | remove> <name> <amount> §4oder §c/chests <name>");
                    return true;
                } else {
                    Player t = Bukkit.getPlayer(args[0]);

                    if (t != null) {
                        sender.sendMessage("§8[§7§l!§8] §eKisten §8» §7Der Spieler §d" + t.getName() + " §7hat §d" + LobbyPlugin.getInstance().getGamePlayer(t.getUniqueId()).getChests() + " §7Kisten!");
                    } else {
                        sender.sendMessage("§8[§7§l!§8] §eKisten §8» §7Der Spieler ist nicht online!");
                    }
                    return true;
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("add")) {
                    Player t = Bukkit.getPlayer(args[1]);

                    if (t != null) {
                        LobbyPlugin.getInstance().getGamePlayer(t.getUniqueId()).addChests(Integer.valueOf(args[2]));
                        sender.sendMessage("§8[§7§l!§8] §eKisten §8» §7Du hast §d" + t.getName() + " §7erfolgreich §d" + args[2] + " §7Kisten hinzugefügt");
                    } else {
                        sender.sendMessage("§8[§7§l!§8] §eKisten §8» §7der Spieler ist nicht online!");
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("remove")) {
                    Player t = Bukkit.getPlayer(args[1]);

                    if (t != null) {
                        int current = LobbyPlugin.getInstance().getGamePlayer(t.getUniqueId()).getChests();
                        int amount = Integer.valueOf(args[2]);

                        if ((current - amount) >= 0) {
                            LobbyPlugin.getInstance().getGamePlayer(t.getUniqueId()).removeChests(Integer.valueOf(args[2]));
                            sender.sendMessage("§8[§7§l!§8] §eKisten §8» §7Du hast §d" + t.getName() + " §7erfolgreich §d" + args[2] + " §7Kisten entfernt");
                        } else {
                            sender.sendMessage("§8[§7§l!§8] §eKisten §8» §7Du kannst §d" + t.getName() + " §7nicht so viele Kisten entfernen! Er hat nur §d" + current + " §7Kisten!");
                        }
                    } else {
                        sender.sendMessage("§8[§7§l!§8] §eKisten §8»§7 Der Spieler ist nicht online!");
                    }
                    return true;
                }
            }

            CoreSystem.getInstance().getMessager().send(sender, "§4Bitte benutze: §c/chest <add | remove> <name> <amount> §4oder §c/chests <name>");
        } else if (sender instanceof Player) {
            CoreSystem.getInstance().getMessager().sendTransl((Player) sender, "system.command.noperm");
        }

        return true;
    }

}
