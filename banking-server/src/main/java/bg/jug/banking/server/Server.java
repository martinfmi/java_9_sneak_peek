package bg.jug.banking.server;

import java.lang.reflect.InvocationTargetException;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.util.Enumeration;
import java.util.ServiceLoader;

import bg.jug.banking.server.applications.BankingApplication;

public class Server {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		System.setSecurityManager(new SecurityManager());
		
		// dumpCodeSource();
		// dumpPermissions();
		// dumpClasspath();
		// dumpDemoAppCodeSource();
		
		ServiceLoader<BankingApplication> apps = ServiceLoader.load(BankingApplication.class);
		for (BankingApplication app : apps) {
			app.execute();
		}
	}
	
	private static void dumpCodeSource() {
		System.out.println(Server.class.getProtectionDomain().getCodeSource());
	}

	private static void dumpPermissions() {
		PermissionCollection permissionsCollection = Policy.getPolicy()
				.getPermissions(Server.class.getProtectionDomain());
		Enumeration<Permission> permissions = permissionsCollection.elements();
		while (permissions.hasMoreElements()) {
			System.out.println(permissions.nextElement().getName());
		}
	}

	private static void dumpDemoAppCodeSource() {

		try {
			CodeSource source = Class.forName("bg.jug.banking.app.demo.DemoApplication").getProtectionDomain()
					.getCodeSource();
			System.out.println(source);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
