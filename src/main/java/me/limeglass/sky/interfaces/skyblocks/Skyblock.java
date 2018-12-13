package me.limeglass.sky.interfaces.skyblocks;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public interface Skyblock {
	
	public enum SkyblockPlugin {
		ASKYBLOCK,
		USKYBLOCK;
	}
	
	/**
	 * @param player The player to check all islands on.
	 * @return The islands the player is trusted on.
	 */
	Set<SkyblockIsland> getTrustedOn(OfflinePlayer player);

	/**
	 * @param uuid The UUID to view the challenges of.
	 * @return The challenges of the UUID.
	 */
	Set<SkyblockChallenge> getChallenges(Player player);
	
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
	
	/**
	 * @param player The player to get the home location from.
	 * @return The home location the player has set.
	 */
	Location getHomeLocation(OfflinePlayer player);
	
	/**
	 * @return Which Skyblock plugin is being ran.
	 */
	SkyblockPlugin getPluginType();
	
	/**
	 * @return If the current Skyblock plugin is ASkyBlock or uSkyBlock.
	 */
	boolean isASkyBlock();
	
	/**
	 * @return The static implementation class used by the Skyblock plugin.
	 */
	Object getInstance();
	
}
