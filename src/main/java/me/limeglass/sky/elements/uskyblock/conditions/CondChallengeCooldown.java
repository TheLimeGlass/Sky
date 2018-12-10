package me.limeglass.sky.elements.uskyblock.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;

public class CondChallengeCooldown extends PropertyCondition<SkyblockChallenge> {

	static {
		if (!Sky.getSkyblock().isASkyBlock())
			register(CondChallengeCooldown.class, PropertyType.HAVE, "[a] cooldown", "challenges");
	}
	
	@Override
	public boolean check(SkyblockChallenge challenge) {
		return challenge.hasCooldown();
	}

	@Override
	protected String getPropertyName() {
		return "cooldown";
	}

}
