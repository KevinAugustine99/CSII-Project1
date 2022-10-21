package functions;

/**Constant represents constant functions, extends function
 * @author Kevin Augustine, kea4437@rit.edu
 * @date 2/17/2019
 **/

public class Constant extends Function{
    public double value; // the value of the constant

    /**The constructor for the constant
     *
     * @param constant the value of the constant
     */
    public Constant(double constant) {
        value = constant;
    }

    /**add adds two constants
     *
     * @param other the other constant being added to this one
     */
    public void add(Constant other){
        this.value += other.value;
    }

    /**multiply multiplies two constants
     *
     * @param other the other constant being multiplied to this one
     */
    public void multiply(Constant other){
        this.value *= other.value;
    }

    /**toString represents the constant as a string
     *
     * @return the string representation of the constant
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**evaluate returns the value of the constant
     *
     * @param parameter the value of x
     * @return the value of the constant
     */
    @Override
    public double evaluate(double parameter) {
        return value;
    }

    /**isConstant returns true because it is a constant
     *
     * @return true
     */
    @Override
    public boolean isConstant() {
        return true;
    }

    /**derivative take the derivative of the constant
     *
     * @return the constant zero
     */
    @Override
    public Function derivative() {
        return new Constant(0);
    }

    /**integral takes the integral from a to b
     *
     * @param a: the first bound
     * @param b: the second bound
     * @param n: the amount of trapezoids to use when finding the integrals of more complex functions
     * @return the value of the integral of a constant
     */
    @Override
    public double integral(double a, double b, double n) {
        return value * (b - a);
    }
}