module banking.server {
	requires java.logging;
	exports bg.jug.banking.server.applications;
	exports bg.jug.banking.server.protocol;
	exports bg.jug.banking.server.services;
	exports bg.jug.banking.server.protocol.permissions;
	uses bg.jug.banking.server.applications.BankingApplication;
	uses bg.jug.banking.server.protocol.BankingProtocol;
}
