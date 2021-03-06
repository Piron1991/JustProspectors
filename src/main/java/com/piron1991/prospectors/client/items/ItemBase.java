package com.piron1991.prospectors.client.items;

import com.piron1991.prospectors.creativeTab.CreativeTab;

import com.piron1991.prospectors.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBase extends Item {

    public ItemBase() {
        super();
        this.setCreativeTab(CreativeTab.testTab);
        this.setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }


    protected String getProperLocalization(ItemStack itemStack){

        if (itemStack.hasTagCompound()){
            NBTTagCompound tag = itemStack.getTagCompound();

            //(?=\p{javaUpperCase}) - RegEx that splits on Case including that cased letter
            String[] oreName = tag.getString("name").split("(?=\\p{javaUpperCase})");
            String properName = "";

            if (oreName.length>2){
                for (int i = oreName.length-1;i>=1;i--){
                    properName = properName+oreName[i]+" ";
                }
                return properName+oreName[0];
            }else{
                return oreName[1]+" "+oreName[0];
            }

        }

        return itemStack.getDisplayName();
    }


    protected String getProperLocalization(String oreName){
        //(?=\p{javaUpperCase}) - RegEx that splits on Case including that cased letter
        String[] oreNameSplit = oreName.split("(?=\\p{javaUpperCase})");
        String properName = "";

        if (oreNameSplit.length>2){
            for (int i = oreNameSplit.length-1;i>=1;i--){
                properName = properName+oreNameSplit[i]+" ";
            }
            return properName+oreNameSplit[0];
        }else{
            return oreNameSplit[1]+" "+oreNameSplit[0];
        }
    }
}
