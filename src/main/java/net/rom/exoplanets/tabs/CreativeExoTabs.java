package net.rom.exoplanets.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.rom.exoplanets.init.BlocksRegister;

public class CreativeExoTabs {

	public static final CreativeTabs DECO_CREATIVE_TABS = new ExoTab("decoration", "decoration.png") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(BlocksRegister.com);
		}
		
		@Override
		public boolean hasSearchBar()
		{
			return false;
		}
	};

}
