package polynom;

import java.util.LinkedList;
import java.util.List;

public class G1 implements DerivativeMethod {
    @Override
    public List<TermFunction> calculateDerivative(Equation equation, double h) {
        List<TermFunction> functions = new LinkedList<>();
        for (var f : equation.termFunctions)
            functions.add(x -> ((3 * f.calculate(x) + 3 * f.calculate(x - h) + f.calculate(x - 2 * h)) / (2 * h)));
        return functions;
    }
}
