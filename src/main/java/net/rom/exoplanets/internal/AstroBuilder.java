/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal;

import javax.annotation.Nullable;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.BodiesHelper;
import asmodeuscore.core.astronomy.SpaceData;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import asmodeuscore.core.prefab.celestialbody.SpacePort;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody.ScalableDistance;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOverworldOrbit;
import micdoodle8.mods.galacticraft.core.world.gen.BiomeOrbit;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.rom.api.space.ExoRegistry;
import net.rom.api.space.RelayStation;
import net.rom.exoplanets.Assets;
import net.rom.exoplanets.conf.SConfigCore;
import net.rom.exoplanets.internal.world.planet.ExoPlanet;
import net.rom.exoplanets.internal.world.star.ExoStar;

// TODO: Auto-generated Javadoc
/**
 * The Class AstroBuilder.
 */
public class AstroBuilder {

	public static final boolean REALISM = SConfigCore.enableRealism;

	/** The modid. */
	private String modid;

	/**
	 * Instantiates a new astro builder.
	 */
	public AstroBuilder() {
		super();
	}

	/**
	 * Instantiates a new astro builder.
	 *
	 * @param modid the modid
	 */
	public AstroBuilder(String modid) {
		super();
		this.setModid(modid);
	}

	/**
	 * Gets the modid.
	 *
	 * @return the modid
	 */
	public String getModid() {
		return modid;
	}

	/**
	 * Sets the modid.
	 *
	 * @param modid set modid
	 */
	public void setModid(String modid) {
		this.modid = modid;
	}

	/**
	 * Builds the exo star.
	 *
	 * @param starName the star name
	 * @param temp     the temp
	 * @param mass     the mass
	 * @param radius   the radius
	 * @return the exo star
	 */
	public ExoStar buildExoStar(String starName, int temp, double mass, double radius) {
		ExoStar star = new ExoStar(starName);
		star.setStarName(starName);
		star.setSurfaceTemp(temp);
		star.setStarMass(mass);
		star.setStarRadius(radius);
		star.setSpectralClass();
		return star;
	}

	/**
	 * Builds the solar system.
	 *
	 * @param name    the name
	 * @param galaxy  the galaxy
	 * @param pos     the pos
	 * @param exoStar the exo star
	 * @return the solar system
	 */
	public SolarSystem buildSolarSystem(String name, Vector3 pos, ExoStar exoStar) {
		SolarSystem body = new SolarSystem(name, "milky_way");
		body.setMapPosition(new Vector3(pos));
		exoStar.setParentSolarSystem(body);
		body.setMainStar(exoStar);
		exoStar.setBodyIcon(Assets.getCelestialTexture(exoStar.getName()));

		return body;
	}

	/**
	 * Builds the exo planet.
	 *
	 * @param system   the system
	 * @param name     the name
	 * @param provider the provider
	 * @param dimID    the dim ID
	 * @param tier     the tier
	 * @param phase    the phase
	 * @return the exo planet
	 */
	public ExoPlanet buildExoPlanet(SolarSystem system, String name, Class<? extends WorldProvider> provider, int dimID, int tier, float phase) {
		ExoPlanet body = (ExoPlanet) new ExoPlanet(name).setParentSolarSystem(system);
		body.setPhaseShift(phase);
		body.setRingColorRGB(0.1F, 0.9F, 2.6F);
		body.setRelativeSize(1.0F);
		body.setBodyIcon(Assets.getCelestialTexture(name));

		if (provider != null) {
			body.setTierRequired(tier);
			body.setDimensionInfo(dimID, provider);
		}
		body.addChecklistKeys("equipOxygenSuit");
		return body;
	}

	/**
	 * Sets the biomes.
	 *
	 * @param body   the body
	 * @param biomes the biomes
	 */
	public void setBiomes(CelestialBody body, Biome... biomes) {
		body.setBiomeInfo(biomes);
	}

