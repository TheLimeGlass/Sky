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

@Name("Island Spawn")
@Description("Returns the spawnpoints of the islands.")
@Examples("teleport player to spawn point of player's island")
public class ExprIslandSpawnPoint extends SimplePropertyExpression<SkyblockIsland, Location> {

	static {
		register(ExprIslandSpawnPoint.class, Location.class, "island spawn [point]", "islands");
	}

	@Override
	@Nullable
	public Location convert(SkyblockIsland island) {
		return island.getSpawnPoint();
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	protected String getPropertyName() {
		return "island spawn";
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
					((ASkyBlockIsland) island).getIsland().setSpawnPoint(location);
				}
			}
		}
	}

}
