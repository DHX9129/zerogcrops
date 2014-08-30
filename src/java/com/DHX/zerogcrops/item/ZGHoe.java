package com.DHX.zerogcrops.item;

import com.DHX.zerogcrops.creativetab.CreativeTabZGC;
import com.DHX.zerogcrops.reference.Reference;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ZGHoe extends ItemZGC
  {
    protected Item.ToolMaterial theToolMaterial;
    public ZGHoe(Item.ToolMaterial material)
      {

        this.setUnlocalizedName("zghoe");
        this.theToolMaterial = material;
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        this.setCreativeTab(CreativeTabZGC.ZGC_Tab);
      }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x,
                             int y, int z, int side, float p_77648_8_, float p_77648_9_,
                             float p_77648_10_)
      {
        if(! player.canPlayerEdit(x, y, z, side, itemStack))
          {
            return false;
          } else
          {
            UseHoeEvent event = new UseHoeEvent(player, itemStack, world, x, y, z);
            if(MinecraftForge.EVENT_BUS.post(event))
              {
                return false;
              }

            if(event.getResult() == Event.Result.ALLOW)
              {
                itemStack.damageItem(1, player);
                return true;
              }

            Block block = world.getBlock(x, y, z);

            if(side != 1 && world.getBlock(x, y - 1, z)
                .isAir(world, x, y - 1, z) &&
                (block == Blocks.grass || block == Blocks.dirt))
              {
                Block block1 = GameRegistry.findBlock(Reference.MOD_ID, "blockzgfarmland");
                world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F),
                    (double) ((float) z + 0.5F), block1.stepSound.getStepResourcePath(),
                    (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

                if(world.isRemote)
                  {
                    return true;
                  } else
                  {
                    world.setBlock(x, y, z, block1);
                    itemStack.damageItem(1, player);
                    return true;
                  }
              } else
              {
                return false;
              }
          }
      }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
      {
        itemIcon = iconRegister.registerIcon("minecraft:diamond_hoe");

      }
  }
