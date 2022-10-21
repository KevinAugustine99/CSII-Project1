package functions;

/**Function is the abstract class for all functions
 * @author Kevin Augustine, kea4437@rit.edu
 * @date 2/17/2019
 **/
public abstract class Function {

    /**evaluate with no paramater evaluates this function using 0 as its parameter
     *
     * @return the value of the function after its evaluated
     */
    public double evaluate(){
        return this.evaluate(0);
    }

    /**evaluate with a parameter evaluates this function using the parameter as the value of x
     *
     * @param parameter the value of x
     * @return the value of the function after it is evaluated
     */
    public abstract double  evaluate(double parameter);

    /**isConstant checks whether a function is constant
     *
     * @return true if the function is constant, false if the function isn't constant
     */
    public abstract boolean isConstant();

    /**derivative returns the derivative of the function
     *
     * @return the function representing the derivative of this function
     */
    public abstract Function derivative();

    /**integral takes a closed integral from a to b using trapezoidal rule on more complex functions
     *
     * @param a: the first bound
     * @param b: the second bound
     * @param n: the amount of trapezoids to use when finding the integrals of more complex functions
     * @return: the integral from a to b of this function
     */
    public double integral(double a, double b, double n){
        //Use the definition of the trapezoidal rule to calculate the integral
        if(a > b){
            double temp = a;
            a = b;
            b = temp;
        }
        double result = (b - a) / (2*n);
        double sum = this.evaluate(a) + this.evaluate(b);
        double change = (b-a)/n;
        for (double i = a + change ; i < b; i += change) {
            sum += 2 * this.evaluate(i);
        }
        return result * sum;
    }
}