package guild;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Guild {
    private String name;
    private int capacity;
    private Map<String, Player> roster;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new LinkedHashMap<>();
    }

    public void addPlayer(Player player) {
        if (capacity > roster.size()) {
            this.roster.put(player.getName(), player);
        }
    }

    public int count() {
        return this.roster.size();
    }

    public boolean removePlayer(String name) {
        Player player = this.roster.remove(name);
        return player != null;
    }

    public void promotePlayer(String name) {
        Player player = this.roster.get(name);

        if (player != null) {
            player.setRank("Member");
        }
    }

    public void demotePlayer(String name) {
        Player player = this.roster.get(name);

        if (player != null) {
            player.setRank("Trial");
        }
    }

    public Player[] kickPlayersByClass(String clazz) {
        Player[] playersToRemove = roster.values().stream()
                .filter(p -> p.getClazz().equals(clazz))
                .toArray(Player[]::new);

        for (Player player : playersToRemove) {
            roster.remove(player.getName());
        }

        return playersToRemove;
    }

    public String report() {
        return String.format("Players in the guild: %s:%n%s", name,
                roster.values()
                        .stream()
                        .map(Player::toString)
                        .collect(Collectors.joining(System.lineSeparator()))
        );
    }
}
