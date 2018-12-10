package me.limeglass.sky.interfaces.skyblocks;

import java.util.Set;
import org.bukkit.entity.Player;

import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;

public interface Skyblock {
	
	public enum SkyblockPlugin {
		ASKYBLOCK,
		USKYBLOCK;
	}

	/**
	 * @param uuid The UUID to view the challenges of.
	 * @return The challenges of the UUID.
	 */
	Set<SkyblockChallenge> getChallenges(Player player);
	
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
