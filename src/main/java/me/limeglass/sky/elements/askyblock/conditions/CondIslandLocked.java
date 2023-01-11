package me.limeglass.sky.elements.askyblock.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.ASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Is Locked")
@Description({"Check if islands are locked.", "ASkyBlock exclusive"})
@Examples({
	"if {_island} is locked:",
		"\tbroadcast \"This island is locked\""
})
@RequiredPlugins("ASkyBlock")
public class CondIslandLocked extends PropertyCondition<SkyblockIsland> {

	static {
		if (Sky.getSkyblock().getPluginType() == SkyblockPlugin.ASKYBLOCK)
			register(CondIslandLocked.class, "locked", "islands");
	}

	@Override
	public boolean check(SkyblockIsland island) {
		return ((ASkyBlockIsland)island).getIsland().isLocked();
	}

	@Override
	protected String getPropertyName() {
		return "locked";
	}

}
