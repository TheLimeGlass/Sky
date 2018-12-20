package me.limeglass.sky.interfaces.islands;

import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

public interface SkyblockIsland {
	
	/**
	 * @return If the input location is within the island bounds.
	 */
	boolean isWithinIsland(Location location);
	
	/**
	 * @return The members of the island.
	 */
	Set<OfflinePlayer> getMembers();
	
	/**
	 * @return The online members of the island.
	 */
	List<Player> getOnlineMembers();
	
	/**
	 * @return The center location of the island.
	 */
	Location getIslandLocation();
	
	/**
	 * @return Spawn point of the island.
	 */
	Location getSpawnPoint();
	
	/**
	 * @return The attempted leader of the island.
	 */
	OfflinePlayer getLeader();
	
	/**
	 * @return The leader name of this island.
	 */
	String getLeaderName();
	
	/**
	 * @return The name of the island.
	 */
	String getName();
	
	/**
	 * @return Biome of the island.
	 */
	Biome getBiome();
	
	/**
	 * @return The level of the island
	 */
	long getLevel();
	
}
