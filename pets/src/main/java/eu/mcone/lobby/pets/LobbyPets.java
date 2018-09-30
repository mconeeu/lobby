/*
 * Copyright (c) 2017 - 2018 Dominik Lippl, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.pets;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.lobby.api.LobbyAddon;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.enums.Category;
import eu.mcone.lobby.api.enums.Item;
import eu.mcone.lobby.items.inventory.backpack.BackpackInventory;
import eu.mcone.lobby.pets.inventory.backpack.AnimalInventory;
import eu.mcone.lobby.pets.listener.PlayerInteractEntity;
import eu.mcone.lobby.pets.listener.PlayerMove;
import eu.mcone.lobby.pets.listener.ProjectileLaunch;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreature;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class LobbyPets extends LobbyAddon {

    @Getter
    private static LobbyPets instance;

    private HashMap<UUID, Entity> pets;

    @Override
    public void onEnable() {
        instance = this;
        this.pets = new HashMap<>();

        BackpackInventory.registerBackpackInventory(Category.ANIMAL, AnimalInventory.class);

        LobbyPlugin.getInstance().registerEvents(
                new PlayerInteractEntity(),
                new PlayerMove(),
                new ProjectileLaunch()
        );
    }

    @Override
    public void onDisable() {
        for (Entity entity : pets.values()) {
            entity.remove();
        }
        pets.clear();
    }

    @Override
    public void reload() {}

    public void spawnPet(Player p, Item item) {
        if (item.hasCategory() && item.getCategory().equals(Category.ANIMAL)) {
            despawnPet(p);
            EntityType type = null;

            switch (item) {
                case ANIMAL_PIG:
                    type = EntityType.PIG;
                    break;
                case ANIMAL_WITHER:
                    type = EntityType.WITHER;
                    break;
                case ANIMAL_SHEEP:
                    type = EntityType.SHEEP;
                    break;
                case ANIMAL_MUSHROOM_COW:
                    type = EntityType.MUSHROOM_COW;
                    break;
                case ANIMAL_CHICKEN:
                    type = EntityType.CHICKEN;
                    break;
            }

            Entity entity = p.getWorld().spawnEntity(p.getLocation(), type);
            entity.setCustomName("§b§o " + p.getName() + "s "+ item.getName());
            entity.setCustomNameVisible(true);

            pets.put(p.getUniqueId(), entity);
        }
    }

    public boolean hasPet(Player p) {
        return pets.containsKey(p.getUniqueId());
    }

    public Entity getEntity(Player p) {
        return pets.getOrDefault(p.getUniqueId(), null);
    }

    public Collection<Entity> getPets() {
        return pets.values();
    }

    public void renamePet(Player p, String name) {
        if (pets.containsKey(p.getUniqueId())) {
            pets.get(p.getUniqueId()).setCustomName(name);
        }
    }

    public void ride(Player p) {
        if (pets.containsKey(p.getUniqueId())) {
            pets.get(p.getUniqueId()).setPassenger(p);
            CoreSystem.getInstance().createActionBar()
                    .message("§f§oBenutze LSHIFT um abszusteigen")
                    .send(p);
        }
    }

    public void despawnPet(Player p) {
        if (pets.containsKey(p.getUniqueId())) {
            pets.get(p.getUniqueId()).remove();
            pets.remove(p.getUniqueId());
        }
    }

    public void followPlayer(Player p) {
        Location location = p.getLocation();
        Creature creature = (Creature) pets.get(p.getUniqueId());

        if (location.distanceSquared(creature.getLocation()) > 100) {
            if (((Entity) p).isOnGround()) {
                creature.teleport(p);
            }
        } else {
            ((CraftCreature) creature).getHandle().getNavigation().a(location.getX(), location.getY(), location.getZ(), 1.4);
        }

    }

    public void unregisterPlayer(Player p) {
        despawnPet(p);
    }

}
