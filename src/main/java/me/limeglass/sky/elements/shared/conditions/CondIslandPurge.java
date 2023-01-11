package me.limeglass.sky.elements.shared.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import me.limeglass.sky.Sky;
import me.limeglass.sky.interfaces.islands.ASkyBlockIsland;
import me.limeglass.sky.interfaces.islands.BentoBoxIsland;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;
import me.limeglass.sky.interfaces.skyblocks.Skyblock.SkyblockPlugin;

@Name("Island Purge Protection")
@Description({"Check if islands have purge protection.", "BentoBox or ASkyBlock exclusive"})
@Examples({
	"if island of player is purge protected:",
		"\tbroadcast \"This island is purge protected\""
})
@RequiredPlugins("BentoBox or ASkyBlock")
public class CondIslandPurge extends PropertyCondition<SkyblockIsland> {

	static {
		if (Sky.getSkyblock().getPluginType() != SkyblockPlugin.USKYBLOCK)
			register(CondIslandPurge.class, "purge protected", "islands");
	}

	@Override
	public boolean check(SkyblockIsland island) {
		if (island instanceof BentoBoxIsland)
			return ((BentoBoxIsland)island).getIsland().getPurgeProtected();
		return ((ASkyBlockIsland)island).getIsland().isPurgeProtected();
	}

	@Override
	protected String getPropertyName() {
		return "purge protected";
	}

}