	/**
	 * Sets the atmos.
	 *
	 * @param body   the body
	 * @param gasses the gasses
	 */
	public void setAtmos(CelestialBody body, EnumAtmosphericGas... gasses) {
		((ExoPlanet) body).setAtmosGasses(gasses);
	}

	/**
	 * Sets the data.
	 *
	 * @param body     the body
	 * @param clazz    the clazz
	 * @param distance the distance
	 * @param gravity  the gravity
	 * @param orbit    the orbit
	 * @param pressure the pressure
	 * @param day      the day
	 */
	public void setData(CelestialBody body, ClassBody clazz, float distance, float gravity, float orbit, int pressure, long day) {
		((ExPlanet) body).setClassPlanet(clazz);
		body.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
		body.setRelativeOrbitTime(orbit);
		body.setRingColorRGB(0.0F, 0.4F, 0.9F);
		((ExoPlanet) body).setPlanetGravity(gravity);
		((ExPlanet) body).setAtmosphericPressure(pressure);
		BodiesHelper.setPlanetData(body, 0, day, BodiesHelper.calculateGravity(gravity), false);

	}

	/**
	 * Sets the exo data.
	 *
	 * @param body   the body
	 * @param temp   the temp
	 * @param mass   the mass
	 * @param radius the radius
	 */
	public void setExoData(CelestialBody body, float temp, float mass, float radius) {
		((ExoPlanet) body).setPlanetTemp(temp);
		((ExoPlanet) body).setPlanetMass(mass);
		((ExoPlanet) body).setPlanetRadius(radius);
	}

	/**
	 * Sets the normal orbit.
	 *
	 * @param body the new normal orbit
	 */
	public void setNormalOrbit(CelestialBody body) {
		BodiesHelper.setOrbitData(body, body.getPhaseShift(), 1.0f, body.getRelativeOrbitTime());

	}

	/**
	 * Sets the orbit.
	 *
	 * @param body          the body
	 * @param eccentricityX the eccentricity X
	 * @param eccentricityY the eccentricity Y
	 * @param orbitOffsetX  the orbit offset X
	 * @param orbitOffsetY  the orbit offset Y
	 */
	public void setOrbit(CelestialBody body, float eccentricityX, float eccentricityY, float orbitOffsetX, float orbitOffsetY) {
		((ExPlanet) body).setOrbitEccentricity(eccentricityY, orbitOffsetX);
		((ExPlanet) body).setOrbitOffset(orbitOffsetX, orbitOffsetY);
		BodiesHelper.setOrbitData(body, body.getPhaseShift(), 1.0f, body.getRelativeOrbitTime(), eccentricityX, eccentricityY, orbitOffsetX, orbitOffsetY);

	}

