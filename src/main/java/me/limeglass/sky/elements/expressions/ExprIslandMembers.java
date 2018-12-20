package me.limeglass.sky.elements.expressions;

import java.util.HashSet;
import java.util.Set;

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
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Members")
@Description("Returns the members of the islands.")
@Examples({
		"loop members of player's island:",
        "\tif name of loop-offlineplayer is string-argument:",
        "\t\tteleport string-argument parsed as player to center location of player's island",
})
public class ExprIslandMembers extends SimpleExpression<OfflinePlayer> {

	static {
		if (Sky.getSkyblock().getPluginType() != SkyblockPlugin.SKYBLOCKEARTH)
			//Usage of this expression is acceptable because of the multiple returns from a single player.
			Skript.registerExpression(ExprIslandMembers.class, OfflinePlayer.class, ExpressionType.PROPERTY, "[(all [[of] the]|the)] [island] members (from|of) %islands%", "[(all [[of] the]|the)] %islands%'[s] [island] members");
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
		for (SkyblockIsland island : islands.getArray(e))
			members.addAll(island.getMembers());
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
		return "Island members of " + islands.toString(e, debug);
	}
	
}
