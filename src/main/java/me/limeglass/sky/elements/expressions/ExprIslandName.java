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

@Name("Island Name")
@Description("Returns the names of the islands.")
public class ExprIslandName extends SimplePropertyExpression<SkyblockIsland, String> {

	static {
		register(ExprIslandName.class, String.class, "[island] name", "islands");
	}
	
	@Override
	@Nullable
	public String convert(SkyblockIsland island) {
		if (island instanceof IuSkyBlockIsland)
			return ((IuSkyBlockIsland) island).getIsland().getName();
		OfflinePlayer owner = island.getLeader();
		return ((ASkyBlockAPI)Sky.getSkyblock().getInstance()).getIslandName(owner.getUniqueId());
	}
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island name";
	}
	
}
