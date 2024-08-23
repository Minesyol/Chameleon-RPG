package me.keegan.chameleon_rpg.utils.events;

public class ChameleonEvent<T> implements IChameleonEvent {
    private final T event;

    public ChameleonEvent(T event) {
        this.event = event;
    }

    public T getEvent() {
        return event;
    }
}
