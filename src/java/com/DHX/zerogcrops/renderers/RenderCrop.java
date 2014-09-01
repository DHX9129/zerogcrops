package com.DHX.zerogcrops.renderers;

import com.DHX.zerogcrops.init.ModBlocks;
import com.DHX.zerogcrops.utility.LogHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public final class RenderCrop extends RenderBlocks implements ISimpleBlockRenderingHandler
    {
        public boolean renderBlockCrops(IBlockAccess blockaccess, Block block, int x, int y, int z)
            {
                Tessellator tessellator = Tessellator.instance;
                tessellator.setBrightness(block.getMixedBrightnessForBlock(blockaccess, x, y, z));
                tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
                this.renderBlockCropsImpl(block,blockaccess.getBlockMetadata(x, y, z), (double)x, (double)((float)y + 0.0625F), (double)z);
                return true;
            }
        @Override
        public void renderBlockCropsImpl(Block block, int meta, double x, double y, double z)
            {
                Tessellator tessellator = Tessellator.instance;
                IIcon iicon = this.getBlockIconFromSideAndMetadata(block, 0, meta);
                if (this.hasOverrideBlockTexture())
                {
                    iicon = this.overrideBlockTexture;
                }

                double a = (double)iicon.getMaxU();
                double b = (double)iicon.getMaxV();
                double c = (double)iicon.getMinU();
                double d = (double)iicon.getMinV();
                double ox = x + 0.5D - 0.25D;
                double ix = x + 0.5D + 0.25D;
                double oz = z + 0.5D - 0.5D;
                double iz = z + 0.5D + 0.5D;
                tessellator.addVertexWithUV(ox, y + 1.0D, oz, a, b);
                tessellator.addVertexWithUV(ox, y + 0.0D, oz, a, d);
                tessellator.addVertexWithUV(ox, y + 0.0D, iz, c, d);
                tessellator.addVertexWithUV(ox, y + 1.0D, iz, c, b);
                tessellator.addVertexWithUV(ox, y + 1.0D, iz, a, b);
                tessellator.addVertexWithUV(ox, y + 0.0D, iz, a, d);
                tessellator.addVertexWithUV(ox, y + 0.0D, oz, c, d);
                tessellator.addVertexWithUV(ox, y + 1.0D, oz, c, b);
                tessellator.addVertexWithUV(ix, y + 1.0D, iz, a, b);
                tessellator.addVertexWithUV(ix, y + 0.0D, iz, a, d);
                tessellator.addVertexWithUV(ix, y + 0.0D, oz, c, d);
                tessellator.addVertexWithUV(ix, y + 1.0D, oz, c, b);
                tessellator.addVertexWithUV(ix, y + 1.0D, oz, a, b);
                tessellator.addVertexWithUV(ix, y + 0.0D, oz, a, d);
                tessellator.addVertexWithUV(ix, y + 0.0D, iz, c, d);
                tessellator.addVertexWithUV(ix, y + 1.0D, iz, c, b);
                ox = x + 0.5D - 0.5D;
                ix = x + 0.5D + 0.5D;
                oz = z + 0.5D - 0.25D;
                iz = z + 0.5D + 0.25D;
                tessellator.addVertexWithUV(ox, y + 1.0D, oz, a, b);
                tessellator.addVertexWithUV(ox, y + 0.0D, oz, a, d);
                tessellator.addVertexWithUV(ix, y + 0.0D, oz, c, d);
                tessellator.addVertexWithUV(ix, y + 1.0D, oz, c, b);
                tessellator.addVertexWithUV(ix, y + 1.0D, oz, a, b);
                tessellator.addVertexWithUV(ix, y + 0.0D, oz, a, d);
                tessellator.addVertexWithUV(ox, y + 0.0D, oz, c, d);
                tessellator.addVertexWithUV(ox, y + 1.0D, oz, c, b);
                tessellator.addVertexWithUV(ix, y + 1.0D, iz, a, b);
                tessellator.addVertexWithUV(ix, y + 0.0D, iz, a, d);
                tessellator.addVertexWithUV(ox, y + 0.0D, iz, c, d);
                tessellator.addVertexWithUV(ox, y + 1.0D, iz, c, b);
                tessellator.addVertexWithUV(ox, y + 1.0D, iz, a, b);
                tessellator.addVertexWithUV(ox, y + 0.0D, iz, a, d);
                tessellator.addVertexWithUV(ix, y + 0.0D, iz, c, d);
                tessellator.addVertexWithUV(ix, y + 1.0D, iz, c, b);
/*        Tessellator tessellator = Tessellator.instance;
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
        iicon = block.getIcon(0, blockAccess.getBlockMetadata( posX, posY, posZ));
        u = iicon.getMinU();
        v = iicon.getMinV();
        U = iicon.getMaxU();
        V = iicon.getMaxV();
        tessellator.addVertexWithUV(0f, 0f, 0.75f, u,v);
        tessellator.addVertexWithUV(1.0f, 0f, 0.75f, U,v);
        tessellator.addVertexWithUV(1.0f, 1.0f, 0.75f, U,V);
        tessellator.addVertexWithUV(0f, 1.0f, 0.75f, u,V);
        //double-sided
        tessellator.addVertexWithUV(0f, 1.0f, 0.75f, u,V);
        tessellator.addVertexWithUV(1.0f, 1.0f, 0.75f, U,V);
        tessellator.addVertexWithUV(1.0f, 0f, 0.75f, U,v);
        tessellator.addVertexWithUV(0f, 0f, 0.75f, u,v);
        // western face
        iicon = block.getIcon(1, blockAccess.getBlockMetadata( posX, posY, posZ));
        u = iicon.getMinU();
        v = iicon.getMinV();
        U = iicon.getMaxU();
        V = iicon.getMaxV();
        tessellator.addVertexWithUV(0.25f, 0f, 1.0f, U, v);
        tessellator.addVertexWithUV(0.25f, 1.0f, 1.0f, U, V);
        tessellator.addVertexWithUV(0.25f, 1.0f, 0f, u, V);
        tessellator.addVertexWithUV(0.25f, 0f, 0f, u, v);
        // double-sided
        tessellator.addVertexWithUV(0.25f, 0f, 0f, u, v);
        tessellator.addVertexWithUV(0.25f, 1.0f, 0f, u, V);
        tessellator.addVertexWithUV(0.25f, 1.0f, 1.0f, U, V);
        tessellator.addVertexWithUV(0.25f, 0f, 1.0f, U, v);
        // northern face
        iicon = block.getIcon(2, blockAccess.getBlockMetadata( posX, posY, posZ));
        u = iicon.getMinU();
        v = iicon.getMinV();
        U = iicon.getMaxU();
        V = iicon.getMaxV();
        tessellator.addVertexWithUV(0f, 0f, 0.25f, u,v);
        tessellator.addVertexWithUV(1.0f, 0f, 0.25f, U,v);
        tessellator.addVertexWithUV(1.0f, 1.0f, 0.25f, U,V);
        tessellator.addVertexWithUV(0f, 1.0f, 0.25f, u,V);
        //double-sided
        tessellator.addVertexWithUV(0f, 1.0f, 0.25f, u,V);
        tessellator.addVertexWithUV(1.0f, 1.0f, 0.25f, U,V);
        tessellator.addVertexWithUV(1.0f, 0f, 0.25f, U,v);
        tessellator.addVertexWithUV(0f, 0f, 0.25f, u,v);
        // eastern face
        iicon = block.getIcon(3, blockAccess.getBlockMetadata( posX, posY, posZ));
        u = iicon.getMinU();
        v = iicon.getMinV();
        U = iicon.getMaxU();
        V = iicon.getMaxV();
        tessellator.addVertexWithUV(0.75f, 0f, 0f, U, v);
        tessellator.addVertexWithUV(0.75f, 1.0f, 0f, U, V);
        tessellator.addVertexWithUV(0.75f, 1.0f, 1.0f, u, V);
        tessellator.addVertexWithUV(0.75f, 0f, 1.0f, u, v);
        // double-sided
        tessellator.addVertexWithUV(0.75f, 0f, 1.0f, u, v);
        tessellator.addVertexWithUV(0.75f, 1.0f, 1.0f, u, V);
        tessellator.addVertexWithUV(0.75f, 1.0f, 0f, U, V);
        tessellator.addVertexWithUV(0.75f, 0f, 0f, U, v);
        // end drawing*/
        //tessellator.addTranslation(-posX, -posY, -posZ);
        //return false;
    }

        @Override
        public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

        }

        @Override
        public boolean renderWorldBlock(IBlockAccess blockaccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
        {
            this.renderBlockCrops(blockaccess, block, x, y, z);

            return false;
        }

        public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return ModRenderers.rendererCrop;
    }
}
