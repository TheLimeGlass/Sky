package me.limeglass.sky.interfaces.challenges;

import java.util.UUID;

import org.bukkit.entity.Player;

import us.talabrek.ultimateskyblock.api.ChallengeCompletion;

public class IuSkyBlockChallenge implements SkyblockChallenge {

	private ChallengeCompletion challenge;
	private Player player;
	
	public IuSkyBlockChallenge(ChallengeCompletion challenge, Player player) {
		this.challenge = challenge;
		this.player = player;
	}
	
	@Override
	public int getTimesCompleted() {
		return challenge.getTimesCompleted();
	}
	
	@Override
	public boolean hasCooldown() {
		return challenge.isOnCooldown();
	}
	
	@Override
	public boolean isASkyBlock() {
		return false;
	}
	
	@Override
	public long getCooldown() {
		return challenge.getCooldownUntil();
	}
	
	@Override
	public String getName() {
		return challenge.getName();
	}

	@Override
	public UUID getOwner() {
		return player.getUniqueId();
	}
	
}
