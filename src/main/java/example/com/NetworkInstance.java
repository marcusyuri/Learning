package example.com;

import java.util.HashMap;
import java.util.Map;

import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.Subinterface1Builder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.Subinterface2Builder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv4.top.Ipv4Builder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv4.top.ipv4.AddressesBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv4.top.ipv4.addresses.Address;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv4.top.ipv4.addresses.AddressKey;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.Ipv6Builder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.interfaces.top.Interfaces;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.interfaces.top.InterfacesBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.interfaces.top.interfaces.Interface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.interfaces.top.interfaces.InterfaceBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.interfaces.top.interfaces.InterfaceKey;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.subinterfaces.top.SubinterfacesBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.subinterfaces.top.subinterfaces.Subinterface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.subinterfaces.top.subinterfaces.SubinterfaceBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.subinterfaces.top.subinterfaces.SubinterfaceKey;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig._if.types.rev181121.IFETHERNET;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.inet.rev240105.Ipv4Address;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.inet.rev240105.Ipv6Address;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.rev230207.vlan.logical.match.top.MatchBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.rev230207.vlan.logical.match.top.match.SingleTaggedBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.rev230207.vlan.logical.top.VlanBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.types.rev220524.VlanId;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.common.Uint8;

public class NetworkInstance {

	private Address buildIpv4Address(String ipv4Adress, int prefixLength) {
		org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv4.top.ipv4.addresses.address.ConfigBuilder ipv4AddressConfigBuilder = new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv4.top.ipv4.addresses.address.ConfigBuilder();

		return new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv4.top.ipv4.addresses.AddressBuilder() //
				.setIp(new Ipv4Address(ipv4Adress)) //
				.setConfig(ipv4AddressConfigBuilder //
						.setIp(new Ipv4Address(ipv4Adress)) //
						.setPrefixLength(Uint8.valueOf(prefixLength)) //
						.build())
				.build();
	}

	private org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.Address buildIpv6Address(
			String ipv6Adress, int prefixLength) {
		org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.address.ConfigBuilder ipv6AddressConfigBuilder = new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.address.ConfigBuilder();

		return new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.AddressBuilder() //
				.setIp(new Ipv6Address(ipv6Adress)) //
				.setConfig(ipv6AddressConfigBuilder //
						.setIp(new Ipv6Address(ipv6Adress)) //
						.setPrefixLength(Uint8.valueOf(prefixLength)) //
						.build())
				.build();
	}

	private Ipv4Builder addAddress(Ipv4Builder builder, String ipv4Address, int prefixLength) {
		Map<AddressKey, Address> ipv4AddressMap = new HashMap<AddressKey, Address>();

		Address address = buildIpv4Address(ipv4Address, prefixLength);
		ipv4AddressMap.put(address.key(), address);

		if (builder == null)
			builder = new Ipv4Builder();

		builder.setAddresses(new AddressesBuilder() //
				.setAddress(ipv4AddressMap).build());

		return builder;
	}

	private Ipv4Builder createIpv4Builder(String ipv4Address, int prefixLength) {
		return addAddress((Ipv4Builder) null, ipv4Address, prefixLength);
	}

	private Ipv6Builder addAddress(Ipv6Builder builder, String ipv6Address, int prefixLength) {
		Map<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.AddressKey, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.Address> ipv6AddressMap = new HashMap<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.AddressKey, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.Address>();

		org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.addresses.Address address = buildIpv6Address(
				ipv6Address, prefixLength);
		ipv6AddressMap.put(address.key(), address);

		if (builder == null)
			builder = new Ipv6Builder();

		builder.setAddresses(
				new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.ip.rev240313.ipv6.top.ipv6.AddressesBuilder() //
						.setAddress(ipv6AddressMap).build());

		return builder;
	}

	private Ipv6Builder createIpv6Builder(String ipv6Address, int prefixLength) {
		return addAddress((Ipv6Builder) null, ipv6Address, prefixLength);
	}

