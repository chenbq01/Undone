package constellation.virgo.spring.template.common;

import java.util.regex.Pattern;

public class IPAddressValidator {

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static boolean validate(final String ip) {
		return Pattern.compile(IPADDRESS_PATTERN).matcher(ip).matches();
	}
}
