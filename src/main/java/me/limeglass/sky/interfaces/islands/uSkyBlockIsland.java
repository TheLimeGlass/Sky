package me.limeglass.sky.interfaces.islands;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Registry;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import us.talabrek.ultimateskyblock.api.IslandInfo;

public class uSkyBlockIsland implements SkyblockIsland {

	private IslandInfo island;
	
	public uSkyBlockIsland(IslandInfo island) {
		this.island = island;
	}
	
	public IslandInfo getIsland() {
		return island;
	}

	@Override
	public boolean isWithinIsland(Location location) {
		return island.contains(location);
	}
	
	@Override
	public List<Player> getOnlineMembers() {
		return island.getOnlineMembers();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Set<OfflinePlayer> getMembers() {
		return island.getMembers().parallelStream()
				.map(player -> Bukkit.getOfflinePlayer(player))
				.collect(Collectors.toSet());
	}

	@Override
	public Location getIslandLocation() {
		return island.getIslandLocation();
	}

	@SuppressWarnings("deprecation")
	@Override
	public OfflinePlayer getLeader() {
		return Bukkit.getOfflinePlayer(island.getLeader());
	}

	@Override
	public String getLeaderName() {
		return island.getLeader();
	}

	@Override
	public Location getSpawnPoint() {
		Location location = island.getWarpLocation();
		if (location == null)
			location = island.getIslandLocation();
		return location;
	}
	
	@Override
	public String getName() {
		return island.getName();
	}

	@Override
	public Biome getBiome() {
		return Biome.valueOf(island.getBiome().toUpperCase().replace(" ", "_"));
	}

	@Override
	public long getLevel() {
		return (long) island.getLevel();
	}

	@Override
	public void setLevel(long level) {
		throw new UnsupportedOperationException();
	}
	
}
