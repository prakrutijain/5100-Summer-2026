
// Prakruti Jain

public class Calculator {

    // position tracker - keeps track of where we are in the string
    int pos;
    String expr;

    public double evaluate(String s) {
        // store expression and start at position 0
        expr = s;
        pos = 0;

        // handle empty string
        if (s == null || s.isEmpty()) return -1;

        try {
            double result = parseExpression();

            // if we didn't reach the end, something is wrong
            if (pos != expr.length()) return -1;

            return result;
        } catch (Exception e) {
            return -1;
        }
    }

    // handles + and - (lowest priority)
    private double parseExpression() {
        double left = parseTerm();

        while (pos < expr.length()) {
            char c = expr.charAt(pos);

            if (c == '+') {
                pos++;
                left += parseTerm();
            } else if (c == '-') {
                pos++;
                left -= parseTerm();
            } else {
                break;
            }
        }

        return left;
    }

    // handles * and / (medium priority)
    private double parseTerm() {
        double left = parseFactor();

        while (pos < expr.length()) {
            char c = expr.charAt(pos);

            if (c == '*') {
                pos++;
                left *= parseFactor();
            } else if (c == '/') {
                pos++;
                double right = parseFactor();

                // division by zero check
                if (right == 0) throw new ArithmeticException("Division by zero");
                left /= right;
            } else {
                break;
            }
        }

        return left;
    }

    // handles factorial ! (high priority)
    private double parseFactor() {
        double value = parseBase();

        // check if factorial follows
        while (pos < expr.length() && expr.charAt(pos) == '!') {
            pos++;

            // factorial only works on non-negative integers 0-12
            if (value != Math.floor(value)) throw new ArithmeticException("Factorial of decimal");
            if (value < 0) throw new ArithmeticException("Factorial of negative");
            if (value > 12) throw new ArithmeticException("Factorial too large");

            value = factorial((int) value);
        }

        return value;
    }

    // handles numbers and parentheses (highest priority)
    private double parseBase() {

        // handle parentheses
        if (pos < expr.length() && expr.charAt(pos) == '(') {
            pos++; // skip (
            double value = parseExpression();
            pos++; // skip )
            return value;
        }

        // handle negative numbers
        if (pos < expr.length() && expr.charAt(pos) == '-') {
            pos++;
            return -parseBase();
        }

        // read a number
        int start = pos;
        while (pos < expr.length() &&
               (Character.isDigit(expr.charAt(pos)) || expr.charAt(pos) == '.')) {
            pos++;
        }

        // no number found
        if (start == pos) throw new NumberFormatException("No number found");

        String numStr = expr.substring(start, pos);

        // invalid number formats
        if (numStr.startsWith(".")) throw new NumberFormatException("Starts with dot");
        if (numStr.endsWith(".")) throw new NumberFormatException("Ends with dot");
        if (numStr.contains("..")) throw new NumberFormatException("Double dot");

        return Double.parseDouble(numStr);
    }

    // calculates factorial of n
    private double factorial(int n) {
        if (n == 0) return 1;
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // valid expressions
        System.out.println("=== Valid Expressions ===");
        System.out.println("2+5!-8.3 => " + calc.evaluate("2+5!-8.3"));           // 113.7
        System.out.println("(3.5-1.5)*2 => " + calc.evaluate("(3.5-1.5)*2"));     // 4.0
        System.out.println("(2+3)!/10 => " + calc.evaluate("(2+3)!/10"));         // 12.0
        System.out.println("0!+1 => " + calc.evaluate("0!+1"));                   // 2.0
        System.out.println("10/3 => " + calc.evaluate("10/3"));                   // 3.333...
        System.out.println("5!/(3!*2!) => " + calc.evaluate("5!/(3!*2!)"));       // 10.0
        System.out.println("(2+3)*(4-1) => " + calc.evaluate("(2+3)*(4-1)"));     // 15.0
        System.out.println("5+(6/3) => " + calc.evaluate("5+(6/3)"));             // 7.0
        System.out.println("(4!-3!)*2 => " + calc.evaluate("(4!-3!)*2"));         // 36.0
        System.out.println("12/(3*2) => " + calc.evaluate("12/(3*2)"));           // 2.0

        // invalid expressions
        System.out.println("\n=== Invalid Expressions (should return -1) ===");
        System.out.println("0..2+3 => " + calc.evaluate("0..2+3"));               // -1
        System.out.println(".5+1 => " + calc.evaluate(".5+1"));                   // -1
        System.out.println("2. => " + calc.evaluate("2."));                       // -1
        System.out.println("5.2! => " + calc.evaluate("5.2!"));                   // -1
        System.out.println("(-1)! => " + calc.evaluate("(-1)!"));                 // -1
        System.out.println("13! => " + calc.evaluate("13!"));                     // -1
        System.out.println("1/0 => " + calc.evaluate("1/0"));                     // -1
        System.out.println("5- => " + calc.evaluate("5-"));                       // -1
    }
}