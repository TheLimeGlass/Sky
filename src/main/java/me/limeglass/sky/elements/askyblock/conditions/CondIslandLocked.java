package me.limeglass.sky.elements.askyblock.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class CondIslandLocked extends PropertyCondition<SkyblockIsland> {

	static {
		if (Sky.getSkyblock().isASkyBlock())
			register(CondIslandLocked.class, "locked", "islands");
	}
	
	@Override
	public boolean check(SkyblockIsland island) {
		return ((IASkyBlockIsland)island).getIsland().isLocked();
	}

	@Override
	protected String getPropertyName() {
		return "locked";
	}

}