	private InterfaceBuilder peInterface() {
		org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.interfaces.top.interfaces._interface.ConfigBuilder interfaceConfigBuilder = new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.interfaces.top.interfaces._interface.ConfigBuilder();
		org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.subinterfaces.top.subinterfaces.subinterface.ConfigBuilder subinterfaceConfigBuilder = new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev240404.subinterfaces.top.subinterfaces.subinterface.ConfigBuilder();
		org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.rev230207.vlan.logical.match.top.match.single.tagged.ConfigBuilder singleTaggedVlanConfigBuilderg = new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.rev230207.vlan.logical.match.top.match.single.tagged.ConfigBuilder();
		org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.rev230207.Subinterface1Builder subinterfaceVlanBuilder = new org.opendaylight.yang.gen.v1.http.openconfig.net.yang.vlan.rev230207.Subinterface1Builder();

		// PINK Vlan Subinterface 1042
		SubinterfaceBuilder subinterfaceBuilder = new SubinterfaceBuilder() //
				.setIndex(Uint32.valueOf(1042)) //
				.setConfig(subinterfaceConfigBuilder.setIndex(Uint32.valueOf(1042)) //
						.setDescription("pink VLAN instance in GLOBAL") //
						.build()) //
				.addAugmentation(new Subinterface1Builder() //
						.setIpv4(createIpv4Builder("192.168.1.2", 24) //
								.build()) //
						.build()) //
				.addAugmentation(new Subinterface2Builder() //
						.setIpv6(createIpv6Builder("2001:db8::1", 64) //
								.build()) //
						.build()) //
				.addAugmentation(subinterfaceVlanBuilder.setVlan(new VlanBuilder() //
						.setMatch(new MatchBuilder() //
								.setSingleTagged(new SingleTaggedBuilder() //
										.setConfig(singleTaggedVlanConfigBuilderg //
												.setVlanId(new VlanId(Uint16.valueOf(1042))) //
												.build()) //
										.build()) //
								.build()) //
						.build()) //
						.build());

		Map<SubinterfaceKey, Subinterface> subinterfacesMap = new HashMap<SubinterfaceKey, Subinterface>();
		Subinterface subinterface = subinterfaceBuilder.build();
		subinterfacesMap.put(subinterface.key(), subinterface);

		// BLUE VLAN Subinterface 1400
		subinterfaceBuilder = new SubinterfaceBuilder() //
				.setIndex(Uint32.valueOf(1400)) //
				.setConfig(subinterfaceConfigBuilder.setIndex(Uint32.valueOf(1400)) //
						.setDescription("blue VLAN instance in L2VSI") //
						.build()) //
				.addAugmentation(subinterfaceVlanBuilder.setVlan(new VlanBuilder() //
						.setMatch(new MatchBuilder() //
								.setSingleTagged(new SingleTaggedBuilder() //
										.setConfig(singleTaggedVlanConfigBuilderg //
												.setVlanId(new VlanId(Uint16.valueOf(1400))) //
												.build()) //
										.build()) //
								.build()) //
						.build()) //
						.build());

		subinterface = subinterfaceBuilder.build();
		subinterfacesMap.put(subinterface.key(), subinterface);

		// CYAN VLAN Subinterface 1500
		subinterfaceBuilder = new SubinterfaceBuilder() //
				.setIndex(Uint32.valueOf(1500)) //
				.setConfig(subinterfaceConfigBuilder.setIndex(Uint32.valueOf(1500)) //
						.setDescription("cyan VLAN instance in L2P2P") //
						.build()) //
				.addAugmentation(subinterfaceVlanBuilder.setVlan(new VlanBuilder() //
						.setMatch(new MatchBuilder() //
								.setSingleTagged(new SingleTaggedBuilder() //
										.setConfig(singleTaggedVlanConfigBuilderg //
												.setVlanId(new VlanId(Uint16.valueOf(1500))) //
												.build()) //
										.build()) //
								.build()) //
						.build()) //
						.build());

		subinterface = subinterfaceBuilder.build();
		subinterfacesMap.put(subinterface.key(), subinterface);

		InterfaceBuilder interfaceBuilder = new InterfaceBuilder() //
				.setName("g0/0/0") //
				.setConfig(interfaceConfigBuilder.setName("g0/0/0") //
						.setType(IFETHERNET.VALUE) //
						.setDescription("PE Interface 1")//
						.build()) //
				.setSubinterfaces(new SubinterfacesBuilder() //
						.setSubinterface(subinterfacesMap) //
						.build());

		return interfaceBuilder;
	}

	public Interfaces getInterfaces() {
		Map<InterfaceKey, Interface> interfaceMap = new HashMap<InterfaceKey, Interface>();
		Interface peInterface = peInterface().build();
		interfaceMap.put(peInterface.key(), peInterface);
		InterfacesBuilder interfacesBuilder = new InterfacesBuilder() //
				.setInterface(interfaceMap);

		return interfacesBuilder.build();
	}
}
