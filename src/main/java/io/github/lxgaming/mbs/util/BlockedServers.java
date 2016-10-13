package io.github.lxgaming.mbs.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import io.github.lxgaming.mbs.MBS;

public class BlockedServers {
	
	private static List<String> BLOCKEDSERVERS_SERVER = new ArrayList<String>();
	private static List<String> BLOCKEDSERVERS_LOCAL = new ArrayList<String>();
	
	public static void checkBlockedServers() {
		int ADDED = 0;
		int REMOVED = 0;
		int NEWTOTAL = 0;
		int OLDTOTAL = 0;
		try {
			getBlockedServers();
			loadBlockedServers();
		} catch (Exception ex) {
			MBS.alert("Failed to gather Blocked Server information!");
			return;
		}
		
		for (String server : BLOCKEDSERVERS_SERVER) {
			if (!BLOCKEDSERVERS_LOCAL.contains(server)) {
				ADDED++;
			}
			NEWTOTAL++;
		}
		
		for (String server : BLOCKEDSERVERS_LOCAL) {
			if (!BLOCKEDSERVERS_SERVER.contains(server)) {
				REMOVED++;
			}
			OLDTOTAL++;
		}
		
		if (ADDED >= 1 || REMOVED >= 1) {
			try {
				saveBlockedServers();
				MBS.alert(ADDED, REMOVED, NEWTOTAL, OLDTOTAL);
			} catch (Exception ex) {
				MBS.alert("Failed to save Blocked Servers!");
			}
		} else {
			MBS.alert("No servers have been added/removed.");
		}
		return;
	}
	
	private static void getBlockedServers() throws Exception {
		URLConnection connection = new URL("https://sessionserver.mojang.com/blockedservers").openConnection();
		connection.setConnectTimeout(10000);
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			BLOCKEDSERVERS_SERVER.add(line);
		}
		return;
	}
	
	private static void loadBlockedServers() throws Exception {
		for (String line : Files.readAllLines(MBS.BLOCKEDSERVERS_FILE.toPath())) {
			BLOCKEDSERVERS_LOCAL.add(line);
		}
		return;
	}
	
	private static void saveBlockedServers() throws Exception {
		Files.write(MBS.BLOCKEDSERVERS_FILE.toPath(), BLOCKEDSERVERS_SERVER, StandardCharsets.UTF_8);
		return;
	}
}