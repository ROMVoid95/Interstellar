package net.rom.exoplanets.init;

import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_b;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_c;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_d;
import static net.rom.exoplanets.conf.SConfigSystems.yzceti_tier;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import net.rom.api.stellar.AstroBuilder;
import net.rom.api.stellar.impl.planet.ExoPlanet;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.astronomy.yzcetisystem.b.WorldProviderYzCetiB;
import net.rom.exoplanets.astronomy.yzcetisystem.c.WorldProviderYzCetiC;
import net.rom.exoplanets.astronomy.yzcetisystem.d.WorldProviderYzCetiD;
import net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.YzCetiDBiomes;
import net.rom.exoplanets.conf.SConfigSystems;

public class InitPlanets {

	public static Float[] yzCetiAu = { 0.3F, 0.4f, 0.55f };

	public static ExoPlanet yzcetib;
	public static ExoPlanet yzcetic;
	public static ExoPlanet yzcetid;

	// Wolf 1061IBuild
	private static Float[] wolfAu = { 0.2F, 0.4f, 2.5f };
	public static ExoPlanet wolf1061b;
	public static ExoPlanet wolf1061c;
	public static ExoPlanet wolf1061d;

	// HD 219134
	private static Float[] hdAu = { 0.2F, 0.3f, 0.4f, 0.6f, 0.8f, 2.7f };
	public static ExoPlanet hd219134b;
	public static ExoPlanet hd219134c;
	public static ExoPlanet hd219134d;
	public static ExoPlanet hd219134f;
	public static ExoPlanet hd219134g;
	public static ExoPlanet hd219134h;

	// Trappist 1
	private static Float[] trappistAu = { 0.3F, 0.4f, 0.6f, 0.8f, 1.0f, 1.2f, 1.6f };
	public static ExoPlanet trappistb;
	public static ExoPlanet trappistc;
	public static ExoPlanet trappistd;
	public static ExoPlanet trappiste;
	public static ExoPlanet trappistf;
	public static ExoPlanet trappistg;
	public static ExoPlanet trappisth;

	static AstroBuilder builder = new AstroBuilder("exoplanets");

	public static void init() {
		InitPlanets.initPlanets();
		InitPlanets.registerPlanetData();
		InitPlanets.registerPlanets();
		InitPlanets.unreachables();
		InitPlanets.registerTeleportTypes();

	}

