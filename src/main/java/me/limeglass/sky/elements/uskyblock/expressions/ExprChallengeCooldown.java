package me.limeglass.sky.elements.uskyblock.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Timespan;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;

public class ExprChallengeCooldown extends SimplePropertyExpression<SkyblockChallenge, Timespan> {

	static {
		if (!Sky.getSkyblock().isASkyBlock())
			register(ExprChallengeCooldown.class, Timespan.class, "[challenge] cooldown [time]", "challenges");
	}
	
	@Override
	@Nullable
	public Timespan convert(SkyblockChallenge challenge) {
		return new Timespan(challenge.getCooldown());
	}
	
	@Override
	public Class<? extends Timespan> getReturnType() {
		return Timespan.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "challenge cooldown";
	}
	
}
