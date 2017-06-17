package com.gr8pefish.portablecrafting.items.craftingBenches;

import com.gr8pefish.portablecrafting.PortableCrafting;
import com.gr8pefish.portablecrafting.inventory.InventoryPortableCrafting;
import com.gr8pefish.portablecrafting.inventory.PortableCrafterContainer;
import com.gr8pefish.portablecrafting.items.ItemBase;
import com.gr8pefish.portablecrafting.util.NBTHelper;
import com.gr8pefish.portablecrafting.util.PortableCraftingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

public class ItemPortableCrafter extends ItemBase implements IItemHandler {//implements ISmartItemModel {

    public ItemPortableCrafter() {
        super("portable_crafter");
        setMaxStackSize(1);
        setHasSubtypes(true);
    }


    /**
     * Should disable the item bobbing up and down when the NBT data is saved.
     * @param oldStack - the old instance of this item
     * @param newStack - the new one
     * @param slotChanged - the slot to check
     * @return - if it should do the animation
     */
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !oldStack.getItem().equals(newStack.getItem());
    }

    /**
     * If this function returns true (or the item is damageable), the ItemStack's NBT tag will be sent to the client.
     */
    @Override
    public boolean getShareTag() {
        return true;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     *
     * @param hand - the hand used to click
     * @param world - the world of the event
     * @param player - the player doing the clicking
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            NBTHelper.setUUID(player.getHeldItem(hand));

            player.openGui(PortableCrafting.instance, 0, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    /**
     * Allows items to add custom lines of information to the mouseover description
     *
     * @param itemStack - the itemstack to modify
     * @param player - the player
     * @param list - the information to add
     * @param advanced - to add advanced info
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced) {
        TextFormatting hl = TextFormatting.WHITE;
        TextFormatting rst = TextFormatting.GRAY;
        
        list.add(hl + GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode()) + rst + " key to balance stacks");
        list.add(hl + GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode()) + rst + " key to clear the grid");
        list.add(hl + GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode()) + rst + " and " + hl + GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode()) + rst + " keys to spin");

    }

    //TODO: Idk what this does, what is the point and where does it go?
    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return null;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return null;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 0;
    }
}
