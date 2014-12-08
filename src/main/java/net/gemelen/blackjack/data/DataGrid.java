package net.gemelen.blackjack.data;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.Map;

public class DataGrid {
    private Map<String, String> casino;
    private Map<Integer, PlayerRecordView> players;

    public DataGrid() {
        Config cfg = new Config();
        HazelcastInstance grid = Hazelcast.newHazelcastInstance(cfg);
        this.casino = grid.getMap("casino");
        this.players = grid.getMap("players");
    }

    public Map<String, String> getCasino() {
        return casino;
    }

    public Map<Integer, PlayerRecordView> getPlayers() {
        return players;
    }
}
