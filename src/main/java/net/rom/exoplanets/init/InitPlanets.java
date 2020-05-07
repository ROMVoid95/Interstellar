package net.rom.exoplanets.init;

import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_b;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_c;
import static net.rom.exoplanets.conf.SConfigDimensionID.id_yz_d;
import static net.rom.exoplanets.conf.SConfigSystems.yzceti_tier;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import net.minecraft.util.ResourceLocation;
import net.rom.api.stellar.AstroBuilder;
import net.rom.api.stellar.impl.planet.ExoPlanet;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.astronomy.yzcetisystem.b.WorldProviderYzCetiB;
import net.rom.exoplanets.astronomy.yzcetisystem.c.WorldProviderYzCetiC;
import net.rom.exoplanets.astronomy.yzcetisystem.d.WorldProviderYzCetiD;
import net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.YzCetiDBiomes;
import net.rom.exoplanets.conf.SConfigSystems;
import net.rom.exoplanets.util.ModSupport;

public class InitPlanets {

    public static Float[] yzCetiAu = { 0.3F, 0.4f, 0.6f };

    public static ExoPlanet yzcetib;
    public static ExoPlanet yzcetic;
    public static ExoPlanet yzcetid;

    // Wolf 1061
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
        InitPlanets.buildUnreachable();
        InitPlanets.registerTeleportTypes();
    }

    public static void initPlanets() {
        yzcetib = builder.buildExoPlanet(InitSolarSystems.yzCeti, "yz_ceti_b", WorldProviderYzCetiB.class, id_yz_b, yzceti_tier, 4.50F);
        yzcetic = builder.buildExoPlanet(InitSolarSystems.yzCeti, "yz_ceti_c", WorldProviderYzCetiC.class, id_yz_c, yzceti_tier, 1.5F);
        yzcetid = builder.buildExoPlanet(GalacticraftCore.solarSystemSol, "yz_ceti_d", WorldProviderYzCetiD.class, id_yz_d, yzceti_tier, 3.6F);

        asmodeusData();

    }

    public static void registerPlanetData() {
        yzcetib.setPlanetGravity(0.35F);
        yzcetib.setDistanceFromCenter(yzCetiAu[0]);
        yzcetib.setRelativeOrbitTime(yzCetiAu[0] + 0.5F);
        yzcetib.setDayLength(0.93F);
        yzcetib.setPlanetTemp(-54.5F);
        yzcetib.setAtmosGasses(EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN,
                EnumAtmosphericGas.ARGON);
        yzcetib.setBiomeInfo(ExoplanetBiomes.CETIB_BASE, ExoplanetBiomes.CETIB_DIRTY);
        yzcetib.addChecklistKeys("equipOxygenSuit");

        yzcetib.setPlanetGravity(0.42F);
        yzcetic.setDistanceFromCenter(yzCetiAu[1]);
        yzcetic.setRelativeOrbitTime(yzCetiAu[1] + 0.5F);
        yzcetic.setDayLength(1.0F);
        yzcetic.setPlanetTemp(-54.5F);
        yzcetic.setAtmosGasses(EnumAtmosphericGas.OXYGEN, EnumAtmosphericGas.WATER, EnumAtmosphericGas.NITROGEN,
                EnumAtmosphericGas.ARGON);
        yzcetic.setBiomeInfo(ExoplanetBiomes.CETIC_BASE, ExoplanetBiomes.CETIC_UNKNWON);
        yzcetic.addChecklistKeys("equipOxygenSuit");

        yzcetid.setPlanetGravity(0.03F);
        yzcetid.setDistanceFromCenter(yzCetiAu[2]);
        yzcetid.setRelativeOrbitTime(yzCetiAu[2] + 0.5F);
        yzcetid.setDayLength(15.0F);
        yzcetid.setPlanetTemp(-150.5F);
        yzcetid.setAtmosGasses(EnumAtmosphericGas.NITROGEN, EnumAtmosphericGas.ARGON);
        yzcetid.setBiomeInfo(YzCetiDBiomes.yz_ceti_d, YzCetiDBiomes.yz_ceti_d_mantle);
        yzcetid.addChecklistKeys("equipOxygenSuit");
    }

    public static void asmodeusData() {
        // Yz Ceti
        BodiesData data = new BodiesData(ClassBody.DESERT, BodiesHelper.calculateGravity(getGravity(yzcetib)), 0, getDayLength(yzcetib), false);
        BodiesHelper.registerBody(yzcetib, data, SConfigSystems.disable_yzceti_system);
        BodiesHelper.setOrbitData(yzcetib, (float)Math.PI / 4, 1.0f, 15F, 10.3F, 5.0F, 5F, 6.0F);


        data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(getGravity(yzcetic)), 0, getDayLength(yzcetic), false);
        BodiesHelper.registerBody(yzcetic, data, false);

        data = new BodiesData(ClassBody.SELENA, BodiesHelper.calculateGravity(getGravity(yzcetid)), 0, getDayLength(yzcetid), false);
        BodiesHelper.setOrbitData(yzcetid, (float)Math.PI / 4, 1.0F, 32F, 10.3F, 10.0F, 5F, 0.0F);
        BodiesHelper.registerBodyData(yzcetid, data);

        if (!SConfigSystems.hideUnfinishedSystems) {

            // Wolf 1061
            data = new BodiesData(ClassBody.SELENA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(wolf1061b, data, false);
            data = new BodiesData(ClassBody.SELENA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(wolf1061c, data, false);
            data = new BodiesData(ClassBody.GASGIANT, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(wolf1061d, data, false);

           

            // HD 219134
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(hd219134b, data, false);
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(hd219134c, data, false);
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(hd219134d, data, false);
            data = new BodiesData(ClassBody.SELENA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(hd219134f, data, false);
            data = new BodiesData(ClassBody.GASGIANT, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(hd219134g, data, false);
            data = new BodiesData(ClassBody.GASGIANT, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(hd219134h, data, false);

            // Trappist 1
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(trappistb, data, false);
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(trappistc, data, false);
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(trappistd, data, false);
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(trappiste, data, false);
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(trappistf, data, false);
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(trappistg, data, false);
            data = new BodiesData(ClassBody.TERRA, BodiesHelper.calculateGravity(0.45F), 0, 24000L, false);
            BodiesHelper.registerBody(trappisth, data, false);
            
            
        }
    }

    public static void buildUnreachable() {

        if (!SConfigSystems.hideUnfinishedSystems) {

            // Wolf 1061
            wolf1061b = builder.buildUnreachablePlanet("wolf_1061_b", InitSolarSystems.wolf1061, 2.9495497F, wolfAu[0]);
            wolf1061c = builder.buildUnreachablePlanet("wolf_1061_c", InitSolarSystems.wolf1061, 1.7264397F, wolfAu[1]);
            wolf1061d = builder.buildUnreachablePlanet("wolf_1061_d", InitSolarSystems.wolf1061, 7.132725F, wolfAu[2]);

            // HD 219134
            hd219134b = builder.buildUnreachablePlanet("hd_219134_b", InitSolarSystems.hd219134, 0.292421F, hdAu[0]);
            hd219134c = builder.buildUnreachablePlanet("hd_219134_c", InitSolarSystems.hd219134, 2.9495497F, hdAu[1]);
            hd219134d = builder.buildUnreachablePlanet("hd_219134_d", InitSolarSystems.hd219134, 0.896365F, hdAu[2]);
            hd219134f = builder.buildUnreachablePlanet("hd_219134_f", InitSolarSystems.hd219134, 4.305977F, hdAu[3]);
            hd219134g = builder.buildUnreachablePlanet("hd_219134_g", InitSolarSystems.hd219134, 1.932375F, hdAu[4]);
            hd219134h = builder.buildUnreachablePlanet("hd_219134_h", InitSolarSystems.hd219134, 7.906747F, hdAu[5]);

            // Trappist 1
            trappistb = builder.buildUnreachablePlanet("trappist_b", InitSolarSystems.trappist1, 3.254752F, trappistAu[0]);
            trappistc = builder.buildUnreachablePlanet("trappist_c", InitSolarSystems.trappist1, 0.6451158F, trappistAu[1]);
            trappistd = builder.buildUnreachablePlanet("trappist_d", InitSolarSystems.trappist1, 5.6336107F, trappistAu[2]);
            trappiste = builder.buildUnreachablePlanet("trappist_e", InitSolarSystems.trappist1, 0.6451158F, trappistAu[3]);
            trappistf = builder.buildUnreachablePlanet("trappist_f", InitSolarSystems.trappist1, 5.396859F, trappistAu[4]);
            trappistg = builder.buildUnreachablePlanet("trappist_g", InitSolarSystems.trappist1, 7.957024F, trappistAu[5]);
            trappisth = builder.buildUnreachablePlanet("trappist_h", InitSolarSystems.trappist1, 2.711277F, trappistAu[6]);

        }
    }
    
    private static void registerOrbits(ExoPlanet planet) {

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

    public static float getGravity(ExoPlanet planet) {
        return (float) planet.getGravity();
    }

    public static long getDayLength(ExoPlanet planet) {
        return planet.getDayLength();
    }
}