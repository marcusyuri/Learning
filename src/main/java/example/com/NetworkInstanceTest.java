package example.com;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv4.top.ipv4.addresses.Address;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv4.top.ipv4.addresses.AddressKey;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.interfaces.top.interfaces.Interface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.interfaces.top.interfaces.InterfaceKey;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.subinterfaces.top.subinterfaces.Subinterface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.subinterfaces.top.subinterfaces.SubinterfaceKey;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig._if.types.rev181121.IFETHERNET;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.inet.rev240105.Ipv4Address;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.inet.rev240105.Ipv6Address;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.rev230207.Subinterface1;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.rev230207.vlan.logical.top.Vlan;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.types.rev220524.VlanId;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;

class NetworkInstanceTest {

	@Test
	void testGetInterfaces() {
		NetworkInstance n = new NetworkInstance();

		Map<InterfaceKey, Interface> interfaces = n.getInterfaces().getInterface();
		Interface iface = interfaces.get(new InterfaceKey("g0/0/0"));
		assertNotNull(iface);
		assertEquals("g0/0/0", iface.getName());
		assertEquals("PE Interface 1", iface.getConfig().getDescription());
		assertEquals(IFETHERNET.VALUE, iface.getConfig().getType());

		Map<SubinterfaceKey, Subinterface> subInterfaces = iface.getSubinterfaces().getSubinterface();
		assertEquals(3, subInterfaces.size());

		Subinterface pinkSubinterface = subInterfaces.get(new SubinterfaceKey(Uint32.valueOf(1042)));
		assertEquals(Uint32.valueOf(1042), pinkSubinterface.getConfig().getIndex());
		assertEquals("pink VLAN instance in GLOBAL", pinkSubinterface.getConfig().getDescription());

		// Vlan Augmentation
		Vlan vlan = pinkSubinterface.augmentation(Subinterface1.class).getVlan();

		// Ipv4 Augmentation
		assertEquals(new VlanId(Uint16.valueOf(1042)), vlan.getMatch().getSingleTagged().getConfig().getVlanId());
		Map<AddressKey, Address> ipv4Addresses = pinkSubinterface.augmentation(
				org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.Subinterface1.class)
				.getIpv4().getAddresses().getAddress();
		Address ipv4 = ipv4Addresses.get(new AddressKey(new Ipv4Address("192.168.1.2")));
		assertEquals("192.168.1.2", ipv4.getIp().getValue());

		// Ipv6 Augmentation
		Map<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.AddressKey, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.Address> ipv6ddresses = pinkSubinterface
				.augmentation(
						org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.Subinterface2.class)
				.getIpv6().getAddresses().getAddress();
		org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.Address ipv6 = ipv6ddresses
				.get(new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.AddressKey(
						new Ipv6Address("2001:db8::1")));
		assertEquals("2001:db8::1", ipv6.getIp().getValue());
	}
}
