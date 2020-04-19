//package net.rom.exoplanets.astronomy.yzcetisystem.b;
//
//import org.lwjgl.opengl.GL11;
//
//import asmodeuscore.core.astronomy.sky.SkyProviderBase;
//import micdoodle8.mods.galacticraft.api.vector.Vector3;
//import net.minecraft.client.renderer.BufferBuilder;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.FMLClientHandler;
//import net.rom.exoplanets.Exoplanets;
//
//public class SkyProviderYzCetiB extends SkyProviderBase {
//
//	private static final ResourceLocation yzcetiCTexture = new ResourceLocation(Exoplanets.MODID, "textures/celestialbodies/yz_ceti/yz_ceti_c.png");
//	  
//	@Override
//	protected void rendererSky(Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {
//		GL11.glPopMatrix();
//        GL11.glPushMatrix();
//        
//        GL11.glEnable(GL11.GL_BLEND);
//       // OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ZERO);
//        
//        f10 = 3.5F;
//        GL11.glScalef(0.8F, 0.8F, 0.8F);
//        GL11.glRotatef(this.mc.world.getCelestialAngle(ticks) * 360.0F, 0.0F, 0.0F, 1.0F);	        
//        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);	
//        GL11.glRotatef(40.0F, 1.0F, 0.0F, 0.0F);	
//        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
//        FMLClientHandler.instance().getClient().renderEngine.bindTexture(yzcetiCTexture);
//        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
//        buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
//        buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
//        buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
//        buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
//        tessellator.draw();
//    
//        GL11.glDisable(GL11.GL_BLEND);
//		
//	}
//
//	@Override
//	protected int modeLight() {
//		return 0;
//	}
//
//	@Override
//	protected boolean enableBaseImages() {
//		return false;
//	}
//
//	@Override
//	protected float sunSize() {
//		return 15.0f;
//	}
//
//	@Override
//	protected ResourceLocation sunImage() {
//		return new ResourceLocation(Exoplanets.MODID, "textures/celestialbodies/yz_ceti/yz_ceti_star.png");
//	}
//
//	@Override
//	protected boolean enableStar() {
//		return true;
//	}
//
//	@Override
//	protected Vector3 colorSunAura() {
//		return null;
//	}
//
//	@Override
//	protected Vector3 getAtmosphereColor() {
//		return null;
//	}
//
//
//}
