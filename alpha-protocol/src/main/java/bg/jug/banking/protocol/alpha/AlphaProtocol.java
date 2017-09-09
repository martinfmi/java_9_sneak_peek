package bg.jug.banking.protocol.alpha;

import java.util.logging.Logger;

import bg.jug.banking.server.protocol.BankingProtocol;

public class AlphaProtocol implements BankingProtocol {

	private static final Logger LOGGER = Logger.getLogger(AlphaProtocol.class.getName());
	
	public String getName() {
		return "Alpha";
	}
	
	public Object execute(byte[] packet) {
		
		LOGGER.info("Sending alpha protocol packet ... ");
		// Logic to execute the alpha packet ... 
		
		return null;
	}
	
	public static void main(String[] args) {
		Module module = AlphaProtocol.class.getModule();
	}	
	
}
