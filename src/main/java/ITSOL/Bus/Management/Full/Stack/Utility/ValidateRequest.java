package ITSOL.Bus.Management.Full.Stack.Utility;

import ITSOL.Bus.Management.Full.Stack.Exception.*;

public class ValidateRequest {
    public static String validPhone(String in) {
        if (in.isEmpty()) throw new ResourceNotFoundException("Empty Phone Number");
        if (in.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b")) {
            return in;
        } else {
            throw new InvalidPhoneNumberFormatException("Invalid Phone Format");
        }
    }

    public static String validLevel(String in) {
        if (in.isEmpty()) throw new ResourceNotFoundException("Empty Driver Level");
        if (in.matches("[a-fA-F]{1}")) {
            return in;
        } else {
            throw new InvalidLevelFormatException("Invalid Driver Level Format");
        }
    }

    public static int validInteger(String num) {
        if (num.isEmpty()) throw new ResourceNotFoundException("Empty station's number");
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new InvalidIntegerFormat("Wrong format station's number !");
        }
    }

    public static float validFloat(String num) {
        if (num.isEmpty()) throw new ResourceNotFoundException("Empty distance's number");
        try {
            return Float.parseFloat(num);
        } catch (NumberFormatException e) {
            throw new InvalidFloatFormat("Wrong format distance's number");
        }

    }

}
