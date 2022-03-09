package me.limeglass.sky.interfaces.islands;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import world.bentobox.bentobox.database.objects.Island;

public class IBentoBoxIsland implements SkyblockIsland {

	private Island island;
	
	public IBentoBoxIsland(Island island) {
		this.island = island;
	}
	
	public Island getIsland() {
		return island;
	}

	@Override
	public boolean isWithinIsland(Location location) {
		return island.inIslandSpace(location);
	}

	@Override
	public Set<Player> getOnlineMembers() {
		return island.getMemberSet().parallelStream()
				.map(uuid -> Bukkit.getPlayer(uuid))
				.filter(player -> player != null)
				.collect(Collectors.toSet());
	}

	@Override
	public Set<OfflinePlayer> getMembers() {
		return island.getMemberSet().parallelStream()
				.map(uuid -> Bukkit.getOfflinePlayer(uuid))
				.filter(player -> player != null)
				.collect(Collectors.toSet());
	}

	@Override
	public Location getIslandLocation() {
		return island.getCenter();
	}

	@Override
	public OfflinePlayer getLeader() {
		UUID owner = island.getOwner();
		if (owner == null)
			return null;
		return Bukkit.getOfflinePlayer(owner);
	}

	@Override
	public String getLeaderName() {
		OfflinePlayer owner = getLeader();
		if (owner == null)
			return null;
		return getLeader().getName();
	}

	@Override
	public Location getSpawnPoint() {
		return island.getSpawnPoint().get(Environment.NORMAL);
	}

	@Override
	public String getName() {
		return island.getName();
	}

	@Override
	public Biome getBiome() {
		return null;
	}

	@Override
	public long getLevel() {
		return 0;
	}
	
}
