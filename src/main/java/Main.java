import polynom.Equation;
import polynom.G1;
import polynom.SecondDerivativeMethod;

public class Main {
    public static void main(String[] args) {
        double h1 = Math.pow(10, -5);
        double h2 = Math.pow(10, -6);
        Equation equation = new Equation(0.001, 10000);
        equation.addTerm(x -> (x * x * x) / 3);
        equation.addTerm(x -> 2 * (x * x));
        equation.addTerm(x -> 2 * x);
        equation.solve(new G1(), h1, new SecondDerivativeMethod(), h1, 1, 2 + Math.sqrt(2));
        equation.displaySolutions();
    }
}
