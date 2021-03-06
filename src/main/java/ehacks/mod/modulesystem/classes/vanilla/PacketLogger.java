package ehacks.mod.modulesystem.classes.vanilla;

import ehacks.mod.api.Module;
import ehacks.mod.packetlogger.Gui;
import ehacks.mod.packetlogger.PacketHandler;
import ehacks.mod.wrapper.ModuleCategory;
import ehacks.mod.wrapper.PacketHandler.Side;

public class PacketLogger
        extends Module {

    private final Gui gui;
    private final PacketHandler handler;

    public PacketLogger() {
        super(ModuleCategory.EHACKS);
        gui = new Gui();
        handler = new PacketHandler(gui);
    }

    @Override
    public String getName() {
        return "PacketLogger";
    }

    @Override
    public String getDescription() {
        return "Allows you to see all out- and incoming packets (c) N1nt3nd0";
    }

    @Override
    public void onModuleEnabled() {
        gui.setVisible(true);
    }

    @Override
    public void onModuleDisabled() {
        gui.setVisible(false);
    }

    @Override
    public void onTicks() {
        if (!gui.isVisible()) {
            this.off();
        }
    }

    @Override
    public boolean onPacket(Object packet, Side side) {
        try {
            if (side == Side.IN && !handler.handlePacket(packet, null, PacketHandler.inBlackList)) {
                return false;
            }
            if (side == Side.OUT && !handler.handlePacket(packet, null, PacketHandler.outBlackList)) {
                return false;
            }
            handler.handlePacket(packet, side, PacketHandler.logBlackList);
        } catch (Exception ex) {
        }
        return true;
    }
}
