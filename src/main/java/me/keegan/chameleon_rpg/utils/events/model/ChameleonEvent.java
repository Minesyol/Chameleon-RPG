package me.keegan.chameleon_rpg.utils.events.model;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ChameleonEvent<T extends Event> extends Event implements Listener {
    private final T event;

    public ChameleonEvent(T event) {
        this.event = event;
    }

    public T getEvent() {
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
