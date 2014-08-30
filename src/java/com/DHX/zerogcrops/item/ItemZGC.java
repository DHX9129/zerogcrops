package com.DHX.zerogcrops.item;

import com.DHX.zerogcrops.creativetab.CreativeTabZGC;
import com.DHX.zerogcrops.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemZGC extends Item
  {
    public ItemZGC()
      {
        super();
        this.setCreativeTab(CreativeTabZGC.ZGC_Tab);
      }

    @Override
    public String getUnlocalizedName()
      {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":",
            getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
      }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
      {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":",
            getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
      }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
      {
        itemIcon = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName(this.getUnlocalizedName()));
      }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
      {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
      }
  }
