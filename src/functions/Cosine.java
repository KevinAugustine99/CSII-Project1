package functions;

/**Cosine represents the cosine function, extends function
 * @author Kevin Augustine, kea4437@rit.edu
 * @date 2/26/2019
 **/
public class Cosine extends Function {
    public Function value; //the value of the cosine function

    /**the constructor method for the cosine function
     *
     * @param f the value in the cosine function
     */
    public Cosine(Function f){
        value = f; //initialize the value to f
    }

    /**evaluate evaluates the cosine function
     *
     * @param parameter the value of x
     * @return the cosine of the evaluation of the value
     */
    @Override
    public double evaluate(double parameter) {
        return Math.cos(value.evaluate(parameter));
    }

    /**isConstant returns whether the function is constant
     *
     * @return whether the value is constant, true if is, false if not
     */
    @Override
    public boolean isConstant() {
        return value.isConstant();
    }

    /**derivative returns the derivative of cosine function
     *
     * @return a new product of the derivative of the value, a new constant of -1, and a new Sine function with the same value
     */
    @Override
    public Function derivative() {
        return new Product(value.derivative(), new Sine(value) , new Constant(-1) );
    }

    /**toString returns the string representation of the cosine function
     *
     * @return the string representation of the cosine function
     */
    @Override
    public String toString(){
        return "cos( " + value.toString() + " )";
    }
}