	/**
	 * builds SpaceStation.
	 *
	 * @param parent             the parent
	 * @param color              the color
	 * @param provider           the provider
	 * @param dimID              the dim ID
	 * @param dimIDStatic        the dim ID static
	 * @param phase              the phase
	 * @param size               the size
	 * @param distancefromcenter the distancefromcenter
	 * @param relativetime       the relativetime
	 * @param customStationIcon  the custom station icon
	 * @param bodyIcon           the body icon
	 * @return the satellite
	 */
	public Satellite buildSpaceStation(Planet parent, String color, Class<? extends WorldProvider> provider, int dimID, int dimIDStatic, float phase, float size,
			float distancefromcenter, float relativetime, boolean customStationIcon, @Nullable String bodyIcon) {
		Satellite body = new Satellite("spacestation." + parent.getUnlocalizedName().replace("planet.", ""));
		body.setParentBody(parent);
		body.setRelativeOrbitTime(relativetime);
		body.setPhaseShift(phase);
		body.setRelativeSize(size);
		if (customStationIcon) {
			body.setBodyIcon(new ResourceLocation(getModid(), "textures/celestialbodies/spacestations/" + bodyIcon + ".png"));
		} else {
			body.setBodyIcon(new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/space_station.png"));
		}
		if (provider != null) {
			body.setTierRequired(parent.getTierRequirement());
			body.setDimensionInfo(dimID, dimIDStatic, provider);
			body.setBiomeInfo(new Biome[] { BiomeOrbit.space });
		}
		return body;
	}

	/**
	 * Builds unreachable planet.
	 *
	 * @param planetName  the planet name
	 * @param solarSystem the solar system
	 * @param randomPhase the random phase
	 * @param au          the au
	 * @return the planet
	 */
	public ExoPlanet buildSpecialUnreachable(String planetName, SolarSystem solarSystem, float randomPhase, float au) {
		ExoPlanet unreachable = (ExoPlanet) new ExoPlanet(planetName).setParentSolarSystem(solarSystem);
		unreachable.setDistanceFromCenter(au);
		unreachable.setPhaseShift(randomPhase);
		unreachable.setRelativeSize(1.0F);
		GalaxyRegistry.registerPlanet(unreachable);
		return unreachable;
	}

	/**
	 * Builds unreachable planet.
	 *
	 * @param planetName  the planet name
	 * @param solarSystem the solar system
	 * @param randomPhase the random phase
	 * @param au          the au
	 * @return the planet
	 */
	public ExoPlanet buildUnreachablePlanet(String planetName, SolarSystem solarSystem, float randomPhase) {
		ExoPlanet unreachable = (ExoPlanet) new ExoPlanet(planetName).setParentSolarSystem(solarSystem);
		unreachable.setBodyIcon(Assets.getCelestialTexture(planetName));
		unreachable.setPhaseShift(randomPhase);
		unreachable.setRelativeSize(1.0F);
		unreachable.setRingColorRGB(0.8F, 0.0F, 0.0F);
		GalaxyRegistry.registerPlanet(unreachable);
		return unreachable;
	}
	
	public RelayStation buildRelayStation() {
		RelayStation unreachable = new RelayStation("station01").setParentPlanet(GalacticraftCore.planetOverworld);
		unreachable.setBodyIcon(Assets.getCelestialTexture("orbitalplatform"));
		unreachable.setPhaseShift(AstronomicalConstants.TWO_PI_F);
		unreachable.setRelativeOrbitTime(GalacticraftCore.moonMoon.getRelativeOrbitTime() / 2);
		unreachable.setRelativeSize(1.0F);
		unreachable.setRingColorRGB(0.8F, 0.0F, 0.0F);
		float distance = GalacticraftCore.moonMoon.getRelativeDistanceFromCenter().scaledDistance / 2;
		float distance1 = GalacticraftCore.moonMoon.getRelativeDistanceFromCenter().unScaledDistance / 2;
		unreachable.setRelativeDistanceFromCenter(new ScalableDistance(distance1, distance));
		ExoRegistry.registerRelayStation(unreachable);
		return unreachable;
	}

	/**
	 * Register solar system.
	 *
	 * @param solarSystem the solar system
	 */
	public void registerSolarSystem(SolarSystem solarSystem) {
		GalaxyRegistry.registerSolarSystem(solarSystem);
	}

	/**
	 * Register planet.
	 *
	 * @param planet the planet
	 */
	public void registerPlanet(Planet planet) {
		GalaxyRegistry.registerPlanet(planet);
	}

	/**
	 * Register moon.
	 *
	 * @param moon the moon
	 */
	public void registerMoon(Moon moon) {
		GalaxyRegistry.registerMoon(moon);
	}

	/**
	 * Register teleport type.
	 *
	 * @param clazz the clazz
	 * @param type  the type
	 */
	public void registerTeleportType(Class<? extends WorldProvider> clazz, ITeleportType type) {
		GalacticraftRegistry.registerTeleportType(clazz, type);
	}

	/**
	 * Register rocket gui.
	 *
	 * @param clazz    the clazz
	 * @param resource the resource
	 */
	public void registerRocketGui(Class<? extends WorldProvider> clazz, String resource) {
		GalacticraftRegistry.registerRocketGui(clazz, new ResourceLocation(getModid() + ":textures/gui/rocket/" + resource + ".png"));
	}
}
