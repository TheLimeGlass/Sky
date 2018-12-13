package me.limeglass.sky.elements.expressions;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;

@Name("Player Home")
@Description("Returns the homes of the players.")
@Examples("teleport player-argument to island home of player")
public class ExprPlayerHome extends SimplePropertyExpression<OfflinePlayer, Location> {

	static {
		register(ExprPlayerHome.class, Location.class, "[island] home", "offlineplayers");
	}
	
	@Override
	@Nullable
	public Location convert(OfflinePlayer player) {
		return Sky.getSkyblock().getHomeLocation(player);
	}
	
	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "player island home";
	}
	
}
