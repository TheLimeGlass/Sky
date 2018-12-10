package me.limeglass.sky.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.registrations.Classes;
import me.limeglass.sky.interfaces.challenges.SkyblockChallenge;

public class StaticRegistration {

	static {
		Classes.registerClass(new ClassInfo<>(SkyblockChallenge.class, "challenge")
				.user("challenges?")
				.name("Challenge")
				.description("Represents a Skyblock challenge.")
				.defaultExpression(new EventValueExpression<>(SkyblockChallenge.class)));
	}
	
}
