package com.DHX.zerogcrops.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ZGSeedFood extends ItemFoodZGC implements IPlantable
{
    private Block block;
    /** Block ID of the soil this seed food should be planted on. */
    private Block soilId;

    public ZGSeedFood(int p_i45351_1_, float p_i45351_2_, Block p_i45351_3_, Block p_i45351_4_)
    {
        super(p_i45351_1_, p_i45351_2_, false);
        this.block = p_i45351_3_;
        this.soilId = p_i45351_4_;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_7_ != 0)
        {
            return false;
        }
        else if (player.canPlayerEdit(x, y, z, p_77648_7_, itemstack) && player.canPlayerEdit(x, y - 1, z, p_77648_7_, itemstack))
        {
            if (world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.DOWN, this) && world.isAirBlock(x, y - 1, z))
            {
                world.setBlock(x, y - 1, z, this.block);
                --itemstack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Crop;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return block;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return 0;
    }
}
