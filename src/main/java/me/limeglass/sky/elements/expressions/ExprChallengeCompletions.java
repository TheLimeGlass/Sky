package me.limeglass.sky.elements.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;

public class ExprChallengeCompletions extends SimplePropertyExpression<SkyblockChallenge, Number> {

	static {
		register(ExprChallengeCompletions.class, Number.class, "[challenge] completions", "challenges");
	}
	
	@Override
	@Nullable
	public Number convert(SkyblockChallenge challenge) {
		return challenge.getTimesCompleted();
	}
	
	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "challenge completions";
	}
	
}
