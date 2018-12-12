package me.limeglass.sky.interfaces.islands;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import com.wasteofplastic.askyblock.Island;

public class IASkyBlockIsland implements SkyblockIsland {

	private Island island;
	
	public IASkyBlockIsland(Island island) {
		this.island = island;
	}

	@Override
	public boolean isWithinIsland(Location location) {
		return island.inIslandSpace(location);
	}

	@Override
	public Location getIslandLocation() {
		return island.getCenter();
	}
	
	public Island getIsland() {
		return island;
	}
	
	public void setIslandLocation(Location location) {
		island.setCenter(location);
	}
	
	@Override
	public Set<OfflinePlayer> getMembers() {
		return island.getMembers().parallelStream()
				.map(uuid -> Bukkit.getOfflinePlayer(uuid))
				.collect(Collectors.toSet());
	}
	
	@Override
	public List<Player> getOnlineMembers() {
		return island.getMembers().parallelStream()
				.map(uuid -> Bukkit.getPlayer(uuid))
				.collect(Collectors.toList());
	}
	
	@Override
	public OfflinePlayer getLeader() {
		return Bukkit.getOfflinePlayer(island.getOwner());
	}
	
	public void setLeader(OfflinePlayer player) {
		island.setOwner(player.getUniqueId());
	}

	@Override
	public String getLeaderName() {
		return Bukkit.getOfflinePlayer(island.getOwner()).getName();
	}

	@Override
	public Location getSpawnPoint() {
		return island.getSpawnPoint();
	}

	@Override
	public Biome getBiome() {
		return island.getBiome();
	}
	
}
