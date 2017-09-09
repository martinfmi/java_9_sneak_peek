Java 9: module system
=====================

- build custom JDK image:
cd D:\software\Java\jdk9_181
bin\jlink.exe --module-path jmods --add-modules java.logging --output D:/software/Java/jdk9_logging_only

- encapsulate app server and applications:
create module-info.java for server and applications (in default packages):
module banking.server {
	requires java.logging;
	exports bg.jug.banking.server.applications;
	exports bg.jug.banking.server.protocol;
	exports bg.jug.banking.server.services;
	exports bg.jug.banking.server.protocol.permissions;
	uses bg.jug.banking.server.applications.BankingApplication;
	uses bg.jug.banking.server.protocol.BankingProtocol;
}
module protocol.alpha {
	requires java.logging;
	requires banking.server;
	provides bg.jug.banking.server.protocol.BankingProtocol with bg.jug.banking.protocol.alpha.AlphaProtocol;
}
module protocol.fix {
	requires java.logging;
	requires banking.server;
	provides bg.jug.banking.server.protocol.BankingProtocol with bg.jug.banking.protocol.fix.FixProtocol;
}
module application.demo {
	requires java.logging;
	requires banking.server;
	provides bg.jug.banking.server.applications.BankingApplication with bg.jug.banking.app.demo.DemoApplication;
}

build server module: 
D:\stuff\seminars\BG_JUG\Java_9_sneak_peak\workspace
set PATH=D:\software\Java\jdk9_181\bin;%PATH%
mkdir modules\banking.server modules\protocol.fix modules\protocol.alpha modules\application.demo

dir /s /B banking-server\src\main\java\bg\jug\*.java > sources.txt
javac -d modules\banking.server banking-server\src\main\java\module-info.java @sources.txt

dir /s /B fix-protocol\src\main\java\bg\jug\*.java > sources.txt
javac --module-path modules -d modules\fix.protocol fix-protocol\src\main\java\module-info.java @sources.txt

dir /s /B alpha-protocol\src\main\java\bg\jug\*.java > sources.txt
javac --module-path modules -d modules\alpha.protocol alpha-protocol\src\main\java\module-info.java @sources.txt

dir /s /B demo-application\src\main\java\bg\jug\*.java > sources.txt
javac --module-path modules -d modules\demo.application demo-application\src\main\java\module-info.java @sources.txt

java --module-path modules -m banking.server/bg.jug.banking.server.Server

- verify usage of internal APIs
cd banking-server
jdeps.exe -jdkinternals
jdeps.exe --compile-time .

Java 9: the good parts
======================

- jshell

bin\jshell
2 + 3
public int sum(int a, int b) { return a + b; }
import java.util.logging
import java.util.logging.*
Logger x = null;
class z {}
/help
/imports
/vars
/methods
/env
/types
/list
/list -all
/save & /open
/exit

- method/variable redefinition
- autocompletion
