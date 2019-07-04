package pl.cwanix.opensun.agentserver.packets.s2c.connection;

import io.netty.channel.ChannelHandlerContext;
import pl.cwanix.opensun.agentserver.properties.AgentServerProperties;
import pl.cwanix.opensun.agentserver.server.AgentServerChannelHandler;
import pl.cwanix.opensun.commonserver.packets.OutgoingPacket;
import pl.cwanix.opensun.commonserver.packets.Packet;
import pl.cwanix.opensun.commonserver.packets.PacketCategory;
import pl.cwanix.opensun.utils.datatypes.FixedLengthField;

@OutgoingPacket(category = PacketCategory.CONNECTION, type = 0x15)
public class S2CAnsWorldConnectPacket implements Packet {

	private FixedLengthField worldServerIp;
	private FixedLengthField worldServerPort;

	public S2CAnsWorldConnectPacket() {
		worldServerIp = new FixedLengthField(32);
		worldServerPort = new FixedLengthField(2);
	}

	@Override
	public void process(ChannelHandlerContext ctx) {
		AgentServerProperties properties = ctx.channel().attr(AgentServerChannelHandler.PROPERIES_ATTRIBUTE).get();
		
		worldServerIp.setValue(properties.getWorld().getIp());
		worldServerPort.setValue(properties.getWorld().getPort());
	}
}
