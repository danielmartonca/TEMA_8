package polynom;

import java.util.LinkedList;
import java.util.List;

public class SecondDerivativeMethod implements DerivativeMethod {
    @Override
    public List<TermFunction> calculateDerivative(Equation equation, double h) {
        List<TermFunction> functions = new LinkedList<>();
        for (var f : equation.termFunctions)
            functions.add(x -> ((-f.calculate(x + 2 * h) + 16 * f.calculate(x + h) - 30 * f.calculate(x) + 16 * f.calculate(x - h) - f.calculate(x - 2 * h)) / (12 * h * h)));
        return functions;
    }
}
