package ehacks.mod.modulesystem.classes.mods.thaumichorizons;

import ehacks.mod.api.ModStatus;
import ehacks.mod.api.Module;
import ehacks.mod.util.InteropUtils;
import ehacks.mod.wrapper.ModuleCategory;
import ehacks.mod.wrapper.Wrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.lwjgl.input.Keyboard;

public class VerticalGui
        extends Module {

    public VerticalGui() {
        super(ModuleCategory.EHACKS);
    }

    @Override
    public String getName() {
        return "VerticalGui";
    }

    @Override
    public String getDescription() {
        return "Opens a gui to perform craft dupe\nUsage:\n  Y - Open gui";
    }

    @Override
    public void onModuleEnabled() {
        try {
            Class.forName("com.kentington.thaumichorizons.common.lib.PacketFingersToServer");
            InteropUtils.log("Press Y for GUI", this);
        } catch (Exception ex) {
            this.off();
        }
    }

    @Override
    public ModStatus getModStatus() {
        try {
            Class.forName("com.kentington.thaumichorizons.common.lib.PacketFingersToServer");
            return ModStatus.WORKING;
        } catch (Exception e) {
            return ModStatus.NOTWORKING;
        }
    }

    private boolean prevState = false;

    @Override
    public void onTicks() {
        boolean newState = Keyboard.isKeyDown(Keyboard.KEY_Y);
        if (newState && !prevState) {
            openGui();
        }
        prevState = newState;
    }

    public void openGui() {
        ByteBuf buf = Unpooled.buffer(0);
        buf.writeByte(9);
        buf.writeInt(Wrapper.INSTANCE.player().getEntityId());
        buf.writeInt(Wrapper.INSTANCE.player().dimension);
        C17PacketCustomPayload packet = new C17PacketCustomPayload("thaumichorizons", buf);
        Wrapper.INSTANCE.player().sendQueue.addToSendQueue(packet);
        InteropUtils.log("Opened", this);
    }

    @Override
    public String getModName() {
        return "Horizons";
    }
}
