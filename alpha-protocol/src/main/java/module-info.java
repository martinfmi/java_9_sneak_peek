module protocol.alpha {
	requires java.logging;
	requires banking.server;
	provides bg.jug.banking.server.protocol.BankingProtocol 
	with bg.jug.banking.protocol.alpha.AlphaProtocol;
}