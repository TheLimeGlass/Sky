package me.limeglass.sky.elements.expressions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;

@Name("Challenge Owner")
@Description("Returns the owner of the challenge.")
public class ExprChallengeOwner extends SimplePropertyExpression<SkyblockChallenge, Player> {

	static {
		register(ExprChallengeOwner.class, Player.class, "[challenge] owner", "challenges");
	}
	
	@Override
	@Nullable
	public Player convert(SkyblockChallenge challenge) {
		return Bukkit.getPlayer(challenge.getOwner());
	}
	
	@Override
	public Class<? extends Player> getReturnType() {
		return Player.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "challenge owner";
	}
	
}
