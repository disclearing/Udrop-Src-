package me.devkevin.practice.util.timer.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import me.devkevin.practice.Practice;
import me.devkevin.practice.util.timer.Timer;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class TimerClearEvent extends Event {
    private static final HandlerList HANDLERS;

    static {
        HANDLERS = new HandlerList();
    }

    private final Optional<UUID> userUUID;
    private final Timer timer;
    private Optional<Player> player;

    public TimerClearEvent(final Timer timer) {
        this.userUUID = Optional.empty();
        this.timer = timer;
    }

    public TimerClearEvent(final UUID userUUID, final Timer timer) {
        this.userUUID = Optional.of(userUUID);
        this.timer = timer;
    }

    public TimerClearEvent(final Player player, final Timer timer) {
        Objects.requireNonNull(player);
        this.player = Optional.of(player);
        this.userUUID = Optional.of(player.getUniqueId());
        this.timer = timer;
    }

    public static HandlerList getHandlerList() {
        return TimerClearEvent.HANDLERS;
    }

    public Optional<Player> getPlayer() {
        if (this.player == null) {
            this.player = (this.userUUID.isPresent() ? Optional.of(Practice.getInstance().getServer().getPlayer(this.userUUID.get())) : Optional.empty());
        }
        return this.player;
    }

    public Optional<UUID> getUserUUID() {
        return this.userUUID;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public HandlerList getHandlers() {
        return TimerClearEvent.HANDLERS;
    }
}
