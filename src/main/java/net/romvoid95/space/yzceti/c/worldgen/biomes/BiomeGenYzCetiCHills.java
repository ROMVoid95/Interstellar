/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.romvoid95.space.yzceti.c.worldgen.biomes;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.common.BiomeDictionary;
import net.romvoid95.space.astrogeneration.biome.BiomeData;
import net.romvoid95.space.yzceti.YzCetiBlocks;
import net.romvoid95.space.yzceti.c.worldgen.YzCetiCBiomes;

public class BiomeGenYzCetiCHills extends YzCetiCBiomes {

	public BiomeGenYzCetiCHills(String name, float height, float variation) {
		super(new BiomeData.BiomeDataBuilder()
				.biomeName(name)
				.rainfall(0F)
				.rainEnabled(false)
				.baseHeight(height)
				.heightVariation(variation));
		this.topBlock = YzCetiBlocks.C.YZC_METAMORPHIC.getDefaultState();
		this.fillerBlock = YzCetiBlocks.C.YZC_IGNEOUS.getDefaultState();
	}
	
	@Override
	public void registerTypes(Biome b) {
		BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.DEAD,
				BiomeDictionary.Type.DRY);
	}
}