	public static void initPlanets() {

		SolarSystem yzCet = IniSystems.yzCeti;

		yzcetib = builder.buildExoPlanet(yzCet, "yz_ceti_b", WorldProviderYzCetiB.class, id_yz_b, yzceti_tier, 0.5F);
		builder.setData(yzcetib, ClassBody.SELENA, yzCetiAu[0], 0.015f, 0.4f, 0, 0L);
		builder.setNormalOrbit(yzcetib);
		builder.setExoData(yzcetib, 50.0f, 0.75f, 0.93f);
		builder.setAtmos(yzcetib, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
		builder.setBiomes(yzcetib, ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);

		yzcetic = builder.buildExoPlanet(yzCet, "yz_ceti_c", WorldProviderYzCetiC.class, id_yz_c, yzceti_tier, 0.1F);
		builder.setData(yzcetic, ClassBody.SELENA, yzCetiAu[1], 0.010f, 0.5f, 0, 19500L);
		builder.setNormalOrbit(yzcetic);
		builder.setExoData(yzcetic, 26.5f, 0.9f, 1.0f);
		builder.setAtmos(yzcetic, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
		builder.setBiomes(yzcetic, ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);

		yzcetid = builder.buildExoPlanet(yzCet, "yz_ceti_d", WorldProviderYzCetiD.class, id_yz_d, yzceti_tier, 0.9F);
		builder.setData(yzcetid, ClassBody.SELENA, yzCetiAu[2], 0.005f, 0.6f, 0, 26500L);
		builder.setOrbit(yzcetid, 1.0f, 1.0f, 4.5f, 4.5f);
//		builder.setOrbit(yzcetid, 1.0f, 1.0f, 4.0f, 4.0f);
//		builder.setOrbit(yzcetid, 1.0f, 1.0f, 5.0f, 5.0f);
		//builder.setOrbit(yzcetid, 1.3f, 1.5f, 5.0f, 1.3f);
		//builder.setOrbit(yzcetid, 2.0f, 1.5f, 0.0f, 1.3f);
		builder.setExoData(yzcetid, 5.0f, 1.14f, 1.05f);
		builder.setAtmos(yzcetid, EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
		builder.setBiomes(yzcetid, YzCetiDBiomes.yz_ceti_d, YzCetiDBiomes.yz_ceti_d_mantle);
	}

	public static void registerPlanetData() {
		// builder.setData(body, class, distance, gravity, orbit, pressure, day);
		// builder.setExoData(body, temp, mass, radius);

	}

	public static void unreachables() {

		if (!SConfigSystems.hideUnfinishedSystems) {
			// Wolf 1061
			wolf1061b = builder.buildUnreachablePlanet("wolf_1061_b", IniSystems.wolf1061, 2.9495497F, wolfAu[0]);
			builder.setData(wolf1061b, ClassBody.SELENA,wolfAu[0],  0.45F, wolfAu[0], 0, 24000L);

			wolf1061c = builder.buildUnreachablePlanet("wolf_1061_c", IniSystems.wolf1061, 1.7264397F, wolfAu[1]);
			builder.setData(wolf1061c, ClassBody.SELENA,wolfAu[1],  0.45F, wolfAu[1], 0, 24000L);

			wolf1061d = builder.buildUnreachablePlanet("wolf_1061_d", IniSystems.wolf1061, 7.132725F, wolfAu[2]);
			builder.setData(wolf1061d, ClassBody.SELENA,wolfAu[2],  0.45F, wolfAu[2], 0, 24000L);

			// HD 219134
			hd219134b = builder.buildUnreachablePlanet("hd_219134_b", IniSystems.hd219134, 0.292421F, hdAu[0]);
			builder.setData(hd219134b, ClassBody.SELENA,hdAu[0],  0.45F, hdAu[0], 0, 24000L);

			hd219134c = builder.buildUnreachablePlanet("hd_219134_c", IniSystems.hd219134, 2.9495497F, hdAu[1]);
			builder.setData(hd219134c, ClassBody.SELENA,hdAu[1],  0.45F, hdAu[1], 0, 24000L);

			hd219134d = builder.buildUnreachablePlanet("hd_219134_d", IniSystems.hd219134, 0.896365F, hdAu[2]);
			builder.setData(hd219134d, ClassBody.SELENA,hdAu[2],  0.45F, hdAu[2], 0, 24000L);

			hd219134f = builder.buildUnreachablePlanet("hd_219134_f", IniSystems.hd219134, 4.305977F, hdAu[3]);
			builder.setData(hd219134f, ClassBody.SELENA,hdAu[3],  0.45F, hdAu[3], 0, 24000L);

			hd219134g = builder.buildUnreachablePlanet("hd_219134_g", IniSystems.hd219134, 1.932375F, hdAu[4]);
			builder.setData(hd219134g, ClassBody.SELENA,hdAu[4],  0.45F, hdAu[4], 0, 24000L);

			hd219134h = builder.buildUnreachablePlanet("hd_219134_h", IniSystems.hd219134, 7.906747F, hdAu[5]);
			builder.setData(hd219134h, ClassBody.SELENA,hdAu[5],  0.45F, hdAu[5], 0, 24000L);

			// Trappist 1
			trappistb = builder.buildUnreachablePlanet("trappist_b", IniSystems.trappist1, 3.254752F, trappistAu[0]);
			builder.setData(trappistb, ClassBody.SELENA,trappistAu[0],  0.45F, trappistAu[0], 0, 24000L);
			
			trappistc = builder.buildUnreachablePlanet("trappist_c", IniSystems.trappist1, 0.6451158F, trappistAu[1]);
			builder.setData(trappistc, ClassBody.SELENA,trappistAu[1],  0.45F, trappistAu[1], 0, 24000L);
			
			trappistd = builder.buildUnreachablePlanet("trappist_d", IniSystems.trappist1, 1.932375F, trappistAu[2]);
			builder.setData(trappistd, ClassBody.SELENA,trappistAu[2],  0.45F, trappistAu[2], 0, 24000L);
			
			trappiste = builder.buildUnreachablePlanet("trappist_e", IniSystems.trappist1, 4.305977F, trappistAu[3]);
			builder.setData(trappiste, ClassBody.SELENA,trappistAu[3],  0.45F, trappistAu[3], 0, 24000L);
			
			trappistf = builder.buildUnreachablePlanet("trappist_f", IniSystems.trappist1, 0.6451158F, trappistAu[4]);
			builder.setData(trappistf, ClassBody.SELENA,trappistAu[4],  0.45F, trappistAu[4], 0, 24000L);
			
			trappistg = builder.buildUnreachablePlanet("trappist_g", IniSystems.trappist1, 0.6451158F, trappistAu[5]);
			builder.setData(trappistg, ClassBody.SELENA,trappistAu[5],  0.45F, trappistAu[5], 0, 24000L);
			
			trappisth = builder.buildUnreachablePlanet("trappist_h", IniSystems.trappist1, 0.896365F, trappistAu[6]);
			builder.setData(trappisth, ClassBody.SELENA,trappistAu[6],  0.45F, trappistAu[6], 0, 24000L);

		}
	}

	public static void registerPlanets() {
		GalaxyRegistry.registerPlanet(yzcetib);
		GalaxyRegistry.registerPlanet(yzcetic);
		GalaxyRegistry.registerPlanet(yzcetid);

	}

	public static void registerTeleportTypes() {
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiB.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiC.class, new TeleportTypeVenus());
		GalacticraftRegistry.registerTeleportType(WorldProviderYzCetiD.class, new TeleportTypeVenus());
	}

	public static float getGravity(ExPlanet planet) {
		return planet.getGravity();
	}

	public static long getDayLength(ExPlanet planet) {
		return planet.getDayLenght();
	}
}