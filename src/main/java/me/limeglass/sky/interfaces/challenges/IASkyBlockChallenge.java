package me.limeglass.sky.interfaces.challenges;

import java.util.UUID;

public class IASkyBlockChallenge implements SkyblockChallenge {

	private String challenge;
	private int completions;
	private UUID owner;
	
	public IASkyBlockChallenge(String challenge, int completions, UUID owner) {
		this.completions = completions;
		this.challenge = challenge;
		this.owner = owner;
	}
	
	@Override
	public int getTimesCompleted() {
		return completions;
	}
	
	@Override
	public boolean hasCooldown() {
		return false;
	}
	
	@Override
	public boolean isASkyBlock() {
		return true;
	}
	
	@Override
	public long getCooldown() {
		return 0;
	}
	
	@Override
	public String getName() {
		return challenge;
	}

	@Override
	public UUID getOwner() {
		return owner;
	}
	
}
