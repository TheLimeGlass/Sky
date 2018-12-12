package me.limeglass.sky.elements.conditions;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class CondIslandContains extends Condition {

	static {
		Skript.registerCondition(CondIslandContains.class, "%locations% (is|are) (within|on) %islands%", "%locations% (isn't|is not|aren't|are not) (within|on) %islands%");
	}
	
	private Expression<SkyblockIsland> islands;
	private Expression<Location> locations;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		locations = (Expression<Location>) exprs[0];
		islands = (Expression<SkyblockIsland>) exprs[1];
		setNegated(matchedPattern == 1);
		return true;
	}

	@Override
	public boolean check(Event e) {
		return locations.check(e, location -> islands.check(e, island -> island.isWithinIsland(location)), isNegated());
	}
	
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "Locations " + locations.toString(e, debug) + " within islands " + islands.toString(e, debug);
	}

}
