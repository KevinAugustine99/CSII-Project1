package functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**Sum represents the addition of multiple functions, extends function
 * @author Kevin Augustine, kea4437@rit.edu
 * @date 2/17/2019
 **/
public class Sum extends Function {
    public List<Function> sums; //the sum of functions

    /**One of the constructors for Sum
     *
     * @param terms: list of functions as terms
     */
    public Sum(List<Function> terms){
        sums = terms; //initialize sums to the list
        this.simplify(); //simplify sums
    }

    /**One of the constructors for Sum, turns the functions into a list, and calls the other constructor
     *
     * @param terms: many functions
     */
    public Sum(Function... terms){
        this(Arrays.asList(terms)); //call other constructor after making list
    }

    /**evaluate evaluates the sum, by adding the evaluations of functions in the sum
     *
     * @param parameter the value of x
     * @return the evaluation of the sum
     */
    @Override
    public double evaluate(double parameter){
        double result = 0;
        for (Function term : sums) { //for each term add their evaluation to the result
            result += term.evaluate(parameter);
        }
        return result;
    }

    /**isConstant returns if every part of the sum is a constant
     *
     * @return true: every term is constant, false: at least one term is not constant
     */
    @Override
    public boolean isConstant(){
        for(Function term: this.sums){ //for each term figure out if it is constant
            boolean result = term.isConstant();
            if(!result) return false; //if at least one term is not constant, return false
        }
        return true;
    }

    /**derivative returns the sum of the derivative of each term
     *
     * @return the sum of each term's derivative
     */
    @Override
    public Function derivative(){
        Sum derivatives = new Sum(); //Create a new sum
        for (Function term : sums) { //for each term in this sum
            if(term.isConstant()) continue; //If term is constant then the derivative is 0, so continue to next term
            derivatives.sums.add(term.derivative()); //Add the derivative of this term to the new sum
        }
        derivatives.simplify(); //Simplify the derivative
        return derivatives;
    }

    /**simplify simplifies the sum
     *
     */
    public void simplify(){
        if(sums.size() == 1) return; //If the size of the sum is one then return, because it is simplified
        List<Function> simplified_sum = new ArrayList<>(); //create a new to hold the new simplified sums
        Constant constant = new Constant(0); //Create a new 0 constant
        for(Function term : sums){ //for each term in the sum
            if(term.isConstant()){ //If the term is constant, add the value to the constant term
                constant.add(new Constant(term.evaluate()));
            }else{ //add the non constant term to the simplified sum array list
                simplified_sum.add(term);
            }
        }
        if(simplified_sum.size() == 0 || constant.evaluate() != 0){
            //If there are no values in the simplified sum or the the constant is non zero, add the constant to the simplified sum
            simplified_sum.add(constant);
        }
        sums = simplified_sum; //set sums to simplified sum
    }

    /**toString returns the string representation of the sum
     *
     * @return the string representation of the sum
     */
    @Override
    public String toString(){
        if(sums.size() == 1) return sums.get(0).toString(); //if the size of the sum is 1 then return the toString of the term
        StringBuilder result = new StringBuilder("( "); //initialize the string to "( "
        for (int i = 0; i < sums.size(); i++) { //For each term in the sum, add the toString of the term
            result.append(sums.get(i).toString());
            if(i != sums.size() - 1) result.append(" + "); //if the term isn't the last term, add a plus sign
        }
        return result + " )"; //return the result plus " )"
    }

    /**the integral of the sum is the sum of the integral of the terms.
     *
     * @param a: the first bound
     * @param b: the second bound
     * @param n: the amount of trapezoids to use when finding the integrals of more complex functions
     * @return the integral of the sum from a to b
     */
    @Override
    public double integral(double a, double b, double n) {
        double result = 0; //initialize the result to zero
        for (Function f : sums){ //Add the integrals of each term to the result
            result += f.integral(a, b, n);
        }
        return result;
    }
}
