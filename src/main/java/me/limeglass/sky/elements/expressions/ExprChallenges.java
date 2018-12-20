package me.limeglass.sky.elements.expressions;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.PlayerResolver;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.skyblocks.Skyblock;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Skyblock Challenges")
@Description("Returns the challenges of a player.")
@Examples({
        "loop skyblock challenges of player:",
        "\tset {_challenges::%name of loop-challenge%} to completions of loop-challenge",
        "loop {_challenges::*}:",
        "\tmessage \"&6%loop-index% was completed %loop-value% times!\""
})
public class ExprChallenges extends SimpleExpression<SkyblockChallenge> implements PlayerResolver {

	static {
		if (Sky.getSkyblock().getPluginType() != SkyblockPlugin.SKYBLOCKEARTH)
			//Usage of this expression is acceptable because of the multiple returns from a single player.
			Skript.registerExpression(ExprChallenges.class, SkyblockChallenge.class, ExpressionType.PROPERTY, "[(all [[of] the]|the)] [(island|sky[ ]block)] challenges (from|of) %players/strings%", "[(all [[of] the]|the)] %players/strings%'[s] [(island|sky[ ]block)] challenges");
	}
	
	@Nullable
	private Expression<?> players;
	private Skyblock skyblock;
	
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		skyblock = Sky.getSkyblock();
		players = exprs[0];
		return true;
	}
	
	@Override
	@Nullable
	protected SkyblockChallenge[] get(Event e) {
		Set<SkyblockChallenge> challenges = new HashSet<>();
		for (Player player : resolve(players.getArray(e)))
			challenges.addAll(skyblock.getChallenges(player));
		return challenges.toArray(new SkyblockChallenge[challenges.size()]);
	}
	
	@Override
	public boolean isSingle() {
		return false;
	}
	
	@Override
	public Class<? extends SkyblockChallenge> getReturnType() {
		return SkyblockChallenge.class;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "Challenges of " + players.toString(e, debug);
	}
	
}
