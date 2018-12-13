package me.limeglass.sky.elements.expressions;

import org.bukkit.OfflinePlayer;
import org.eclipse.jdt.annotation.Nullable;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IuSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

@Name("Island Level")
@Description("Returns the level of the islands.")
public class ExprIslandLevel extends SimplePropertyExpression<SkyblockIsland, Number> {

	static {
		register(ExprIslandLevel.class, Number.class, "[island] level", "islands");
	}
	
	@Override
	@Nullable
	public Number convert(SkyblockIsland island) {
		if (island instanceof IuSkyBlockIsland)
			return ((IuSkyBlockIsland) island).getIsland().getLevel();
		OfflinePlayer owner = island.getLeader();
		return ((ASkyBlockAPI)Sky.getSkyblock().getInstance()).getLongIslandLevel(owner.getUniqueId());
	}
	
	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island level";
	}
	
}
