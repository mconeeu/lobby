/*
 * Copyright (c) 2017 Dominik L., Rufus Maiwald, BamDev and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.inventar;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.mcone.lobby.Main;
import de.Dominik.BukkitCoreSystem.util.LocationFactory;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.List;

public class Kompass_Click {

    public Kompass_Click(InventoryClickEvent e, Player p) {
        if ((e.getCurrentItem() == null) || !e.getCurrentItem().hasItemMeta() || e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            e.setCancelled(true);
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-1"))) {
            p.teleport(LocationFactory.getConfigLocation(Main.config, "Location-Navigator-1"));
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-2"))) {
            p.teleport(LocationFactory.getConfigLocation(Main.config, "Location-Navigator-2"));
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-3"))) {
            p.teleport(LocationFactory.getConfigLocation(Main.config, "Location-Navigator-3"));
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-4"))) {
            p.teleport(LocationFactory.getConfigLocation(Main.config, "Location-Navigator-4"));
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-5"))) {
            p.teleport(LocationFactory.getConfigLocation(Main.config, "Location-Navigator-5"));
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-6"))) {
            p.teleport(LocationFactory.getConfigLocation(Main.config, "Location-Navigator-6"));
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-7"))) {
            p.teleport(LocationFactory.getConfigLocation(Main.config, "Location-Navigator-7"));
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-8"))) {
            p.teleport(LocationFactory.getConfigLocation(Main.config, "Location-Navigator-8"));
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(getItemname("Navigator-9"))) {
            p.teleport(LocationFactory.getConfigLocation(Main.config, "Location-Navigator-9"));
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);

        }
    }

    private String getItemname(String configKey) {
        gsonResult rs = new Gson().fromJson(Main.config.getConfigValue(configKey), gsonResult.class);
        return rs.name;
    }

    private class gsonResult {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("ItemID")
        @Expose
        private Integer itemID;
        @SerializedName("Lore")
        @Expose
        private List<String> lore = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getItemID() {
            return itemID;
        }

        public void setItemID(Integer itemID) {
            this.itemID = itemID;
        }

        public List<String> getLore() {
            return lore;
        }

        public void setLore(List<String> lore) {
            this.lore = lore;
        }

    }
}
