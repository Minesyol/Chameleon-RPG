package me.keegan.chameleon_rpg.utils.events.model;

import me.keegan.chameleon_rpg.utils.events.IChameleonListener;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ChameleonEvent<T extends Event> extends Event implements IChameleonListener {
    private final T event;

    public ChameleonEvent(T event) {
        this.event = event;
    }

    public T getEvent() {
        return event;
    }

    /**
     *
     *
     * @param declaredClass The class where the method was originally declared
     * @param currentClass The current class (this.getClass())
     * @return True if the method was called in the class that declared it,
     *      in others words it returns false if the method was called and if it's
     *      inherited from a super class.
     */
    public final boolean isCalledInSuperClass(Class<?> declaredClass, Class<?> currentClass) {
        return currentClass.getName().equals(declaredClass.getName());
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
