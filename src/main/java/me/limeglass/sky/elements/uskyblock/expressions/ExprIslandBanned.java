package me.limeglass.sky.elements.uskyblock.expressions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.PlayerResolver;
import me.limeglass.sky.interfaces.islands.IuSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

public class ExprIslandBanned extends SimpleExpression<OfflinePlayer> implements PlayerResolver {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.USKYBLOCK)
			Skript.registerExpression(ExprIslandBanned.class, OfflinePlayer.class, ExpressionType.PROPERTY, "[(all [[of] the]|the)] [island] banned members (from|of) %islands%", "[(all [[of] the]|the)] %islands%'[s] [island] banned members");
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
	protected OfflinePlayer[] get(Event e) {
		Set<OfflinePlayer> members = new HashSet<>();
		for (SkyblockIsland island : islands.getArray(e)) {
			List<String> bans = ((IuSkyBlockIsland) island).getIsland().getBans();
			for (OfflinePlayer player : resolveOffline(bans.toArray(new String[bans.size()])))
				members.add(player);
		}
		return members.toArray(new OfflinePlayer[members.size()]);
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
		return "Island banned members of " + islands.toString(e, debug);
	}
	
}
