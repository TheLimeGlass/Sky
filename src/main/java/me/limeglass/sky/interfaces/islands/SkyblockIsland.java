package me.limeglass.sky.interfaces.islands;

import java.util.Collection;

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
	Collection<OfflinePlayer> getMembers();
	
	/**
	 * @return The online members of the island.
	 */
	Collection<Player> getOnlineMembers();
	
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

	/**
	 * Sets the level of the island.
	 * 
	 * @param level the new level.
	 */
	void setLevel(long level);
	
}
