package me.keegan.chameleon_rpg.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ChameleonEvent extends Event implements Listener {
    private final ChameleonEvent event;

    protected ChameleonEvent() {
        event = this;
    }

    public ChameleonEvent getEvent() {
        return event;
    }

    @NonNull
    public HandlerList getHandlers() {
        return new HandlerList();
    }

    @NonNull
    public static HandlerList getHandlerList() {
        return new HandlerList();
    }
}
