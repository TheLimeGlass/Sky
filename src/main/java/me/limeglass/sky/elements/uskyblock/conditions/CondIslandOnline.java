package me.limeglass.sky.elements.uskyblock.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IuSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class CondIslandOnline extends PropertyCondition<SkyblockIsland> {

	static {
		if (!Sky.getSkyblock().isASkyBlock())
			register(CondIslandOnline.class, PropertyType.HAVE, "online members", "islands");
	}
	
	@Override
	public boolean check(SkyblockIsland island) {
		return ((IuSkyBlockIsland)island).getIsland().hasOnlineMembers();
	}

	@Override
	protected String getPropertyName() {
		return "online members";
	}

}
