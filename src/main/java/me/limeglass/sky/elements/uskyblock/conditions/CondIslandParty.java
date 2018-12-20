package me.limeglass.sky.elements.uskyblock.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IuSkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

public class CondIslandParty extends PropertyCondition<SkyblockIsland> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.USKYBLOCK)
			register(CondIslandParty.class, PropertyType.HAVE, "[a] party", "islands");
	}
	
	@Override
	public boolean check(SkyblockIsland island) {
		return ((IuSkyBlockIsland)island).getIsland().isParty();
	}

	@Override
	protected String getPropertyName() {
		return "party";
	}

}
