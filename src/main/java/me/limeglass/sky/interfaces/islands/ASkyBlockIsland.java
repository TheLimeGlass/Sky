package me.limeglass.sky.interfaces.islands;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;

import me.limeglass.sky.Sky;

public class ASkyBlockIsland implements SkyblockIsland {

	private Island island;
	
	public ASkyBlockIsland(Island island) {
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
	public String getName() {
		return ((ASkyBlockAPI)Sky.getSkyblock().getInstance()).getIslandName(getLeader().getUniqueId());
	}

	@Override
	public Biome getBiome() {
		return island.getBiome();
	}

	@Override
	public long getLevel() {
		return ((ASkyBlockAPI)Sky.getSkyblock().getInstance()).getLongIslandLevel(getLeader().getUniqueId());
	}

	@Override
	public void setLevel(long level) {
		((ASkyBlockAPI)Sky.getSkyblock().getInstance()).setIslandLevel(getLeader().getUniqueId(), (int) level);
	}

}
