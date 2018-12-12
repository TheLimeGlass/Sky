package me.limeglass.sky.elements.askyblock.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class ExprIslandLocked extends SimplePropertyExpression<SkyblockIsland, Boolean> {

	static {
		if (Sky.getSkyblock().isASkyBlock())
			register(ExprIslandLocked.class, boolean.class, "[island] locked", "islands");
	}
	
	@Override
	@Nullable
	public Boolean convert(SkyblockIsland island) {
		return ((IASkyBlockIsland) island).getIsland().isLocked();
	}
	
	@Override
	public Class<? extends Boolean> getReturnType() {
		return boolean.class;
	}
	
	@Override
	protected String getPropertyName() {
		return "island locked";
	}
	
	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(boolean.class);
		return null;
	}
	
	@Override
	public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
		if (delta != null) {
			boolean locked = (boolean) delta[0];
			for (SkyblockIsland island : getExpr().getArray(e)) {
				if (island instanceof IASkyBlockIsland) {
					((IASkyBlockIsland) island).getIsland().setLocked(locked);
				}
			}
		}
	}
	
}
