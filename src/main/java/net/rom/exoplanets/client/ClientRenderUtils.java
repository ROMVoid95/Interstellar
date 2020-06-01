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

package net.rom.exoplanets.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class ClientRenderUtils {

	public static void renderTexturedQuad(TextureAtlasSprite texture, EnumFacing face, BlockPos pos, double x1, double y1, double z1, double x2, double y2, double z2,
			int brightness, int color) {
		BufferBuilder renderer = Tessellator.getInstance().getBuffer();
		renderer.begin(7, DefaultVertexFormats.BLOCK);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		putTexturedQuad(renderer, texture, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, face, color, brightness, false);
		Tessellator.getInstance().draw();
	}

	public static void renderTexturedCuboid(TextureAtlasSprite[] textures, BlockPos pos, double x1, double y1, double z1, double x2, double y2, double z2, int brightness,
			int color) {
		BufferBuilder renderer = Tessellator.getInstance().getBuffer();
		renderer.begin(7, DefaultVertexFormats.BLOCK);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		putTexturedQuad(renderer, textures[0], x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.DOWN, color, brightness, false);
		putTexturedQuad(renderer, textures[1], x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.NORTH, color, brightness, false);
		putTexturedQuad(renderer, textures[2], x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.EAST, color, brightness, false);
		putTexturedQuad(renderer, textures[3], x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.SOUTH, color, brightness, false);
		putTexturedQuad(renderer, textures[4], x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.WEST, color, brightness, false);
		putTexturedQuad(renderer, textures[5], x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.UP, color, brightness, false);
		Tessellator.getInstance().draw();
	}

	public static void renderTexturedCuboid(TextureAtlasSprite texture, BlockPos pos, double x1, double y1, double z1, double x2, double y2, double z2, int brightness, int color) {
		BufferBuilder renderer = Tessellator.getInstance().getBuffer();
		renderer.begin(7, DefaultVertexFormats.BLOCK);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		putTexturedQuad(renderer, texture, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.DOWN, color, brightness, false);
		putTexturedQuad(renderer, texture, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.NORTH, color, brightness, false);
		putTexturedQuad(renderer, texture, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.EAST, color, brightness, false);
		putTexturedQuad(renderer, texture, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.SOUTH, color, brightness, false);
		putTexturedQuad(renderer, texture, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.WEST, color, brightness, false);
		putTexturedQuad(renderer, texture, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, EnumFacing.UP, color, brightness, false);
		Tessellator.getInstance().draw();
	}

	public static void drawCube(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, BufferBuilder buffer) {
		drawPlaneNegX(minX, minY, maxY, minZ, maxZ, buffer);
		drawPlanePosX(maxX, minY, maxY, minZ, maxZ, buffer);
		drawPlaneNegY(minY, minX, maxX, minZ, maxZ, buffer);
		drawPlanePosY(maxY, minX, maxX, minZ, maxZ, buffer);
		drawPlaneNegZ(minZ, minX, maxX, minY, maxY, buffer);
		drawPlanePosZ(maxZ, minX, maxX, minY, maxY, buffer);
	}

	public static void drawCube(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float r, float g, float b, float a, BufferBuilder buffer) {
		drawPlaneNegX(minX, minY, maxY, minZ, maxZ, r, g, b, a * 0.9F, buffer);
		drawPlanePosX(maxX, minY, maxY, minZ, maxZ, r, g, b, a * 0.9F, buffer);
		drawPlaneNegY(minY, minX, maxX, minZ, maxZ, r, g, b, a * 0.8F, buffer);
		drawPlanePosY(maxY, minX, maxX, minZ, maxZ, r, g, b, a * 1.1F, buffer);
		drawPlaneNegZ(minZ, minX, maxX, minY, maxY, r, g, b, a, buffer);
		drawPlanePosZ(maxZ, minX, maxX, minY, maxY, r, g, b, a, buffer);
	}

	public static void drawPlaneNegX(double x, double minY, double maxY, double minZ, double maxZ, BufferBuilder buffer) {
		buffer.pos(x, minY, minZ).endVertex();
		buffer.pos(x, minY, maxZ).endVertex();
		buffer.pos(x, maxY, maxZ).endVertex();
		buffer.pos(x, maxY, minZ).endVertex();
	}

	public static void drawPlaneNegX(double x, double minY, double maxY, double minZ, double maxZ, float r, float g, float b, float a, BufferBuilder buffer) {
		buffer.pos(x, minY, minZ).color(r, g, b, a).endVertex();
		buffer.pos(x, minY, maxZ).color(r, g, b, a).endVertex();
		buffer.pos(x, maxY, maxZ).color(r, g, b, a).endVertex();
		buffer.pos(x, maxY, minZ).color(r, g, b, a).endVertex();
	}

	public static void drawPlanePosX(double x, double minY, double maxY, double minZ, double maxZ, BufferBuilder buffer) {
		buffer.pos(x, minY, minZ).endVertex();
		buffer.pos(x, maxY, minZ).endVertex();
		buffer.pos(x, maxY, maxZ).endVertex();
		buffer.pos(x, minY, maxZ).endVertex();
	}

	public static void drawPlanePosX(double x, double minY, double maxY, double minZ, double maxZ, float r, float g, float b, float a, BufferBuilder buffer) {
		buffer.pos(x, minY, minZ).color(r, g, b, a).endVertex();
		buffer.pos(x, maxY, minZ).color(r, g, b, a).endVertex();
		buffer.pos(x, maxY, maxZ).color(r, g, b, a).endVertex();
		buffer.pos(x, minY, maxZ).color(r, g, b, a).endVertex();
	}

	public static void drawPlaneNegY(double y, double minX, double maxX, double minZ, double maxZ, BufferBuilder buffer) {
		buffer.pos(minX, y, minZ).endVertex();
		buffer.pos(maxX, y, minZ).endVertex();
		buffer.pos(maxX, y, maxZ).endVertex();
		buffer.pos(minX, y, maxZ).endVertex();
	}

	public static void drawPlaneNegY(double y, double minX, double maxX, double minZ, double maxZ, float r, float g, float b, float a, BufferBuilder buffer) {
		buffer.pos(minX, y, minZ).color(r, g, b, a).endVertex();
		buffer.pos(maxX, y, minZ).color(r, g, b, a).endVertex();
		buffer.pos(maxX, y, maxZ).color(r, g, b, a).endVertex();
		buffer.pos(minX, y, maxZ).color(r, g, b, a).endVertex();
	}

	public static void drawPlanePosY(double y, double minX, double maxX, double minZ, double maxZ, BufferBuilder buffer) {
		buffer.pos(minX, y, minZ).endVertex();
		buffer.pos(minX, y, maxZ).endVertex();
		buffer.pos(maxX, y, maxZ).endVertex();
		buffer.pos(maxX, y, minZ).endVertex();
	}

	public static void drawPlanePosY(double y, double minX, double maxX, double minZ, double maxZ, float r, float g, float b, float a, BufferBuilder buffer) {
		buffer.pos(minX, y, minZ).color(r, g, b, a).endVertex();
		buffer.pos(minX, y, maxZ).color(r, g, b, a).endVertex();
		buffer.pos(maxX, y, maxZ).color(r, g, b, a).endVertex();
		buffer.pos(maxX, y, minZ).color(r, g, b, a).endVertex();
	}

	public static void drawPlaneNegZ(double z, double minX, double maxX, double minY, double maxY, BufferBuilder buffer) {
		buffer.pos(minX, minY, z).endVertex();
		buffer.pos(minX, maxY, z).endVertex();
		buffer.pos(maxX, maxY, z).endVertex();
		buffer.pos(maxX, minY, z).endVertex();
	}

	public static void drawPlaneNegZ(double z, double minX, double maxX, double minY, double maxY, float r, float g, float b, float a, BufferBuilder buffer) {
		buffer.pos(minX, minY, z).color(r, g, b, a).endVertex();
		buffer.pos(minX, maxY, z).color(r, g, b, a).endVertex();
		buffer.pos(maxX, maxY, z).color(r, g, b, a).endVertex();
		buffer.pos(maxX, minY, z).color(r, g, b, a).endVertex();
	}

	public static void drawPlanePosZ(double z, double minX, double maxX, double minY, double maxY, BufferBuilder buffer) {
		buffer.pos(minX, minY, z).endVertex();
		buffer.pos(maxX, minY, z).endVertex();
		buffer.pos(maxX, maxY, z).endVertex();
		buffer.pos(minX, maxY, z).endVertex();
	}

	public static void drawPlanePosZ(double z, double minX, double maxX, double minY, double maxY, float r, float g, float b, float a, BufferBuilder buffer) {
		buffer.pos(minX, minY, z).color(r, g, b, a).endVertex();
		buffer.pos(maxX, minY, z).color(r, g, b, a).endVertex();
		buffer.pos(maxX, maxY, z).color(r, g, b, a).endVertex();
		buffer.pos(minX, maxY, z).color(r, g, b, a).endVertex();
	}

	public static void putTexturedQuad(BufferBuilder renderer, TextureAtlasSprite sprite, double x, double y, double z, double w, double h, double d, EnumFacing face, int color,
			int brightness, boolean flowing) {
		int l1 = brightness >> 16 & '\uffff';
		int l2 = brightness & '\uffff';
		int a = color >> 24 & 255;
		int r = color >> 16 & 255;
		int g = color >> 8 & 255;
		int b = color & 255;
		putQuad(renderer, sprite, x, y, z, w, h, d, face, r, g, b, a, l1, l2, flowing);
	}

	public static void putQuad(BufferBuilder renderer, TextureAtlasSprite sprite, double x, double y, double z, double w, double h, double d, EnumFacing face, int r, int g,
			int b, int a, int light1, int light2, boolean flowing) {
		if (sprite != null) {
			double size = 16.0D;
			if (flowing) {
				size = 8.0D;
			}

			double x2 = x + w;
			double y2 = y + h;
			double z2 = z + d;
			double xt1 = x % 1.0D;

			double xt2;
			for (xt2 = xt1 + w; xt2 > 1.0D; --xt2) {
			}

			double yt1 = y % 1.0D;

			double yt2;
			for (yt2 = yt1 + h; yt2 > 1.0D; --yt2) {
			}

			double zt1 = z % 1.0D;

			double zt2;
			for (zt2 = zt1 + d; zt2 > 1.0D; --zt2) {
			}

			if (flowing) {
				double tmp = 1.0D - yt1;
				yt1 = 1.0D - yt2;
				yt2 = tmp;
			}

			double minU;
			double maxU;
			double minV;
			double maxV;
			switch (face) {
				case DOWN:
				case UP:
					minU = (double) sprite.getInterpolatedU(xt1 * size);
					maxU = (double) sprite.getInterpolatedU(xt2 * size);
					minV = (double) sprite.getInterpolatedV(zt1 * size);
					maxV = (double) sprite.getInterpolatedV(zt2 * size);
					break;
				case NORTH:
				case SOUTH:
					minU = (double) sprite.getInterpolatedU(xt2 * size);
					maxU = (double) sprite.getInterpolatedU(xt1 * size);
					minV = (double) sprite.getInterpolatedV(yt1 * size);
					maxV = (double) sprite.getInterpolatedV(yt2 * size);
					break;
				case WEST:
				case EAST:
					minU = (double) sprite.getInterpolatedU(zt2 * size);
					maxU = (double) sprite.getInterpolatedU(zt1 * size);
					minV = (double) sprite.getInterpolatedV(yt1 * size);
					maxV = (double) sprite.getInterpolatedV(yt2 * size);
					break;
				default:
					minU = (double) sprite.getMinU();
					maxU = (double) sprite.getMaxU();
					minV = (double) sprite.getMinV();
					maxV = (double) sprite.getMaxV();
			}

			switch (face) {
				case DOWN:
					renderer.pos(x, y, z).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y, z).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
					renderer.pos(x, y, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
					break;
				case UP:
					renderer.pos(x, y2, z).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
					renderer.pos(x, y2, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y2, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y2, z).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
					break;
				case NORTH:
					renderer.pos(x, y, z).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
					renderer.pos(x, y2, z).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y2, z).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y, z).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
					break;
				case SOUTH:
					renderer.pos(x, y, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y2, z2).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
					renderer.pos(x, y2, z2).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
					break;
				case WEST:
					renderer.pos(x, y, z).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
					renderer.pos(x, y, z2).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
					renderer.pos(x, y2, z2).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
					renderer.pos(x, y2, z).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
					break;
				case EAST:
					renderer.pos(x2, y, z).color(r, g, b, a).tex(minU, maxV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y2, z).color(r, g, b, a).tex(minU, minV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y2, z2).color(r, g, b, a).tex(maxU, minV).lightmap(light1, light2).endVertex();
					renderer.pos(x2, y, z2).color(r, g, b, a).tex(maxU, maxV).lightmap(light1, light2).endVertex();
			}

		}
	}
}
