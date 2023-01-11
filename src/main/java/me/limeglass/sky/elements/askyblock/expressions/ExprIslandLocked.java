package me.limeglass.sky.elements.askyblock.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.ASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Locked State")
@Description({"Returns the islands locked states.", "ASkyBlock exclusive"})
@Examples("set locked state of player's island to true")
@RequiredPlugins("ASkyBlock")
public class ExprIslandLocked extends SimplePropertyExpression<SkyblockIsland, Boolean> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
			register(ExprIslandLocked.class, Boolean.class, "[island] locked [state]", "islands");
	}

	@Override
	@Nullable
	public Boolean convert(SkyblockIsland island) {
		return ((ASkyBlockIsland) island).getIsland().isLocked();
	}

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	protected String getPropertyName() {
		return "island locked";
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Boolean.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta == null)
			return;
		boolean locked = (boolean) delta[0];
		for (SkyblockIsland island : getExpr().getArray(event)) {
			if (island == null)
				continue;
			if (island instanceof ASkyBlockIsland)
				((ASkyBlockIsland) island).getIsland().setLocked(locked);
		}
	}

}
