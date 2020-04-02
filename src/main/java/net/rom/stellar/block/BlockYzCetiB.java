package net.rom.stellar.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.ItemStack;
import net.rom.stellar.tabs.CreativeExoTabs;

public class BlockYzCetiB extends BasicBlock {

	public BlockYzCetiB() {
		super(Material.ROCK);
		setHardness(3.0f);
		setResistance(30.0f);
		setCreativeTab(CreativeExoTabs.TERRAIN);
	}

	public ItemStack getStack(int count) {
		return new ItemStack(this, count);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this);
	}
}