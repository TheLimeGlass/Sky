package me.limeglass.sky.elements.expressions;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
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
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Player Island Home")
@Description("Returns the island homes of the players.")
@Examples("teleport player-argument to island home of player")
public class ExprPlayerHome extends SimpleExpression<Location> {

	static {
		Skript.registerExpression(ExprPlayerHome.class, Location.class, ExpressionType.PROPERTY, "[island] home[s] of %offlineplayers%", "%offlineplayers%'[s] [island] home[s]");
	}

	private Expression<OfflinePlayer> players;

	@Override
	public boolean isSingle() {
		return Sky.getSkyblock().getPluginType() != SkyblockPlugin.BENTOBOX && players.isSingle();
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		players = (Expression<OfflinePlayer>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null || debug)
			return "island homes of players";
		return "island homes of " + players.toString(event, debug);
	}

	@Override
	protected @Nullable Location[] get(Event event) {
		return players.stream(event).flatMap(player -> Sky.getSkyblock().getHomeLocations(player).stream()).toArray(Location[]::new);
	}
	
}
