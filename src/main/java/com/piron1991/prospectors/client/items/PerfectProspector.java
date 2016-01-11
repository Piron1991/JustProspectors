package com.piron1991.prospectors.client.items;

import com.piron1991.prospectors.handler.ConfigHandler;
import com.piron1991.prospectors.utilities.LogHelper;
import com.piron1991.prospectors.utilities.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WhoCares on 2016-01-10.
 */
public class PerfectProspector extends ItemBase{
    private Random rand = new Random();
    public PerfectProspector() {
        super();
        this.setUnlocalizedName("perfectProspector");
        this.setMaxDamage(50);
        this.setMaxStackSize(1);

    }


    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int face, float hit_x, float hit_y, float hit_z) {
        if (!world.isRemote) {

            ArrayList<Block> temp= ConfigHandler.blocks;
            Block testedBlock;
            Integer val = -1;
            int temp_x = x;
            int temp_y = y;
            int temp_z = z;
            if (itemstack.hasTagCompound()) {

                NBTTagCompound tag = NBTHelper.getTagCompound(itemstack,"stackOreBlock");
                ItemStack _temp = ItemStack.loadItemStackFromNBT(tag);
                LogHelper.info(tag.toString());
                Block savedBlock=Block.getBlockFromItem(_temp.getItem());


                for (int k = z - 3; k <= z + 3; k++) {
                    for (int i = x - 3; i <= x + 3; i++) {
                        for (int j = y; j <= y + 5; j++) {
                            testedBlock = world.getBlock(i, j, k);
                            if (testedBlock.equals(savedBlock)) {
                                val = 1;
                                temp_x = i;
                                temp_y = j;
                                temp_z = k;
                                }if (val==1)break;
                            }if (val==1)break;
                        }if (val==1)break;
                    }
                if (val == 1) {
                    player.addChatComponentMessage(
                            new ChatComponentText(
                                    "Found ore at: "
                                            + getProperLocalization(new ItemStack(temp.get(val)))
                            )
                    );

                    player.addChatComponentMessage(
                            new ChatComponentText(
                                    "Around x: "
                                            + (temp_x + rand.nextInt(11) - 5)
                                            + " y: "
                                            + (temp_y + rand.nextInt(7) - 3)
                                            + " z: "
                                            + (temp_z + rand.nextInt(11) - 5)
                            )
                    );
                } else {
                    player.addChatComponentMessage(new ChatComponentText("No ores found"));
                }
                itemstack.damageItem(1,player);



            }else{
                player.addChatComponentMessage(new ChatComponentText("No block selected,craft with ore."));
            }

               }
        return true;
    }
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        super.addInformation(stack, player, list, b);
        String oreName = getProperLocalization(stack);
            list.add("Finds selected ore.");
            list.add( oreName.equals(stack.getDisplayName())?"No ore selected":oreName);


        }
    }




