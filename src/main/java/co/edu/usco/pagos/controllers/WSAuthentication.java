package co.edu.usco.pagos.controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

class WSAuthentication {

	/**
	 * Example of use, in your code you can ignore this function
	 * 
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, ParseException {

		String text = "2016-09-15T13:49:01-05:00";
		// Timestamp ts = Timestamp.valueOf(text);
		// System.out.println("Ok la fecha es:" + ts.getNanos() + " =" + ts);

		
	    
	    
		
		String login = "0a6873c10bc872b6538a876cd4292528";
		String tranKey = "35S4vNS1MJC7nanp";

		WSAuthentication auth = new WSAuthentication(login, tranKey);

		// Example of the values to use. YOU NEED TO CHANGE FOR YOUR OWN LOGIN
		// AND TRANKEY
		System.out.println("Login: " + auth.getLogin());
		System.out.println("TranKey: " + auth.getTranKey());
		System.out.println("Seed: " + auth.getSeed());
		System.out.println("Nonce: " + auth.getNonce());

		// This is just a quick test, IGNORE IT
		String nonce = "WmEyvut9GgvcMWrV";
		String seed = "2016-08-30T16:21:35+00:00";
		auth.setNonce(nonce).setSeed(seed);
		if (auth.getTranKey().equals("i/RFwSHAh8d7YgtO3HME5kCnYy8=")
				&& auth.getNonce().equals("V21FeXZ1dDlHZ3ZjTVdyVg==")) {
			System.out.println("--- Quick DIGEST test passed ---");
		} else {
			System.out.println("--- Quick DIGEST test FAILING ---");
		}
		if (auth.getBasicTranKey().equals("535523ff10c67a75bc947b56d53ce7e27f86dbc4")) {
			System.out.println("--- Quick BASIC test passed ---");
		} else {
			System.out.println("--- Quick BASIC test FAILING: " + auth.getBasicTranKey() + " ---");
		}
	}

	protected String login;
	protected String tranKey;
	protected String seed;
	protected String nonce;

	public WSAuthentication(String login, String tranKey) {
		this.login = login;
		this.tranKey = tranKey;
		this.generate();
	}

	/**
	 * Invoque this function each time that you use this class to generate a WS
	 * call this will save the need to construct a new class every time
	 * 
	 * @return
	 */
	public WSAuthentication generate() {
		this.nonce = new BigInteger(130, new SecureRandom()).toString();
		this.seed = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ", Locale.getDefault())).format(new Date());
		return this;
	}

	/**
	 * Returns the login to use on the authentication structure
	 * 
	 * @return String
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Constructs the password digest String to use on the authentication
	 * structure
	 * 
	 * @return String
	 */
	public String getTranKey() {
		try {
			return base64(sha1(nonce + seed + tranKey));
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * Constructs the password hash for simple authentication services like AIM
	 * or PSE
	 * 
	 * @return String
	 */
	public String getBasicTranKey() {
		try {
			return sha1String(seed + tranKey);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * Returns the seed on which the password digest has been calculated
	 * 
	 * @return
	 */
	public String getSeed() {
		return this.seed;
	}

	/**
	 * Returns the Base64 encoded nonce used to generate the password digest
	 * 
	 * @return
	 */
	public String getNonce() {
		return base64(nonce.getBytes());
	}

	/**
	 * FOR TESTING PURPOSES, there is no need to set this one, actually is a
	 * security risk created to test the password digest algorithm
	 * 
	 * @param seed
	 * @return
	 */
	public WSAuthentication setSeed(String seed) {
		this.seed = seed;
		return this;
	}

	/**
	 * FOR TESTING PURPOSES, there is no need to set this one, actually is a
	 * security risk created to test the password digest algorithm
	 * 
	 * @param nonce
	 * @return
	 */
	public WSAuthentication setNonce(String nonce) {
		this.nonce = nonce;
		return this;
	}

	static byte[] sha1(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		return mDigest.digest(input.getBytes());
	}

	static String sha1String(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	static String base64(byte[] input) {
		byte[] encodedBytes = (Base64.getEncoder()).encode(input);
		return new String(encodedBytes);
	}

}