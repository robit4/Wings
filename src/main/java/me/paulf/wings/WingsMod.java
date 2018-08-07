package me.paulf.wings;

import baubles.api.render.IRenderBauble;
import me.paulf.wings.server.capability.Flight;
import me.paulf.wings.server.item.StandardWing;
import me.paulf.wings.server.net.Network;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = WingsMod.ID,
	name = WingsMod.NAME,
	version = WingsMod.VERSION,
	dependencies =
		"required-after:baubles;" +
		"required-after:llibrary@[1.7,1.8)",
	acceptedMinecraftVersions = "[1.12]"
)
public final class WingsMod {
	public static final String ID = "wings";

	public static final String NAME = "Wings";

	public static final String VERSION = "1.0.0";

	private static final class Holder {
		private static final WingsMod INSTANCE = new WingsMod();
	}

	@SidedProxy(
		clientSide = "me.paulf.wings.client.ClientProxy",
		serverSide = "me.paulf.wings.server.ServerProxy"
	)
	private static Proxy proxy;

	@Mod.EventHandler
	public void init(FMLPreInitializationEvent event) {
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	public void renderWings(StandardWing type, EntityPlayer player, IRenderBauble.RenderType renderType, float delta) {
		proxy.renderWings(type, player, renderType, delta);
	}

	public Flight newFlight(EntityPlayer player) {
		return proxy.newFlight(player);
	}

	public Network getNetwork() {
		return proxy.getNetwork();
	}

	@Mod.InstanceFactory
	public static WingsMod instance() {
		return Holder.INSTANCE;
	}
}
