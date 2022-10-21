package functions;

/**Sine represents the sine function, extends function
 * @author Kevin Augustine, kea4437@rit.edu
 * @date 2/26/2019
 **/
public class Sine extends Function{

    public Function value; //The value in the sin function

    /**The constructor of the sine class
     *
     * @param f the value in the sine function
     */
    public Sine(Function f){
        value = f; //initialize f to the value
    }

    /**evaluate the sine function
     *
     * @param parameter the value of x
     * @return the sin of the evaluation of the value in the sine function
     */
    @Override
    public double evaluate(double parameter) {
        return Math.sin(value.evaluate(parameter));
    }

    /**isConstant returns whether this function is constant
     *
     * @return true: if the value in the sine function is a constant, false if not
     */
    @Override
    public boolean isConstant() {
        return value.isConstant();
    }

    /**derivative returns the derivative of the sine function
     *
     * @return a product between the derivative of the value and the cosine of the value
     */
    @Override
    public Function derivative() {
        return new Product( value.derivative(), new Cosine(value));
    }

    /**toString returns the string representation of the sine function
     *
     * @return the string representation of the sine function
     */
    @Override
    public String toString(){
        return "sin( " + value.toString() + " )";
    }
}
