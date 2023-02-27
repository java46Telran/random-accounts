package telran.util.model;

public class Account {
public String username;
public String password;
public String experation; //date-time in ISO format YYYY-MM-DDTHH-mm-SS
public String[] roles;
public Account(String username, String password, String experation, String[] roles) {
	this.username = username;
	this.password = password;
	this.experation = experation;
	this.roles = roles;
}
public Account() {
}

}
