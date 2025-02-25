package pl.coderslab.emailapp;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private String email;

    private int mailboxCapacity = 500;
    private String alternateEmail;
    private String companySuffix = "company.com";
    private final int defaultPasswordLength = 8;

    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = setDepartment();
        this.password = generatePassword(defaultPasswordLength);
        this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department.toLowerCase() + "." + companySuffix;
        System.out.println(email);
        System.out.println(password);
    }


    private String setDepartment() {
        System.out.println("Enter the department: \n 1 for Sales\n 2 for Development\n 3 for Accounting\n 0 for none");
        Scanner scanner = new Scanner(System.in);
        int depChoice;
        String department = null;

        while (!scanner.hasNextInt()) {
            System.out.println("Enter the department: \n 1 for Sales\n 2 for Development\n 3 for Accounting\n 0 for none");
            scanner.next();
        }
        depChoice = scanner.nextInt();

        switch (depChoice) {
            case 1:
                department = "Sales";
                break;
            case 2:
                department = "Development";
                break;
            case 3:
                department = "Accounting";
                break;
            case 0:
                department = "";
        }
        return department;
    }

    private String generatePassword(int passwordLength) {
        String password = RandomStringUtils.randomAscii(passwordLength);

        final String illegalCharacters = "[ `\"',.\\/:;]";
        Pattern compiledPattern = Pattern.compile(illegalCharacters);
        Matcher matcher = compiledPattern.matcher(password);

        int counter = 0;
        while (matcher.find()) {
            password = RandomStringUtils.randomAscii(passwordLength);
            matcher = compiledPattern.matcher(password);
            counter++;
            System.out.println(counter);
        }

        return password;
    }

    public void setMailboxCapacity(int mailboxCapacity) {
        this.mailboxCapacity = mailboxCapacity;
    }

    public void setAlternateEmail(String alternateEmail) {
        String emailRegex = "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(alternateEmail);
        if (matcher.matches()) {
            this.alternateEmail = alternateEmail;
        } else {
            System.out.println("Invalid email");
        }
    }

    public void changePassword(String newPassword) {

        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{"+Integer.toString(defaultPasswordLength) +",}";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(newPassword);

        if (matcher.matches()) {
            this.password = newPassword;
            System.out.println("Password changed successfully");
        } else {
            System.out.println("Invalid password. Must be at least 8 characters long, contain at least one letter, one number and one special character (@$!%*#?&)");
        }

    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getMailboxCapacity() {
        return this.mailboxCapacity;
    }



}
