package me.limeglass.sky.elements.expressions;

import org.bukkit.OfflinePlayer;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

@Name("Player Island")
@Description("Returns the islands of the players.")
@Examples("set {_island} to player's island")
public class ExprIslandOfPlayer extends SimplePropertyExpression<OfflinePlayer, SkyblockIsland> {

	static {
		register(ExprIslandOfPlayer.class, SkyblockIsland.class, "island", "offlineplayers");
	}
	
	@Override
	@Nullable
	public SkyblockIsland convert(OfflinePlayer player) {
		return Sky.getSkyblock().getIslandOf(player);
	}
	
	@Override
	public Class<? extends SkyblockIsland> getReturnType() {
		return SkyblockIsland.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island";
	}
	
}
