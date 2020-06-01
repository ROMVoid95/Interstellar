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

package net.rom.exoplanets.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.conf.SConfigCore;
import net.rom.exoplanets.content.block.machine.TileAlloyRefinery;
import net.rom.exoplanets.content.block.machine.container.ContainerAlloyRefinery;

public class GuiAlloyRefinery extends GuiContainer {
    private static final ResourceLocation guiTextures = new ResourceLocation(ExoInfo.MODID, "textures/gui/alloyrefinery.png");
    private IInventory tileAlloyRefinery;

    public GuiAlloyRefinery(InventoryPlayer playerInventory, IInventory smelterInventory) {
        super(new ContainerAlloyRefinery(playerInventory, smelterInventory));
        this.tileAlloyRefinery = smelterInventory;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (TileEntityFurnace.isBurning(this.tileAlloyRefinery)) {
            i1 = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(k + 20, l + 27 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        }

        i1 = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);

        if (SConfigCore.enableDebug)
            drawDebugInfo();
    }

    private int getCookProgressScaled(int pixels) {
        int j = this.tileAlloyRefinery.getField(2);
        int k = this.tileAlloyRefinery.getField(3);
        return k != 0 && j != 0 ? j * pixels / k : 0;
    }

    private int getBurnLeftScaled(int pixels) {
        int currentItemBurnTime = this.tileAlloyRefinery.getField(1);
        if (currentItemBurnTime == 0)
            currentItemBurnTime = 200;
        return this.tileAlloyRefinery.getField(0) * pixels / currentItemBurnTime;
    }

    private void drawDebugInfo() {
        if (!(tileAlloyRefinery instanceof TileAlloyRefinery)) return;

        TileAlloyRefinery tile = (TileAlloyRefinery) tileAlloyRefinery;
        FontRenderer fontRender = mc.fontRenderer;
        int x = 5;
        int y = 5;
        int yIncrement = 10;
        int color = 0xFFFFFF;

        GL11.glPushMatrix();
        float scale = 0.75f;
        GL11.glScalef(scale, scale, 1f);
        for (String str : tile.getDebugLines()) {
            fontRender.drawStringWithShadow(str, x, y, color);
            y += yIncrement;
        }
        GL11.glPopMatrix();
    }
}
