package me.limeglass.sky.elements.expressions;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
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
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

@Name("Island Online Members")
@Description("Returns the online members of the islands.")
@Examples({
        "on chat:",
        "\tif {skyblock::player::%player%::memberchat} is true:",
        "\t\tmessage \"&e[&aIslandChat&e] &r%message%\" to all online memebers of player's island"
})
public class ExprIslandOnlineMembers extends SimpleExpression<Player> {

	static {
		//Usage of this expression is acceptable because of the multiple returns from a single player.
		Skript.registerExpression(ExprIslandOnlineMembers.class, Player.class, ExpressionType.PROPERTY, "[(all [[of] the]|the)] online [island] members (from|of) %islands%", "[(all [[of] the]|the)] %islands%'[s] online [island] members");
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
	protected Player[] get(Event e) {
		Set<OfflinePlayer> members = new HashSet<>();
		for (SkyblockIsland island : islands.getArray(e))
			members.addAll(island.getOnlineMembers());
		return members.toArray(new Player[members.size()]);
	}
	
	@Override
	public boolean isSingle() {
		return false;
	}
	
	@Override
	public Class<? extends Player> getReturnType() {
		return Player.class;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "Island memebers of " + islands.toString(e, debug);
	}
	
}
