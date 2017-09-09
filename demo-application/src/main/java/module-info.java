module application.demo {
	requires java.logging;
	requires banking.server;
	provides bg.jug.banking.server.applications.BankingApplication with bg.jug.banking.app.demo.DemoApplication;
}