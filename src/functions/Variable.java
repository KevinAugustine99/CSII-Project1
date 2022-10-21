package functions;

/**Variable represents the variable x, extends Function
 * @author Kevin Augustine, kea4437@rit.edu
 * @date 2/17/2019
 **/
public class Variable extends Function{
    public final static Variable X = new Variable(); //the only variable instance

    /**Constructor method for Variable
     * This method is private to make sure that the Variable X is the only variable constructed
     */
    private Variable(){}

    /**toString returns the string representation of the variable
     *
     * @return "x"
     */
    @Override
    public String toString(){
        return "x";
    }

    /**evaluate evaluates the varaible
     *
     * @param parameter the value of x
     * @return the parameter since it is the value of x
     */
    @Override
    public double evaluate(double parameter) {
        return parameter;
    }

    /**isConstant returns whether the variable is constant
     *
     * @return false since x is not a constant
     */
    @Override
    public boolean isConstant() {
        return false;
    }

    /**derivative returns the derivative of the variable
     *
     * @return the Constant: 1
     */
    @Override
    public Function derivative() {
        return new Constant(1);
    }

    /**integral takes the integral of the variable from a to b
     *
     * @param a: the first bound
     * @param b: the second bound
     * @param n: the amount of trapezoids to use when finding the integrals of more complex functions
     * @return (the upper bound squared - the lower bound squared) / 2
     */
    @Override
    public double integral(double a, double b, double n) {
        return ( (b*b)- (a*a) ) / 2;
    }
}
