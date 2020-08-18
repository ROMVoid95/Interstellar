package net.rom.exoplanets.astronomy.trappist1.d.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.rom.api.client.Texture;
import net.rom.api.world.CloudProvider;
import net.rom.api.world.IStormProvider;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.astronomy.trappist1.d.WorldProviderTrappist1D;

@EventBusSubscriber
public class CloudProviderTrappist1D extends CloudProvider {

	private static final Texture CLOUDS = new Texture(ExoInfo.MODID, "textures/world/varda-clouds.png");

	private IStormProvider stormProvider;

	@Override
	public float getMaxCloudSpeedDuringStorm () {
		return 32;
	}

	@Override
	public float getMaxNormalCloudSpeed () {
		return 12;
	}

	@Override
	public Texture getCloudTexture () {
		return CLOUDS;
	}

	@Override
	public boolean areCloudsApplicableTo (WorldProvider provider) {
		return provider instanceof WorldProviderTrappist1D;
	}

	@Override
	public double getCloudMovementX (World world, float cloudTicksPrev, float cloudTicks) {
		return -super.getCloudMovementX(world, cloudTicksPrev, cloudTicks);
	}

	@Override
	public void render (float partialTicks, WorldClient world, Minecraft mc) {
		super.render(partialTicks, world, mc);
	}
}
