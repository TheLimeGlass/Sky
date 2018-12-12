package me.limeglass.sky.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.registrations.Classes;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;
import me.limeglass.sky.interfaces.islands.SkyblockIsland;

public class StaticRegistration {

	static {
		Classes.registerClass(new ClassInfo<>(SkyblockChallenge.class, "challenge")
				.user("challenges?")
				.name("Challenge")
				.description("Represents a Skyblock challenge.")
				.defaultExpression(new EventValueExpression<>(SkyblockChallenge.class)));
		Classes.registerClass(new ClassInfo<>(SkyblockIsland.class, "island")
				.user("islands?")
				.name("Island")
				.description("Represents a Skyblock island.")
				.defaultExpression(new EventValueExpression<>(SkyblockIsland.class)));
	}
	
}
