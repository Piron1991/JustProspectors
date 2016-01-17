package com.piron1991.prospectors.handler;

import com.piron1991.prospectors.client.items.PerfectProspector;
import com.piron1991.prospectors.utilities.BlockDataHolder;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SpecialRecipeHandler implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting invCraft, World world) {

        ItemStack prospector=null;
        ItemStack ore = null;

        for (int i=0;i<3;i++){
            for (int j = 0;j<3;j++){
                ItemStack itemstack = invCraft.getStackInRowAndColumn(j, i);

                if(itemstack == null)continue;
                if (itemstack.getItem() instanceof PerfectProspector){
                    prospector = itemstack;}
                for (BlockDataHolder data:ConfigHandler.oreArray){
                    if (Block.getBlockFromItem(itemstack.getItem())==(data.getBlock())) {
                        ore = itemstack;
                    }
                }

                if (prospector !=null && ore !=null){return true;}
            }
            if (prospector !=null && ore !=null){return true;}
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting invCraft) {

        ItemStack prospector=null;
        ItemStack ore = null;
        BlockDataHolder info=null;

        for (int i=0;i<3;i++){
            for (int j = 0;j<3;j++){
                ItemStack itemstack = invCraft.getStackInRowAndColumn(j, i);

                if(itemstack == null)continue;

                if (itemstack.getItem() instanceof PerfectProspector)
                {
                    prospector = itemstack;
                }
                for (BlockDataHolder data:ConfigHandler.oreArray)
                {
                    if (Block.getBlockFromItem(itemstack.getItem())==(data.getBlock())) {
                        ore = itemstack;
                        info=data;
                    }
                }
            }
        }
        if (prospector !=null && ore !=null){

            ItemStack result = prospector.copy();
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setInteger("block",Block.getIdFromBlock(info.getBlock()));
            nbttagcompound.setByte("meta",(byte)info.getMeta());
            nbttagcompound.setString("name",info.getOredict());
            result.setTagCompound(null);
            result.setTagCompound(nbttagcompound);
            return result;
        }
        return null;
    }

    @Override
    public int getRecipeSize() {
        return 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
