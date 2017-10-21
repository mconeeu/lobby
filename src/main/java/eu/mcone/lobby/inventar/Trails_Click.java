/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import eu.mcone.lobby.utils.Var;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Trails_Click {

    public Trails_Click(InventoryClickEvent e, Player p) {
        e.setCancelled(true);
        if (e.getCurrentItem().getItemMeta().getDisplayName() == "§5CookieTrail"){
            if(p.hasPermission("lobby.cookietrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                if(Var.musik.contains(p) || Var.ender.contains(p) || Var.glow.contains(p) || Var.snow.contains(p) || Var.wasser.contains(p) || Var.heart.contains(p)){
                    p.sendMessage(Var.pr + "§7Bitte lege deinen jetzigen Trail ab!");
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 2);
                }else{
                    if(!Var.cookies.contains(p)){
                        Var.cookies.add(p);

                        Var.lava.remove(p);
                        Var.build.remove(p);
                        Var.glow.remove(p);
                        Var.ender.remove(p);
                        Var.musik.remove(p);

                        Var.lava.remove(p);
                        Var.heart.remove(p);
                        Var.snow.remove(p);
                        Var.wasser.remove(p);
                        p.sendMessage(Var.pr + "§6CookieTrail §7aktiviert!");
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        e.getView().close();
                    }else{
                        p.sendMessage(Var.pr + "§cDu hast diesen Trail schon aktiviert!");
                    }
                }
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }else {
                p.sendMessage(Var.pr + "§cKeine permissons");
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§6GlowTrail"){
            if(p.hasPermission("lobby.glowtrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                if(Var.cookies.contains(p) || Var.ender.contains(p) || Var.musik.contains(p) || Var.snow.contains(p) || Var.wasser.contains(p) || Var.heart.contains(p)){
                    p.sendMessage(Var.pr + "§7Bitte lege deinen jetzigen Trail ab!");
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 2);
                }else{
                    if(!Var.glow.contains(p)){
                        Var.glow.add(p);

                        Var.lava.remove(p);
                        Var.build.remove(p);
                        Var.cookies.remove(p);
                        Var.ender.remove(p);
                        Var.musik.remove(p);

                        Var.lava.remove(p);
                        Var.heart.remove(p);
                        Var.snow.remove(p);
                        Var.wasser.remove(p);
                        p.sendMessage(Var.pr + "§6GlowTrail §7aktiviert!");
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        e.getView().close();
                    }else{
                        p.sendMessage(Var.pr + "§cDu hast diesen Trail schon aktiviert!");
                    }
                }
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }else {
                p.sendMessage(Var.pr + "§cKeine permissons");
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§5EnderTrail"){
            if(p.hasPermission("lobby.endertrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                if(Var.cookies.contains(p) || Var.ender.contains(p) || Var.glow.contains(p) || Var.snow.contains(p) || Var.wasser.contains(p) || Var.heart.contains(p) || Var.musik.contains(p)){
                    p.sendMessage(Var.pr + "§7Bitte lege deinen jetzigen Trail ab!");
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 2);
                }else{
                    if(!Var.ender.contains(p)){
                        Var.ender.add(p);

                        Var.lava.remove(p);
                        Var.build.remove(p);
                        Var.cookies.remove(p);
                        Var.glow.remove(p);
                        Var.musik.remove(p);

                        Var.lava.remove(p);
                        Var.heart.remove(p);
                        Var.snow.remove(p);
                        Var.wasser.remove(p);
                        p.sendMessage(Var.pr + "§5EnderTrail §7aktiviert!");
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        e.getView().close();
                    }else{
                        p.sendMessage(Var.pr + "§cDu hast diesen Trail schon aktiviert!");
                    }
                }
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }else {
                p.sendMessage(Var.pr + "§cKeine permissons");
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§aMusikTrail"){
            if(p.hasPermission("lobby.musiktrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                if(Var.cookies.contains(p) || Var.ender.contains(p) || Var.glow.contains(p) || Var.snow.contains(p) || Var.wasser.contains(p) || Var.heart.contains(p)){
                    p.sendMessage(Var.pr + "§7Bitte lege deinen jetzigen Trail ab!");
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 2);
                }else{
                    if(!Var.musik.contains(p)){
                        Var.musik.add(p);

                        Var.lava.remove(p);
                        Var.build.remove(p);
                        Var.cookies.remove(p);
                        Var.glow.remove(p);
                        Var.ender.remove(p);

                        Var.lava.remove(p);
                        Var.heart.remove(p);
                        Var.snow.remove(p);
                        Var.wasser.remove(p);
                        p.sendMessage(Var.pr + "§aMusikTrail §7aktiviert!");
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        e.getView().close();
                    }else{
                        p.sendMessage(Var.pr + "§cDu hast diesen Trail schon aktiviert!");
                    }
                }
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }else {
                p.sendMessage(Var.pr + "§cKeine permissons");
            }
        }else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§cTrail ablegen"){
            Var.cookies.remove(p);
            Var.ender.remove(p);
            Var.glow.remove(p);
            Var.musik.remove(p);
            Var.snow.remove(p);
            Var.lava.remove(p);
            Var.heart.remove(p);
            Var.wasser.remove(p);
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        }else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§cLavaTrail"){
            if(p.hasPermission("lobby.lavatrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                if(Var.cookies.contains(p) || Var.ender.contains(p) || Var.glow.contains(p) || Var.snow.contains(p) || Var.wasser.contains(p) || Var.heart.contains(p)){
                    p.sendMessage(Var.pr + "§7Bitte lege deinen jetzigen Trail ab!");
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 2);
                }else{
                    if(!Var.lava.contains(p)){
                        Var.lava.add(p);

                        Var.build.remove(p);
                        Var.cookies.remove(p);
                        Var.glow.remove(p);
                        Var.ender.remove(p);
                        Var.musik.remove(p);

                        Var.lava.remove(p);
                        Var.heart.remove(p);
                        Var.snow.remove(p);
                        Var.wasser.remove(p);
                        p.sendMessage(Var.pr + "§cLavaTrail §7aktiviert!");
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        e.getView().close();
                    }else{
                        p.sendMessage(Var.pr + "§cDu hast diesen Trail schon aktiviert!");
                    }

                }
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }else {
                p.sendMessage(Var.pr + "§cKeine permissons");
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§aHeartTrail"){
            if(p.hasPermission("lobby.hearttrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                if(Var.cookies.contains(p) || Var.ender.contains(p) || Var.glow.contains(p) || Var.snow.contains(p) || Var.wasser.contains(p) || Var.lava.contains(p)){
                    p.sendMessage(Var.pr + "§7Bitte lege deinen jetzigen Trail ab!");
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 2);
                }else{
                    if(!Var.heart.contains(p)){
                        Var.heart.add(p);

                        Var.lava.remove(p);
                        Var.build.remove(p);
                        Var.cookies.remove(p);
                        Var.glow.remove(p);
                        Var.ender.remove(p);
                        Var.musik.remove(p);

                        Var.lava.remove(p);
                        Var.snow.remove(p);
                        Var.wasser.remove(p);
                        p.sendMessage(Var.pr + "§aHeartTrail §7aktiviert!");
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        e.getView().close();
                    }else{
                        p.sendMessage(Var.pr + "§cDu hast diesen Trail schon aktiviert!");
                    }
                }
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }else {
                p.sendMessage(Var.pr + "§cKeine permissons");
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§9WaterTrail"){
            if(p.hasPermission("lobby.watertrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                if(Var.cookies.contains(p) || Var.ender.contains(p) || Var.glow.contains(p) || Var.snow.contains(p) || Var.heart.contains(p) || Var.lava.contains(p)){
                    p.sendMessage(Var.pr + "§7Bitte lege deinen jetzigen Trail ab!");
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 2);
                }else{
                    if(!Var.wasser.contains(p)){
                        Var.wasser.add(p);

                        Var.lava.remove(p);
                        Var.build.remove(p);
                        Var.cookies.remove(p);
                        Var.glow.remove(p);
                        Var.ender.remove(p);
                        Var.musik.remove(p);

                        Var.lava.remove(p);
                        Var.heart.remove(p);
                        Var.snow.remove(p);
                        p.sendMessage(Var.pr + "§9WasserTrail §7aktiviert!");
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        e.getView().close();
                    }else{
                        p.sendMessage(Var.pr + "§cDu hast diesen Trail schon aktiviert!");
                    }
                }
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            }else {
                p.sendMessage(Var.pr + "§cKeine permissons");
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§fSnowTrail") {
            if (p.hasPermission("lobby.lavatrail") || p.hasPermission("lobby.Premiumplus") || p.hasPermission("lobby.*")) {
                if (Var.cookies.contains(p) || Var.ender.contains(p) || Var.glow.contains(p) || Var.wasser.contains(p) || Var.heart.contains(p) || Var.lava.contains(p)) {
                    p.sendMessage(Var.pr + "§7Bitte lege deinen jetzigen Trail ab!");
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 2);
                } else {
                    if (!Var.snow.contains(p)) {
                        Var.snow.add(p);
                        Var.wasser.add(p);

                        Var.lava.remove(p);
                        Var.build.remove(p);
                        Var.cookies.remove(p);
                        Var.glow.remove(p);
                        Var.ender.remove(p);
                        Var.musik.remove(p);

                        Var.lava.remove(p);
                        Var.heart.remove(p);

                        p.sendMessage(Var.pr + "§fSnowTrail §7aktiviert!");
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                        e.getView().close();
                    } else {
                        p.sendMessage(Var.pr + "§cDu hast diesen Trail schon aktiviert!");
                    }
                }
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                p.closeInventory();
            } else {
                p.sendMessage(Var.pr + "§cKeine permissons");
            }
        }
    }
}
