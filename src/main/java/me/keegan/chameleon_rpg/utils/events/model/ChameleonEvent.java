package me.keegan.chameleon_rpg.utils.events.model;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ChameleonEvent extends Event implements Listener {
    @NonNull
    public HandlerList getHandlers() {
        return new HandlerList();
    }

    @NonNull
    public static HandlerList getHandlerList() {
        return new HandlerList();
    }
}
