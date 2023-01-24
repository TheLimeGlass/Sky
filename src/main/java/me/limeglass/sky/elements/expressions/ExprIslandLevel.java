package me.limeglass.sky.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Level")
@Description("Returns the level of the islands.")
public class ExprIslandLevel extends SimplePropertyExpression<SkyblockIsland, Number> {

	static {
		register(ExprIslandLevel.class, Number.class, "[island] level", "islands");
	}

	@Override
	@Nullable
	public Number convert(SkyblockIsland island) {
		return island.getLevel();
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	protected String getPropertyName() {
		return "island level";
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode != ChangeMode.REMOVE_ALL && Sky.getSkyblock().getPluginType() != SkyblockPlugin.USKYBLOCK)
			return CollectionUtils.array(Number.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (mode != ChangeMode.RESET && mode != ChangeMode.DELETE && delta == null)
			return;
		long level = delta != null ? (int) ((Number) delta[0]).longValue() : 0;
		switch (mode) {
			case RESET:
			case DELETE:
			case SET:
				for (SkyblockIsland island : getExpr().getArray(event))
					island.setLevel(level);
				break;
			case REMOVE:
				for (SkyblockIsland island : getExpr().getArray(event))
					island.setLevel(Math.max(island.getLevel() - level, 0));
				break;
			case ADD:
				for (SkyblockIsland island : getExpr().getArray(event))
					island.setLevel(Math.max(island.getLevel() + level, 0));
				break;
			case REMOVE_ALL:
			default:
				break;
		}
	}

}
