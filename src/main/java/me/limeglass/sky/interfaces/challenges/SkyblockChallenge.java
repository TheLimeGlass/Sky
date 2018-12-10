package me.limeglass.sky.interfaces.challenges;

import java.util.UUID;

public interface SkyblockChallenge {
	
	/**
	 * @return Amount of times this challenge has been completed.
	 */
	int getTimesCompleted();
	
	/**
	 * @return If this challenge belongs to ASkyBlock or uSkyBlock.
	 */
	boolean isASkyBlock();
	
	/**
	 * uSkyBlock cooldown
	 * @return If this uSkyBlock challenge has a cooldown.
	 */
	boolean hasCooldown();
	
	/**
	 * uSkyBlock cooldown
	 * @return The time on the uSkyBlock challenge.
	 */
	long getCooldown();
	
	/**
	 * @return Name of this Skyblock challenge.
	 */
	String getName();
	
	/**
	 * @return The owner of this challenge.
	 */
	UUID getOwner();
	
}
