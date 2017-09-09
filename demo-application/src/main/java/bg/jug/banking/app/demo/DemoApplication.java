package bg.jug.banking.app.demo;

import java.util.logging.Logger;

import bg.jug.banking.server.applications.BankingApplication;
import bg.jug.banking.server.services.ProtocolService;

public class DemoApplication implements BankingApplication {

	private static final Logger LOGGER = Logger.getLogger(DemoApplication.class.getName());

	public void execute() {
		LOGGER.info("Executing demo application ...");
		String packet = "...";
		ProtocolService service = new ProtocolService();
		service.sendPacket(packet.getBytes(), "FIX");
	}
	
}
