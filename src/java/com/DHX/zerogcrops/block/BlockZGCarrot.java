package com.DHX.zerogcrops.block;

import com.DHX.zerogcrops.init.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockZGCarrot extends BlockZGCrops {
    @SideOnly(Side.CLIENT)
    private IIcon[] iconarray;

    /**
     * Gets the block's texture. Args: side, meta
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta < 7) {
            if (meta == 6) {
                meta = 5;
            }

            return this.iconarray[meta >> 1];
        } else {
            return this.iconarray[3];
        }
    }

    protected Item seeditem() {
        return ModItems.zgcarrot;
    }
    protected Item cropitem() {
        return ModItems.zgcarrot;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.iconarray = new IIcon[4];

        for (int i = 0; i < this.iconarray.length; ++i) {
            this.iconarray[i] = p_149651_1_.registerIcon("minecraft:"+ this.getTextureName() + "_stage_" + i);
        }
    }
}