package polynom;

import java.util.LinkedList;
import java.util.List;

public class G2 implements DerivativeMethod {
    @Override
    public List<TermFunction> calculateDerivative(Equation equation, double h) {
        List<TermFunction> functions = new LinkedList<>();
        for (var f : equation.termFunctions)
            functions.add(x -> ((-f.calculate(x + 2 * h) + 8 * f.calculate(x + h) - 8 * f.calculate(x - h) + f.calculate(x - 2 * h)) / (12 * h)));
        return functions;
    }
}
