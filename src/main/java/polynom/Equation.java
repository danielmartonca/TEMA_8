package polynom;

import java.util.LinkedList;
import java.util.List;

public class Equation {
    private final Double eps;
    private final int k_max;
    final List<TermFunction> termFunctions;
    private List<TermFunction> firstDerivative;
    private List<TermFunction> secondDerivative;
    private final List<Double> solutions;

    public Equation(Double eps, int k_max) {
        this.k_max = k_max;
        this.termFunctions = new LinkedList<>();
        this.firstDerivative = new LinkedList<>();
        this.secondDerivative = new LinkedList<>();
        this.eps = eps;
        this.solutions = new LinkedList<>();
    }

    public void addTerm(TermFunction f) {
        this.termFunctions.add(f);
    }

    public double f(double x) {
        return termFunctions.stream().map(f -> f.calculate(x)).mapToDouble(i -> i).sum();
    }

    public double fFirstDerivative(double x) {
        return firstDerivative.stream().map(f -> f.calculate(x)).mapToDouble(i -> i).sum();
    }

    public double fSecondDerivative(double x) {
        return secondDerivative.stream().map(f -> f.calculate(x)).mapToDouble(i -> i).sum();
    }

    public void calculateFirstDerivative(DerivativeMethod method, double h) {
        this.firstDerivative = method.calculateDerivative(this, h);
    }

    public void calculateSecondDerivative(DerivativeMethod method, double h) {
        this.secondDerivative = method.calculateDerivative(this, h);
    }


    private double calculateDelta(double x) {
        return (Math.pow(fFirstDerivative(x), 2)) / (fFirstDerivative(x + fFirstDerivative(x)) - fFirstDerivative(x));
    }

    private Output calculateSol(double x) {
        int k = 0;
        double delta_x;
        do {
            var g_x = fFirstDerivative(x);
            var temp = fFirstDerivative(x + g_x);
            if (Math.abs(temp - g_x) <= eps) {
                Output output = Output.CONVERGENT;
                output.setSolution(x);
                return output;
            }
            delta_x = calculateDelta(x);
            x = x - delta_x;
            k++;
        } while (Math.abs(delta_x) <= eps && k < k_max && Math.abs(delta_x) <= Math.pow(10, 8));
        if (Math.abs(delta_x) < eps) {
            Output output = Output.CONVERGENT;
            output.setSolution(x);
            return output;
        }
        return Output.DIVERGENT;
    }

    private void filterSolutions() {
        solutions.removeIf(solution -> fSecondDerivative(solution) <= 0);
    }

    public void solve(DerivativeMethod firstDerivativeMethod, double h1, DerivativeMethod secondDerivativeMethod, double h2, double range, double... knownSolutions) {
        if (termFunctions.size() == 0) throw new IllegalArgumentException("Please insert values for terms.");
        calculateFirstDerivative(firstDerivativeMethod, h1);
        calculateSecondDerivative(secondDerivativeMethod, h2);

        if (firstDerivative.size() == 0 || secondDerivative.size() == 0)
            throw new IllegalArgumentException("Please insert values for derivatives.");
        for (var x0 : knownSolutions) {
            var x = (Math.random() * (x0 + range - x0 - range));
            var output = calculateSol(x);
            if (output == Output.CONVERGENT) solutions.add(output.getSolution());
        }
        filterSolutions();
    }


    public void displaySolutions() {
        for (var solution : solutions)
            System.out.println("Solution x=" + solution + "  f(x)=" + f(solution));
        System.out.println("\n");
    }
}
