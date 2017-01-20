/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lxgaming.mbs;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import io.github.lxgaming.mbs.util.BlockedServers;
import io.github.lxgaming.mbs.util.ConsoleOutput;
import io.github.lxgaming.mbs.util.Environment;

public class MBS {
	
	public static String VERSION = "0.2.0 ('Blocked')";
	public static File BLOCKEDSERVERS_FILE = new File(".\\blockedservers.txt");
	
	public static void main(String[] args) {
		ConsoleOutput.info("Mojang Blocked Servers v" + VERSION);
		ConsoleOutput.info("Author: LX_Gaming");
		ConsoleOutput.info("Website: http://lxgaming.github.io/");
		ConsoleOutput.info(Environment.getJavaVendor() + " - " + Environment.getJavaVersion());
		ConsoleOutput.info(Environment.getOSName() + " - " + Environment.getOSVersion() + " - " + Environment.getOSArch());
		setup();
	}
	
	public static void setup() {
		if (!BLOCKEDSERVERS_FILE.exists()) {
			ConsoleOutput.warn("'blockedservers.txt' Does not exist, creating...");
			try {
				BLOCKEDSERVERS_FILE.createNewFile();
				ConsoleOutput.info("Successfully created 'blockedservers.txt'.");
			} catch (IOException ex) {
				ConsoleOutput.error("Failed to create 'blockedservers.txt'!");
				ex.printStackTrace();
				System.exit(0);
			}
		} else {
			ConsoleOutput.info("'blockedservers.txt' Does exist.");
		}
		BlockedServers.checkBlockedServers();
		ConsoleOutput.info("Thank you for using");
		ConsoleOutput.info("Mojang Blocked Servers v" + VERSION);
		ConsoleOutput.info("Author: LX_Gaming");
		ConsoleOutput.info("Website: http://lxgaming.github.io/");
		ConsoleOutput.warn("Exiting...");
		System.exit(0);
	}
	
	public static void alert(String string) {
		ConsoleOutput.info(string);
		JOptionPane.showMessageDialog(null, string, "MBS v" + VERSION, JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void alert(int ADDED, int REMOVED, int NEWTOTAL, int OLDTOTAL) {
		ConsoleOutput.info("Blocked Servers Updated!\n" + ADDED + " Servers added!\n" + REMOVED + " Servers removed!\n" + NEWTOTAL + " Blocked Servers Total.");
		JOptionPane.showMessageDialog(null, "Blocked Servers Updated.\n" + ADDED + " Servers added.\n" + REMOVED + " Servers removed.\n" + NEWTOTAL + " Blocked Servers Total.", "MBS v" + VERSION, JOptionPane.WARNING_MESSAGE, null);
	}
}
