package com.DHX.zerogcrops.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public final class RenderCrop extends RenderBlocks implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        // nothing here
        return;
    }

    public boolean renderWorldBlock(IBlockAccess blockAccess, int posX, int posY, int posZ, Block block, int modelId, RenderBlocks renderer) {

        Tessellator tessellator = Tessellator.instance;
        IIcon iicon;
        float u;
        float v;
        float U;
        float V;
        // start drawing
        tessellator.addTranslation(posX, posY, posZ);
        // No idea what this is for but it fixes messed up color tint
        tessellator.setColorOpaque_F(1F, 1F, 1F);
        tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess, posX, posY, posZ));
        // southern face (starting bottom-right corner, going anti-clockwise)
        iicon = block.getIcon(0, 0);
        u = iicon.getMinU();
        v = iicon.getMinV();
        U = iicon.getMaxU();
        V = iicon.getMaxV();
        tessellator.addVertexWithUV(0, 0, 0.75, u,v);
        tessellator.addVertexWithUV(1, 0, 0.75, U,v);
        tessellator.addVertexWithUV(1, 1, 0.75, U,V);
        tessellator.addVertexWithUV(0, 1, 0.75, u,V);
        //double-sided
        tessellator.addVertexWithUV(0, 1, 0.75, u,V);
        tessellator.addVertexWithUV(1, 1, 0.75, U,V);
        tessellator.addVertexWithUV(1, 0, 0.75, U,v);
        tessellator.addVertexWithUV(0, 0, 0.75, u,v);
        // western face
        iicon = block.getIcon(1, 0);
        u = iicon.getMinU();
        v = iicon.getMinV();
        U = iicon.getMaxU();
        V = iicon.getMaxV();
        tessellator.addVertexWithUV(0.25, 0, 1, U, v);
        tessellator.addVertexWithUV(0.25, 1, 1, U, V);
        tessellator.addVertexWithUV(0.25, 1, 0, u, V);
        tessellator.addVertexWithUV(0.25, 0, 0, u, v);
        // double-sided
        tessellator.addVertexWithUV(0.25, 0, 0, u, v);
        tessellator.addVertexWithUV(0.25, 1, 0, u, V);
        tessellator.addVertexWithUV(0.25, 1, 1, U, V);
        tessellator.addVertexWithUV(0.25, 0, 1, U, v);
        // northern face
        iicon = block.getIcon(2, 0);
        u = iicon.getMinU();
        v = iicon.getMinV();
        U = iicon.getMaxU();
        V = iicon.getMaxV();
        tessellator.addVertexWithUV(0, 0, 0.25, u,v);
        tessellator.addVertexWithUV(1, 0, 0.25, U,v);
        tessellator.addVertexWithUV(1, 1, 0.25, U,V);
        tessellator.addVertexWithUV(0, 1, 0.25, u,V);
        //double-sided
        tessellator.addVertexWithUV(0, 1, 0.25, u,V);
        tessellator.addVertexWithUV(1, 1, 0.25, U,V);
        tessellator.addVertexWithUV(1, 0, 0.25, U,v);
        tessellator.addVertexWithUV(0, 0, 0.25, u,v);
        // eastern face
        iicon = block.getIcon(3, 0);
        u = iicon.getMinU();
        v = iicon.getMinV();
        U = iicon.getMaxU();
        V = iicon.getMaxV();
        tessellator.addVertexWithUV(0.75, 0, 0, U, v);
        tessellator.addVertexWithUV(0.75, 1, 0, U, V);
        tessellator.addVertexWithUV(0.75, 1, 1, u, V);
        tessellator.addVertexWithUV(0.75, 0, 1, u, v);
        // double-sided
        tessellator.addVertexWithUV(0.75, 0, 1, u, v);
        tessellator.addVertexWithUV(0.75, 1, 1, u, V);
        tessellator.addVertexWithUV(0.75, 1, 0, U, V);
        tessellator.addVertexWithUV(0.75, 0, 0, U, v);
        // end drawing
        tessellator.addTranslation(-posX, -posY, -posZ);
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return ModRenderers.rendererCrop;
    }
}
