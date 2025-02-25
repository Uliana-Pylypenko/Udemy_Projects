package pl.coderslab.emailapp;
import org.apache.commons.lang3.RandomStringUtils;

public class EmailApp {
    public static void main(String[] args) {
        Email em1 = new Email("Uliana", "Pylypenko");
        em1.changePassword( "admin888!!!!!!");

    }
}
