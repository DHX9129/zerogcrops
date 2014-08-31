package com.DHX.zerogcrops.block;
import com.DHX.zerogcrops.init.ModBlocks;
import com.DHX.zerogcrops.init.ModItems;
import com.DHX.zerogcrops.renderers.ModRenderers;
import com.DHX.zerogcrops.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockZGCrops extends BlockZGBush implements IGrowable
  {
    @SideOnly(Side.CLIENT)
    private IIcon[] iconarray;

    public BlockZGCrops()
      {
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 1.0F, 0.5F - f, 0.5F + f, 0.75F, 0.5F + f);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.disableStats();
      }

    /**
     * is the block grass, dirt or farmland
     */
    @Override
    protected boolean canPlaceBlockOn(Block hostblock)
      {
        return hostblock == ModBlocks.blockZGFarmland;
      }
    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
      {
        if (world.getBlockLightValue(x, y - 1, z) >= 9)
          {
            int l = world.getBlockMetadata(x, y, z);

            if (l < 7)
              {
                float f = this.func_149864_n(world, x, y, z);

                if (random.nextInt((int)(25.0F / f) + 1) == 0)
                  {
                    ++l;
                    world.setBlockMetadataWithNotify(x, y, z, l, 2);
                  }
              }
          }
      }


    public void growthstate(World world, int x, int y, int z)
      {
        int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

        if (l > 7)
          {
            l = 7;
          }

        world.setBlockMetadataWithNotify(x, y, z, l, 2);
      }


    private float func_149864_n(World world, int x, int y, int z)
      {
        float f = 1.0F;
        Block block = world.getBlock(x, y, z - 1);
        Block block1 = world.getBlock(x, y, z + 1);
        Block block2 = world.getBlock(x - 1, y, z);
        Block block3 = world.getBlock(x + 1, y, z);
        Block block4 = world.getBlock(x - 1, y, z - 1);
        Block block5 = world.getBlock(x + 1, y, z - 1);
        Block block6 = world.getBlock(x + 1, y, z + 1);
        Block block7 = world.getBlock(x - 1, y, z + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for (int l = x - 1; l <= x + 1; ++l)
          {
            for (int i1 = z - 1; i1 <= z + 1; ++i1)
              {
                float f1 = 0.0F;

                if (world.getBlock(l, y + 1, i1).canSustainPlant(world, l, y + 1, i1, ForgeDirection.DOWN, this))
                  {
                    f1 = 1.0F;

                    if (world.getBlock(l, y + 1, i1).isFertile(world, l, y + 1, i1))
                      {
                        f1 = 3.0F;
                      }
                  }

                if (l != x || i1 != z)
                  {
                    f1 /= 4.0F;
                  }

                f += f1;
              }
          }

        if (flag2 || flag && flag1)
          {
            f /= 2.0F;
          }

        return f;
      }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
      {
        if (p_149691_2_ < 0 || p_149691_2_ > 7)
          {
            p_149691_2_ = 7;
          }

        return this.iconarray[p_149691_2_];
      }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType()
    {
        return ModRenderers.rendererCrop;
    }


    protected Item seeditem()
      {
        return ModItems.zgseeds;
      }


    protected Item cropitem()
      {
        return Items.wheat;
      }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_)
      {
        super.dropBlockAsItemWithChance(world, x, y, z, p_149690_5_, p_149690_6_, 0);
      }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
      {
        return p_149650_1_ == 7 ? this.cropitem() : this.seeditem();
      }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random)
      {
        return 1;
      }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_)
      {
        return world.getBlockMetadata(x, y, z) != 7;
      }

    @Override
    public boolean func_149852_a(World world, Random random, int x, int y, int z)
      {
        return true;
      }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
      {
        return this.seeditem();
      }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister)
      {
        this.iconarray = new IIcon[8];
        for (int i = 0; i < this.iconarray.length; ++i)
          {
            this.iconarray[i] = iconregister.registerIcon("minecraft:" + this.getTextureName() + "_stage_" + i);
          }
      }

    @Override
    public void func_149853_b(World world, Random random, int x, int y, int z)
      {
        this.growthstate(world, x, y, z);
      }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
      {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (metadata >= 7)
          {
            for (int i = 0; i < 3 + fortune; ++i)
              {
                if (world.rand.nextInt(15) <= metadata)
                  {
                    ret.add(new ItemStack(this.seeditem(), 1, 0));
                  }
              }
          }

        return ret;
      }
  }
