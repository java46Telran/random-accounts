package telran.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.util.model.Account;

public class RandomAccountsAppl {

	private static final int N_ACCOUNTS = 50;
	private static final int PASSWORD_LENGTH = 8;
	private static final int MIN_HOURS = -100;
	private static final int MAX_HOURS = 400;
	private static final String[] ROLES= {"ADMIN", "STATIST", "USER", "APPL_ADMIN"};
	private static final long N_ROLES = 4;
	private static final String BASE_USER_NAME = "account";
	private static ThreadLocalRandom tlr = ThreadLocalRandom.current();
	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Account> accounts = getRandomAccounts();
		mapper.writeValue(new File("accounts.json"), accounts);
		

	}
	private static List<Account> getRandomAccounts() {
		
		return IntStream.rangeClosed(1, N_ACCOUNTS)
		.mapToObj(RandomAccountsAppl::getRandomAccount)
		.toList();
	}
	private static Account getRandomAccount(int accountNumber) {
		
		return new Account(BASE_USER_NAME + accountNumber, getRandomPassword(),
				getRandomExperation(), getRandomRoles());
	}
	private static String[] getRandomRoles() {
		
		return tlr.ints(N_ROLES, 0, ROLES.length).distinct().mapToObj(ri -> ROLES[ri])
				.toArray(String[]::new);
	}
	private static String getRandomExperation() {
		LocalDateTime experationDateTime = LocalDateTime.now()
				.plusHours(tlr.nextInt(MIN_HOURS, MAX_HOURS + 1));
				
		return experationDateTime.toString();
	}
	private static String getRandomPassword() {
		
		return tlr.ints(PASSWORD_LENGTH, '!', '~' + 1).mapToObj(code -> (char)code + "")
				.collect(Collectors.joining());
	}

}

