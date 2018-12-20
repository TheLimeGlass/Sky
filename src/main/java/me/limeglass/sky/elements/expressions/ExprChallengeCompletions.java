package me.limeglass.sky.elements.expressions;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Challenge Completions")
@Description("Returns the amount of completions a challenge has been done.")
@Examples({
        "loop skyblock challenges of player:",
        "\tset {_challenges::%name of loop-challenge%} to completions of loop-challenge",
        "loop {_challenges::*}:",
        "\tmessage \"&6%loop-index% was completed %loop-value% times!\""
})
public class ExprChallengeCompletions extends SimplePropertyExpression<SkyblockChallenge, Number> {

	static {
		if (Sky.getSkyblock().getPluginType() != SkyblockPlugin.SKYBLOCKEARTH)
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
