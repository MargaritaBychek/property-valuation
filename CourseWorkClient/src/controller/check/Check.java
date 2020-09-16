package controller.check;

public class Check {
    public static boolean checkDouble(String string) {
        if (string == null || string.length() == 0)
            return false;
        int i = 0;
        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!((c=='.')||(c >= '0' && c <= '9'))) {
                { System.out.println("not double");
                    return false;}
            }
        }
        return true;
    }
    public static boolean checkInt(String string) {
        if (string == null || string.length() == 0)
            return false;
        int i = 0;
        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                {
                    System.out.println("not int");
                    return false;}
            }
        }
        return true;
    }
    public static boolean checkPhone(String string) {
        if (string == null || string.length() == 0)
            return false;
        int i = 0;
        char c;
        c = string.charAt(0);
        if(!((c >= '0' && c <= '9')||(c=='+')))
            return false;
        i++;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                {
                    System.out.println("not int");
                    return false;}
            }
        }
        return true;
    }
    public static boolean checkString(String string) {
        if (string == null || string.length() == 0)
            return false;
        int i = 0;
        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!((c >= 'a' && c <= 'z')||(c >= 'A' && c <= 'Z')||(c=='-')||(c==' ')||(c >= 'А' && c <= 'Я')||(c >= 'а' && c <= 'я'))) {
                { System.out.println("not string");
                    return false;}
            }
        }
        return true;
    }
    public static boolean checkLog(String string) {
        if (string == null || string.length() == 0)
            return false;
        int i = 0;
        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!((c >= '0' && c <= '9')|(c >= 'a' && c <= 'z')||(c >= 'A' && c <= 'Z')||(c=='.')||(c==' ')||(c >= 'А' && c <= 'Я')||(c >= 'а' && c <= 'я'))) {
                { System.out.println("not string");
                    return false;}
            }
        }
        return true;
    }
    public static boolean checkPassport(String string) {
        if (string == null || string.length() == 0||string.length() > 2)
            return false;
        int i = 0;
        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!((c >= 'A' && c <= 'Z')||(c >= 'А' && c <= 'Я'))) {
                { System.out.println("not pass");
                    return false;}
            }
        }
        return true;
    }
    public static boolean checkRegister(String string) {
        if (string == null || string.length() == 0)
            return false;
        int i = 0;
        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!((c >= '0' && c <= '9')||(c >= 'A' && c <= 'Z')||(c >= 'А' && c <= 'Я'))) {
                { System.out.println("not register");
                    return false;}
            }
        }
        return true;
    }
    public static boolean checkAddress(String string) {
        if (string == null || string.length() == 0)
            return false;
        int i = 0;
        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!((c >= '0' && c <= '9')||(c >= 'A' && c <= 'Z')||(c >= 'a' && c <= 'z')||(c==',')||(c==' ')||(c=='.')||(c >= 'А' && c <= 'Я')||(c >= 'а' && c <= 'я'))) {
                { System.out.println("not addr");
                    return false;}
            }
        }
        return true;
    }
    public static boolean checkEmail(String string) {
        if (string == null || string.length() == 0)
            return false;
        int i = 0;
        char c;
        c = string.charAt(0);
        if(!((c >= 'a' && c <= 'z')||(c >= 'A' && c <= 'Z')))
            return false;
        i++;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!((c >= 'a' && c <= 'z')||(c >= 'A' && c <= 'Z')||(c !='@')||(c!='.'))) {
                { System.out.println("not string");
                    return false;}
            }
        }
        return true;
    }
}
