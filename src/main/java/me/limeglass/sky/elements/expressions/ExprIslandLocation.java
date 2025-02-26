package me.limeglass.sky.elements.expressions;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.ASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Location")
@Description("Returns the locations of the islands.")
@Examples("spawn an iron golem above the center location of player's island")
public class ExprIslandLocation extends SimplePropertyExpression<SkyblockIsland, Location> {

	static {
		register(ExprIslandLocation.class, Location.class, "island (center [location]|location)", "islands");
	}
	
	@Override
	@Nullable
	public Location convert(SkyblockIsland island) {
		return island.getIslandLocation();
	}
	
	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island center location";
	}
	
	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET && Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
			return CollectionUtils.array(Location.class);
		return null;
	}
	
	@Override
	public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
		if (delta != null) {
			Location location = (Location) delta[0];
			for (SkyblockIsland island : getExpr().getArray(e)) {
				if (island instanceof ASkyBlockIsland) {
					((ASkyBlockIsland) island).setIslandLocation(location);
				}
			}
		}
	}
	
}
