package net.gemelen.blackjack.data;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.Map;

public class DataGrid {
    private static DataGrid instance = new DataGrid();
    private static final String SETTINGS_MAP = "casino";
    private static final String PLAYERS_MAP = "players";

    private Map<String, String> casino;
    private Map<Integer, PlayerRecordView> players;

    private DataGrid() {
        Config cfg = new Config();

        MapConfig mc = new MapConfig();
        mc.setName(SETTINGS_MAP);
        mc.setBackupCount(1);
        mc.setReadBackupData(true);
        cfg.addMapConfig(mc);
        MapConfig pc = new MapConfig();
        pc.setName(PLAYERS_MAP);
        pc.setBackupCount(1);
        pc.setReadBackupData(true);
        cfg.addMapConfig(pc);

        HazelcastInstance grid = Hazelcast.newHazelcastInstance(cfg);

        this.casino = grid.getMap(SETTINGS_MAP);
        this.players = grid.getMap(PLAYERS_MAP);
    }

    public static DataGrid getInstance() {
        return instance;
    }

    public Map<String, String> getCasino() {
        return casino;
    }

    public Map<Integer, PlayerRecordView> getPlayers() {
        return players;
    }
}
