package com.piron1991.prospectors.client.items;

import com.piron1991.prospectors.handler.ConfigHandler;
import com.piron1991.prospectors.utilities.BlockDataHolder;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by WhoCares on 2016-01-10.
 */
public class CrapProspector extends ItemBase {
    private Random rand = new Random();
    public CrapProspector() {
        super();
        this.setUnlocalizedName("crapProspector");
        this.setMaxDamage(50);
        this.setMaxStackSize(1);
    }


    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int face, float hit_x, float hit_y, float hit_z) {
        if (!world.isRemote) {

            Block testedBlock;
            Integer val = ConfigHandler.oreList.length+1;
            int temp_x = x;
            int temp_y = y;
            int temp_z = z;
            BlockDataHolder data = null;

            for (int k = z - 3; k <= z + 3; k++) {
                for (int i = x - 3; i <= x + 3; i++) {
                    for (int j = y; j <= y + 5; j++) {
                        testedBlock = world.getBlock(i, j, k);
                        int meta = world.getBlockMetadata(i,j,k);
                        for (BlockDataHolder _data:ConfigHandler.oreArray){

                            if (_data.contains(testedBlock,meta) && _data.getValue()<val){
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


            if (val != ConfigHandler.oreList.length+1){
                player.addChatComponentMessage(
                        new ChatComponentText(
                                "Worst found ore belongs to: "
                                        +getProperLocalization(data.getStack())
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

        }
        return true;
    }


    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        super.addInformation(stack, player, list, b);
        list.add("Finds the least valuable ores in close range.");

    }
}
