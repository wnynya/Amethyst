package io.wany.amethyst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * Node.js style EventEmitter
 */
public class EventEmitter {
  private final HashMap<String, List<Consumer<Object[]>>> listeners;
  private final HashMap<String, List<Consumer<Object[]>>> onceListeners;

  public EventEmitter() {
    this.listeners = new HashMap<>();
    this.onceListeners = new HashMap<>();
  }

  /**
   * Add listener of event
   * @param event Name of event
   * @param callback Listener
   * @return this
   */
  public EventEmitter on(String event, Consumer<Object[]> callback) {
    if (!this.listeners.containsKey(event)) {
      this.listeners.put(event, new ArrayList<>());
    }
    this.listeners.get(event).add(callback);
    return this;
  }

  /**
   * Add listener of event, listen only once
   * @param event Name of event
   * @param callback Listener
   * @return this
   */
  public EventEmitter once(String event, Consumer<Object[]> callback) {
    if (!this.onceListeners.containsKey(event)) {
      this.onceListeners.put(event, new ArrayList<>());
    }
    this.onceListeners.get(event).add(callback);
    return this;
  }

  /**
   * Emit event to listeners
   * @param event Name of event
   * @param args Args for listeners
   * @return this
   */
  public EventEmitter emit(String event, Object... args) {
    if (this.listeners.containsKey(event)) {
      this.listeners.get(event).forEach(callback -> callback.accept(args));
    }
    if (this.onceListeners.containsKey(event)) {
      this.onceListeners.get(event).forEach(callback -> callback.accept(args));
      this.onceListeners.remove(event);
    }
    return this;
  }

}
