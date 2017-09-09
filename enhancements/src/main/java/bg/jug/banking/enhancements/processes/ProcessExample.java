package bg.jug.banking.enhancements.processes;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class ProcessExample {

	private static Logger LOGGER = Logger.getLogger(ProcessExample.class.getName());
	
	public static void startProcess() throws IOException, InterruptedException, ExecutionException {
		Process process = Runtime.getRuntime().exec("cmd /c notepad");
		LOGGER.info("PID: " + process.pid());
		LOGGER.info("Number of children: " + process.children().count());
		LOGGER.info("Number of descendants: " + process.descendants().count());
		
		ProcessHandle.Info info = process.info();
		LOGGER.info("Process command: " + info.command());
		LOGGER.info("Info: " + info.toString());
		
//		ProcessHandle handle = process.toHandle();
		
		CompletableFuture<Process> exitedFuture = process.onExit();
		exitedFuture.whenComplete((p, e) -> { LOGGER.info("Process exited ... ");});
		exitedFuture.get();
	}
	
	public static void startProcessWithProcessBuilder() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "services.msc");
		Process p = pb.start();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		startProcess();
//		startProcessWithProcessBuilder();
	}
	
}
