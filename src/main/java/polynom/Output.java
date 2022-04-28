package polynom;

public enum Output {
    CONVERGENT(null), DIVERGENT("Divergent. Try a different x0 starting point."), MAX_ITERATIONS_REACHED("The maximum number of iterations was reached. Try to increase the number of iterations or modify epsilon.");
    private String message;

    Output(String message) {
        this.message = message;
    }

    public void display() {
        System.out.println(message);
        System.out.flush();
    }


    private Double solution = null;

    public void setSolution(double solution) {
        this.solution = solution;
        this.message = "Convergent. Solution is '" + solution + "'.";
    }

    public double getSolution() {
        return solution;
    }
}
