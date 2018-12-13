package me.limeglass.sky.elements.expressions;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock;

@Name("Player Trusted Islands")
@Description("Returns all the islands the players are trusted on.")
public class ExprPlayerTrustedIslands extends SimpleExpression<OfflinePlayer> {

	static {
		Skript.registerExpression(ExprPlayerTrustedIslands.class, OfflinePlayer.class, ExpressionType.PROPERTY, "[(all [[of] the]|the)] trusted islands (from|of) %offlineplayers%", "[(all [[of] the]|the)] %offlineplayers%'[s] trusted islands");
	}
	
	@Nullable
	private Expression<OfflinePlayer> players;
	private Skyblock skyblock;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		players = (Expression<OfflinePlayer>) exprs[0];
		skyblock = Sky.getSkyblock();
		return true;
	}
	
	@Override
	@Nullable
	protected OfflinePlayer[] get(Event e) {
		Set<SkyblockIsland> islands = new HashSet<>();
		for (OfflinePlayer player : players.getArray(e))
			islands.addAll(skyblock.getTrustedOn(player));
		return islands.toArray(new OfflinePlayer[islands.size()]);
	}
	
	@Override
	public boolean isSingle() {
		return false;
	}
	
	@Override
	public Class<? extends OfflinePlayer> getReturnType() {
		return OfflinePlayer.class;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "Trusted islands of " + players.toString(e, debug);
	}
	
}
