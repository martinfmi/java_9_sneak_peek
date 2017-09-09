package bg.jug.banking.enhancements.processes;

import java.util.logging.Logger;

public class ProcessHandleExample {

	private static final Logger LOGGER = Logger.getLogger(ProcessHandleExample.class.getName());
	
	public static long getCurrentPid() {
		long pid = ProcessHandle.current().pid();
		LOGGER.info("PID: " + pid);
		return pid;
	}
	
	public static ProcessHandle[] getProcessesByName(String name) {
		ProcessHandle[] processes = ProcessHandle.allProcesses().filter((pHandle) -> { return pHandle.info().toString().contains(name); }).toArray(ProcessHandle[] :: new);
		for(ProcessHandle process : processes) {
			LOGGER.info("Process details: " + process.info().toString());
		}
		
		return processes;
	}
	
	public static void main(String[] args) {
//		getCurrentPid();
		getProcessesByName("chrome");
	}
	
}
