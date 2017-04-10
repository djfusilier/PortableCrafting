package com.gr8pefish.portablecrafting.items.craftingItems;

import com.gr8pefish.portablecrafting.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemSubCrafting extends ItemBase {

    public String[] names = {"crafting_core"};

    public ItemSubCrafting() {
        super("subcrafting.");
        setHasSubtypes(true); //could be removed, keeping it here for expandability's sake
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item id, CreativeTabs creativeTab, NonNullList<ItemStack> list) {
        for (int i = 0; i < names.length; i++)
            list.add(new ItemStack(id, 1, i));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + names[stack.getItemDamage()];
    }
}
