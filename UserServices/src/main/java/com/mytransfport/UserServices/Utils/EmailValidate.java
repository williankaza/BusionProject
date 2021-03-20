package com.mytransfport.UserServices.Utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidate {

    public static boolean isValidEmailAddress(String email) {

        final String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

            //initialize the Pattern object
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
    }
}
