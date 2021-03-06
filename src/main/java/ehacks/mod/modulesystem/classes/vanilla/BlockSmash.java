package ehacks.mod.modulesystem.classes.vanilla;

import ehacks.mod.api.Module;
import ehacks.mod.wrapper.ModuleCategory;
import ehacks.mod.wrapper.Wrapper;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;

public class BlockSmash
        extends Module {

    public BlockSmash() {
        super(ModuleCategory.RENDER);
    }

    @Override
    public String getName() {
        return "BlockSmash";
    }

    @Override
    public String getDescription() {
        return "Plays the fall effect";
    }

    @Override
    public void onTicks() {
        Wrapper.INSTANCE.player().fallDistance = 100.0f;
        int x = MathHelper.floor_double(Wrapper.INSTANCE.player().posX);
        int y = MathHelper.floor_double((Wrapper.INSTANCE.player().posY - 0.20000000298023224 - Wrapper.INSTANCE.player().yOffset));
        int z = MathHelper.floor_double(Wrapper.INSTANCE.player().posZ);
        Block block = Wrapper.INSTANCE.player().worldObj.getBlock(x, y - 1, z);
        Wrapper.INSTANCE.player().worldObj.playAuxSFX(2006, x, y, z, MathHelper.ceiling_float_int((Wrapper.INSTANCE.player().fallDistance - 3.0f)));
        block.onFallenUpon(Wrapper.INSTANCE.player().worldObj, x, y, z, Wrapper.INSTANCE.player(), Wrapper.INSTANCE.player().fallDistance);
    }
}
