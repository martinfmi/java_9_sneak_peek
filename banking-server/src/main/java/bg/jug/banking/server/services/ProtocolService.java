package bg.jug.banking.server.services;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ServiceLoader;

import bg.jug.banking.server.protocol.BankingProtocol;
import bg.jug.banking.server.protocol.permissions.ProtocolPermission;

public class ProtocolService {
	
	public void sendPacket(byte[] packet, String protocolName) {

		if(protocolName == null) {
			throw new RuntimeException("Protocol name not specified.");
		}
		
		BankingProtocol targetProtocol = AccessController.doPrivileged(new PrivilegedAction<BankingProtocol>() {

			@Override
			public BankingProtocol run() {
				ServiceLoader<BankingProtocol> protocols = ServiceLoader.load(BankingProtocol.class);
				BankingProtocol targetProtocol = null;
				for(BankingProtocol protocol : protocols) {
					if(protocolName.equals(protocol.getName())) {
						return protocol;
					}
				}
				
				return null;
			}
		});
	
		if(targetProtocol == null) {
			throw new RuntimeException(String.format("Target protocol: %s not found", protocolName));
		}
		
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager != null) {
			securityManager.checkPermission(new ProtocolPermission(protocolName));
		}

		targetProtocol.execute(packet);
	}
}
