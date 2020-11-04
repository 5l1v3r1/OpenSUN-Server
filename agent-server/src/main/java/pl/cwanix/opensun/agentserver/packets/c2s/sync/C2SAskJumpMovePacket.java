package pl.cwanix.opensun.agentserver.packets.c2s.sync;

import pl.cwanix.opensun.commonserver.packets.Packet;
import pl.cwanix.opensun.commonserver.packets.PacketCategory;
import pl.cwanix.opensun.commonserver.packets.annotations.IncomingPacket;
import pl.cwanix.opensun.commonserver.packets.annotations.PacketOPCode;

@IncomingPacket(category = PacketCategory.SYNC, operation = PacketOPCode.SYNC_ASK_JUMP_MOVE)
public class C2SAskJumpMovePacket implements Packet {

    public C2SAskJumpMovePacket(final byte[] value) {
        // TODO Auto-generated constructor stub
    }

}
