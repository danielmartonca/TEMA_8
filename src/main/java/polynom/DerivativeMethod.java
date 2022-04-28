package polynom;

import java.util.List;

public interface DerivativeMethod {
    List<TermFunction> calculateDerivative(Equation equation, double h);
}
