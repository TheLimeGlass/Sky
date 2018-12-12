package me.limeglass.sky.elements.expressions;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock;

public class ExprIslandAt extends SimpleExpression<SkyblockIsland> {

	static {
		Skript.registerExpression(ExprIslandAt.class, SkyblockIsland.class, ExpressionType.COMBINED, "island[s] at %locations%");
	}
	
	private Expression<Location> locations;
	private Skyblock instance;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.locations = (Expression<Location>) exprs[0];
		this.instance = Sky.getSkyblock();
		return true;
	}
	
	@Override
	@Nullable
	protected SkyblockIsland[] get(Event e) {
		Set<SkyblockIsland> islands = new HashSet<>();
		for (Location location : locations.getArray(e)) {
			islands.add(instance.getIslandAt(location));
		}
		return islands.toArray(new SkyblockIsland[islands.size()]);
	}

	@Override
	public boolean isSingle() {
		return locations.isSingle();
	}

	@Override
	public Class<? extends SkyblockIsland> getReturnType() {
		return SkyblockIsland.class;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "Islands at " + locations.toString(e, debug);
	}
	
}
