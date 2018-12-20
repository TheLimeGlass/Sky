package me.limeglass.sky.elements.askyblock.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.IASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Purge Protection")
@Description({"Check if islands have purge protection.", "ASkyBlock exclusive"})
@Examples({
        "if island of player is purge protected:",
        "\tbroadcast \"This island is purge protected\""
})
public class CondIslandPurge extends PropertyCondition<SkyblockIsland> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
			register(CondIslandPurge.class, "purge protected", "islands");
	}
	
	@Override
	public boolean check(SkyblockIsland island) {
		return ((IASkyBlockIsland)island).getIsland().isPurgeProtected();
	}

	@Override
	protected String getPropertyName() {
		return "purge protected";
	}

}
