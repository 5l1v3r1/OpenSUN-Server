package pl.cwanix.opensun.agentserver.packets.s2c.connection;

import org.apache.commons.lang3.ArrayUtils;
import pl.cwanix.opensun.commonserver.packets.Packet;
import pl.cwanix.opensun.commonserver.packets.PacketCategory;
import pl.cwanix.opensun.commonserver.packets.annotations.OutgoingPacket;
import pl.cwanix.opensun.commonserver.packets.annotations.PacketOPCode;
import pl.cwanix.opensun.utils.datatypes.FixedLengthField;

@SuppressWarnings("checkstyle:MagicNumber")
@OutgoingPacket(category = PacketCategory.CONNECTION, operation = PacketOPCode.CONNECTION_ANS_ENTER_VILLAGE)
public class S2CAnsEnterVillagePacket implements Packet {

    private final FixedLengthField playerKey;

    public S2CAnsEnterVillagePacket(final int playerKey) {
        this.playerKey = new FixedLengthField(4, playerKey);
    }

    @Override
    public Object[] getOrderedFields() {
        return ArrayUtils.toArray(playerKey);
    }
}
