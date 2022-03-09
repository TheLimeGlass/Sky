package me.limeglass.sky.interfaces.challenges;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.World;

import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.challenges.ChallengesAddon;
import world.bentobox.challenges.managers.ChallengesManager;

public class IBentoBoxChallenge implements SkyblockChallenge {

	private ChallengesManager challenges;
	private final String challenge;
	private final UUID uuid;

	public IBentoBoxChallenge(String challenge, UUID uuid) throws IllegalAccessException {
		this.challenge = challenge;
		this.uuid = uuid;
		Optional<Addon> addon = BentoBox.getInstance().getAddonsManager().getAddonByName("Challenges");
		if (!addon.isPresent())
			throw new IllegalAccessException("Challenges was not loaded.");
		challenges = ((ChallengesAddon) addon.get()).getChallengesManager();
	}

	@Override
	public int getTimesCompleted() {
		World world = null;
		return (int) challenges.getChallengeTimes(User.getInstance(uuid), world, challenge);
	}

	@Override
	public boolean hasCooldown() {
		return false;
	}
	
	@Override
	public boolean isASkyBlock() {
		return false;
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
		return uuid;
	}
	
}
