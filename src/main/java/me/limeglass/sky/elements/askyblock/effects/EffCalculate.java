package me.limeglass.sky.elements.askyblock.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.PlayerResolver;

public class EffCalculate extends Effect implements PlayerResolver {

	static {
		if (Sky.getSkyblock().isASkyBlock())
			Skript.registerEffect(EffCalculate.class, "[re]calculate island[s] (for|of) %players/strings%");
	}
	
	@Nullable
	private Expression<?> players;
	private ASkyBlockAPI api;
	
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		api = (ASkyBlockAPI) Sky.getSkyblock().getInstance();
		players = exprs[0];
		return true;
	}

	@Override
	protected void execute(Event e) {
		for (Player player : resolve(players.getArray(e)))
			api.calculateIslandLevel(player.getUniqueId());
	}
	
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ASkyBlock calculate island " + players.toString(e, debug);
	}
	
}
