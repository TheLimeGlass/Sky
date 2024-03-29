package me.limeglass.sky.elements.conditions;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

@Name("Island Contains")
@Description("Check if locations are within the islands bounds.")
@Examples({
        "while player is within player's island:",
        "\twait a second",
        "message \"&6You have left your own island's boundries.\""
})
public class CondWithinIsland extends Condition {

	static {
		Skript.registerCondition(CondWithinIsland.class, "%locations% (is|are) (within|on) %islands%", "%locations% (isn't|is not|aren't|are not) (within|on) %islands%");
	}

	private Expression<SkyblockIsland> islands;
	private Expression<Location> locations;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		locations = (Expression<Location>) exprs[0];
		islands = (Expression<SkyblockIsland>) exprs[1];
		setNegated(matchedPattern == 1);
		return true;
	}

	@Override
	public boolean check(Event event) {
		return locations.check(event, location -> islands.check(event, island -> island.isWithinIsland(location)), isNegated());
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "locations " + locations.toString(event, debug) + " within islands " + islands.toString(event, debug);
	}

}
