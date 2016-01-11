package com.piron1991.prospectors.init;


import com.piron1991.prospectors.handler.SpecialRecipeHandler;
import com.piron1991.prospectors.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class InitRecipe {


    public static void init() {
        RecipeSorter.register(Reference.MOD_ID + ":SpecialRecipeHandler", SpecialRecipeHandler.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.valueProspector),"AAA","BCD"," C ",'A', "cobblestone",'B', "sand",'C', "stickWood",'D', new ItemStack(Blocks.gravel)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.crapProspector),"AAA"," C ","BCD",'A', "cobblestone",'B', "sand",'C', "stickWood",'D', new ItemStack(Blocks.gravel)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.perfectProspector),"AAA"," C "," C ",'A', "cobblestone",'B', "sand",'C', "stickWood",'D', new ItemStack(Blocks.gravel)));
        GameRegistry.addRecipe(new SpecialRecipeHandler());

    }



}


