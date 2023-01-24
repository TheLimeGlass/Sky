package me.limeglass.sky.interfaces.skyblocks;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;

import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public interface Skyblock {
	
	public enum SkyblockPlugin {
		ASKYBLOCK,
		USKYBLOCK,
		BENTOBOX;
	}
	
	/**
	 * @param player The player to check all islands on.
	 * @return The islands the player is trusted on.
	 */
	Collection<SkyblockIsland> getTrustedOn(OfflinePlayer player);

	/**
	 * @param uuid The UUID to view the challenges of.
	 * @return The challenges of the UUID.
	 */
	Collection<SkyblockChallenge> getChallenges(Player player);
	
	/**
	 * @param location The location to check around for a island at.
	 * @return The island if one was found.
	 */
	SkyblockIsland getIslandAt(Location location);
	
	/**
	 * @param player The player to check and get if they have an island.
	 * @return The island if one was found.
	 */
	SkyblockIsland getIslandOf(OfflinePlayer player);
	SkyblockIsland getIslandOf(@Nullable World world, OfflinePlayer player);
	
	/**
	 * @param player The player to get the home location from.
	 * @return The home locations the player has set.
	 */
	Collection<Location> getHomeLocations(OfflinePlayer player);
	
	/**
	 * @return Which Skyblock plugin is being ran.
	 */
	SkyblockPlugin getPluginType();

	/**
	 * Returns all the islands of the defined world.
	 * 
	 * @param world the world to search in.
	 * @return collection of all the islands.
	 */
	Collection<SkyblockIsland> getIslandsOf(World world);
	
	/**
	 * @return The static implementation class used by the Skyblock plugin.
	 */
	Object getInstance();
	
}
