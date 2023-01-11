package me.limeglass.sky.elements.askyblock.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

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

@Name("Island Calculate")
@Description({"Calculates the islands' levels.", "ASkyBlock exclusive"})
@Examples("calculate island of player")
@RequiredPlugins("ASkyBlock")
public class EffCalculate extends Effect implements PlayerResolver {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
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
	protected void execute(Event event) {
		for (Player player : resolve(players.getArray(event)))
			api.calculateIslandLevel(player.getUniqueId());
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "ASkyBlock calculate island " + players.toString(event, debug);
	}

}
