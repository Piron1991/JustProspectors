package com.piron1991.prospectors.client.items;


import com.piron1991.prospectors.handler.ConfigHandler;
import com.piron1991.prospectors.utilities.BlockDataHolder;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;


public class ValueProspector extends ItemBase {

    private Random rand = new Random();
    public ValueProspector() {
        super();
        this.setUnlocalizedName("valueProspector");
        this.setMaxDamage(50);
        this.setMaxStackSize(1);
    }


    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int face, float hit_x, float hit_y, float hit_z) {
        if (!world.isRemote) {

            Block testedBlock;
            Integer val = -1;
            int temp_x = x;
            int temp_y = y;
            int temp_z = z;
            BlockDataHolder data = null;


            for (int k = z - ConfigHandler.xz_size; k <= z + ConfigHandler.xz_size; k++) {
                for (int i = x - ConfigHandler.xz_size; i <= x + ConfigHandler.xz_size; i++) {
                    for (int j = y; j <= y + ConfigHandler.y_size; j++) {
                        testedBlock = world.getBlock(i, j, k);
                        int meta = world.getBlockMetadata(i,j,k);
                        for (BlockDataHolder _data:ConfigHandler.oreArray){

                            if (_data.contains(testedBlock,meta) && _data.getValue()>val){
                                val=_data.getValue();
                                temp_x=i;
                                temp_y=j;
                                temp_z=k;
                                data=_data;
                                break;
                            }
                        }
                    }
                }
            }


            if (val != -1){
                player.addChatComponentMessage(
                        new ChatComponentText(
                                "Best found ore belongs to: "
                                        +getProperLocalization(data.getOredict())
                        )
                );

                player.addChatComponentMessage(
                        new ChatComponentText(
                                "Around x: "
                                        +(temp_x+rand.nextInt(11)-5)
                                        +" y: "
                                        +(temp_y+rand.nextInt(7)-3)
                                        +" z: "
                                        +(temp_z+rand.nextInt(11)-5)
                        )
                );
                itemstack.damageItem(1,player);
            }else{
                player.addChatComponentMessage(new ChatComponentText("No ores found"));
            }


          /*  for (Block block :ConfigHandler.blocks) {
                LogHelper.info(block.getLocalizedName());
            }*/
        }
        return true;
    }
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        super.addInformation(stack, player, list, b);
        list.add("Finds the most valuable ores in close range.");

    }
}