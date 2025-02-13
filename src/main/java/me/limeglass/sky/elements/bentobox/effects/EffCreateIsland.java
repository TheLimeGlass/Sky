package me.limeglass.sky.elements.bentobox.effects;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.PlayerResolver;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Create Island")
@Description({
	"Creates a new island at a location or at a location and for a player or.",
	"recreates a new island if the provided expression is an island. BentoBox syntax."
})
@Examples({
	"create a new island for player's island",
	"create an island at {_location} for uuid of event-offlineplayer",
	"create an island at {_location} for player",
	"create an island at {_location}",
})
@RequiredPlugins("BentoBox")
public class EffCreateIsland extends Effect implements PlayerResolver {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.BENTOBOX)
			Skript.registerEffect(EffCreateIsland.class,
			"create [a[n]] [new] island at %location% [for %player/string%]",
					"create [a[n]] [new] island at %location% with existing island %island%",
					"create [a[n]] [new] island for %player/string/island%"
			);
	}

	@Nullable private Expression<Location> location;
	@Nullable private Expression<?> object;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		location = (Expression<Location>) exprs[0];
		object = exprs[1];
		return true;
	}

	@Override
	protected void execute(Event event) {
		
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (object == null) {
			assert location != null;
			return "create a new island at " + location.toString(event, debug);
		} else {
			if (location != null)
				return "create a new island at " + location.toString(event, debug) + " for " + object.toString(event, debug);
			return "create a new island for " + object.toString(event, debug);
		}
	}

}
