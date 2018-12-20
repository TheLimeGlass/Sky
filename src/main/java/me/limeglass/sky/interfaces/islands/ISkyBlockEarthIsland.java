package me.limeglass.sky.interfaces.islands;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import me.goodandevil.skyblock.api.SkyBlockAPI;
import me.goodandevil.skyblock.api.island.Island;
import me.goodandevil.skyblock.api.island.IslandEnvironment;
import me.goodandevil.skyblock.api.island.IslandWorld;

public class ISkyBlockEarthIsland implements SkyblockIsland {

	private Island island;
	
	public ISkyBlockEarthIsland(Island island) {
		this.island = island;
	}
	
	public Island getIsland() {
		return island;
	}

	@Override
	public boolean isWithinIsland(Location location) {
		return SkyBlockAPI.isLocationAtIsland(island, location);
	}
	
	@Override
	public List<Player> getOnlineMembers() {
		return SkyBlockAPI.getMembersOnline(island).stream()
				.map(uuid -> Bukkit.getPlayer(uuid))
				.collect(Collectors.toList());
	}

	@Override
	public Set<OfflinePlayer> getMembers() {
		throw new UnsupportedOperationException("SkyBlockEarth does not grab members as of build 54.");
	}

	@Override
	public Location getIslandLocation() {
		return island.getLocation(IslandWorld.OVERWORLD, IslandEnvironment.ISLAND);
	}

	@Override
	public OfflinePlayer getLeader() {
		return Bukkit.getOfflinePlayer(island.getOwnerUUID());
	}

	@Override
	public String getLeaderName() {
		return getLeader().getName();
	}

	@Override
	public Location getSpawnPoint() {
		return island.getLocation(IslandWorld.OVERWORLD, IslandEnvironment.MAIN);
	}
	
	@Override
	public String getName() {
		throw new UnsupportedOperationException("SkyBlockEarth does not support names of islands.");
	}

	@Override
	public Biome getBiome() {
		return island.getBiome();
	}

	@Override
	public long getLevel() {
		return island.getLevel().getLevel();
	}
	
}
