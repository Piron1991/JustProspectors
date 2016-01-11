package com.piron1991.prospectors.handler;

import com.piron1991.prospectors.client.items.PerfectProspector;
import com.piron1991.prospectors.utilities.LogHelper;
import com.piron1991.prospectors.utilities.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

/**
 * Created by WhoCares on 2016-01-10.
 */
public class SpecialRecipeHandler implements IRecipe {

    ArrayList<Block> oredict = ConfigHandler.blocks;

    @Override
    public boolean matches(InventoryCrafting invCraft, World world) {

        ItemStack prospector=null;
        ItemStack ore = null;

        for (int i=0;i<3;i++){
            for (int j = 0;j<3;j++){
                ItemStack itemstack = invCraft.getStackInRowAndColumn(j, i);
                if(itemstack == null)continue;
                if (itemstack.getItem() instanceof PerfectProspector){
                    prospector = itemstack;
                }if (oredict.contains(Block.getBlockFromItem(itemstack.getItem()))){
                    ore = itemstack;
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
        int index = 0;
        for (int i=0;i<3;i++){
            for (int j = 0;j<3;j++){
                ItemStack itemstack = invCraft.getStackInRowAndColumn(j, i);
                if(itemstack == null)continue;
                if (itemstack.getItem() instanceof PerfectProspector){
                    prospector = itemstack;
                }if (oredict.contains(Block.getBlockFromItem(itemstack.getItem()))){
                    ore = itemstack;
                    index= oredict.indexOf(Block.getBlockFromItem(itemstack.getItem()));
                }
            }
        }
        if (prospector !=null && ore !=null){



            ItemStack result = prospector.copy();
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            ore.writeToNBT(nbttagcompound);
            nbttagcompound.setString("oreDictName", OreDictionary.getOreName(OreDictionary.getOreIDs(ore)[0]));
            result.setTagCompound(nbttagcompound);
//            NBTHelper.setTagCompound(result,"stackOreBlock,",nbttagcompound);
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
