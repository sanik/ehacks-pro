package ehacks.mod.modulesystem.classes.vanilla;

import ehacks.mod.api.Module;
import ehacks.mod.config.CheatConfiguration;
import ehacks.mod.wrapper.ModuleCategory;
import ehacks.mod.wrapper.Wrapper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C07PacketPlayerDigging;

public class Nuker
        extends Module {

    public static boolean isActive = false;

    public Nuker() {
        super(ModuleCategory.PLAYER);
    }

    @Override
    public String getName() {
        return "Nuker";
    }

    @Override
    public String getDescription() {
        return "Default nuker";
    }

    @Override
    public void onModuleEnabled() {
        isActive = true;
    }

    @Override
    public void onModuleDisabled() {
        isActive = false;
    }

    @Override
    public void onTicks() {
        int radius = CheatConfiguration.config.nukerradius;
        block6:
        {
            block7:
            {
                if (!Wrapper.INSTANCE.mc().playerController.isInCreativeMode()) {
                    break block7;
                }
                for (int i = radius; i >= -radius; --i) {
                    for (int k = radius; k >= -radius; --k) {
                        for (int j = -radius; j <= radius; ++j) {
                            int x = (int) (Wrapper.INSTANCE.player().posX + i);
                            int y = (int) (Wrapper.INSTANCE.player().posY + j);
                            int z = (int) (Wrapper.INSTANCE.player().posZ + k);
                            Block blockID = Wrapper.INSTANCE.world().getBlock(x, y, z);
                            if (blockID.getMaterial() == Material.air) {
                                continue;
                            }
                            Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, x, y, z, 0));
                        }
                    }
                }
                break block6;
            }
            if (!Wrapper.INSTANCE.mc().playerController.isNotCreative()) {
                break block6;
            }
            for (int i = radius; i >= -radius; --i) {
                for (int k = radius; k >= -radius; --k) {
                    for (int j = -radius; j <= radius; ++j) {
                        int x = (int) (Wrapper.INSTANCE.player().posX + i);
                        int y = (int) (Wrapper.INSTANCE.player().posY + j);
                        int z = (int) (Wrapper.INSTANCE.player().posZ + k);
                        Block block = Wrapper.INSTANCE.world().getBlock(x, y, z);
                        if (block.getMaterial() == Material.air) {
                            continue;
                        }
                        Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, x, y, z, 0));
                        Wrapper.INSTANCE.player().sendQueue.addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, 0));
                    }
                }
            }
        }
    }
}
