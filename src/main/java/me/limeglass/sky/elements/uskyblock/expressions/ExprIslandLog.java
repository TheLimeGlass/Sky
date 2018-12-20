package me.limeglass.sky.elements.uskyblock.expressions;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IuSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

public class ExprIslandLog extends SimpleExpression<String> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.USKYBLOCK)
			Skript.registerExpression(ExprIslandLog.class, String.class, ExpressionType.PROPERTY, "[the] log (from|of) %islands%");
	}
	
	@Nullable
	private Expression<SkyblockIsland> islands;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		islands = (Expression<SkyblockIsland>) exprs[0];
		return true;
	}
	
	@Override
	@Nullable
	protected String[] get(Event e) {
		Set<String> log = new HashSet<>();
		for (SkyblockIsland island : islands.getArray(e))
			log.addAll(((IuSkyBlockIsland)island).getIsland().getLog());
		return log.toArray(new String[log.size()]);
	}
	
	@Override
	public boolean isSingle() {
		return false;
	}
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "Island log from " + islands.toString(e, debug);
	}
	
}
