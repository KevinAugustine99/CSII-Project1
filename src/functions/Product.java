package functions;

import java.util.*;

/**Product represents a product of multiple terms, extends function
 * @author Kevin Augustine, kea4437@rit.edu
 * @date 2/18/2019
 **/
public class Product extends Function {
    public List<Function> products; //the list of products

    /**One of the constructors of Product that takes a list of terms
     *
     * @param terms: list of terms
     */
    public Product(List<Function> terms){
        products = terms;
        this.simplify();
    }

    /**One of the constructors for Product, which takes in many Functions
     *
     * @param terms: the many functions used as parameters for product
     */
    public Product(Function... terms){
        this(Arrays.asList(terms)); //Call other constructor
    }

    /**evaluate the product
     *
     * @param parameter the value of x
     * @return the value of the product
     */
    @Override
    public double evaluate(double parameter){
        double result = 1; //initialize the result to 1
        for (Function term : products) { //for each term in the product multiply the evaluation of the term to the result
            result *= term.evaluate(parameter);
        }
        return result;
    }

    /**isConstant returns whether a product is a constant
     *
     * @return if a single term is a not a constant, return false, else return true
     */
    @Override
    public boolean isConstant(){
        for(Function term: this.products){ //iterate over each term in the product
            boolean result = term.isConstant(); //get the isConstant of the term
            if(!result) return false; //if the term is not constant, return false
        }
        return true;
    }

    /**derivative returns the derivative of the function
     *
     * @return a function that is the derivative of the product
     */
    @Override
    public Function derivative() {
        if(isConstant()) return new Constant(0); //if the product is a constant, return the zero constant
        if(products.size() == 1) return products.get(0).derivative(); //if the the size of the product is one, return the derivative of the only item in the product
        Sum derivative = new Sum(); //initialize a new sum
        for(Function term: products){ //iterate over each term in the product
            Product rest = new Product(this.products); //create a new Product with the same terms
            rest.products.remove(term); //remove this current term
            rest.products.add(term.derivative()); //add the derivative of the term back into the the product
            rest.simplify(); //simplify
            derivative.sums.add(rest); //add it to the sum of derivatives
        }
        derivative.simplify(); //simplify the derivative
        return derivative;
    }

//    This is the other method of taking the derivatives of Products
//    My way is faster, so I kept this one in but if it is easier to grade, I also included this method
//    @Override
//    public Function derivative() {
//        if(isConstant()) return new Constant(0);
//        if (products.size() == 1) return products.get(0).derivative();
//        Function term1 = products.get(0);
//        Function rest = new Product(products.subList(1, products.size()));
//        Product derivative1 = new Product(term1, rest.derivative());
//        Product derivative2 = new Product(term1.derivative(), rest);
//        return new Sum(derivative1, derivative2);
//    }

    /**simplify simplifies products
     *
     */
    public void simplify(){
        if(products.size() == 1) return; //if size of product is 0 then return, because already simplified
        List<Function> simplified_product = new ArrayList<>(); //initialize simplified product
        Constant constant = new Constant(1); //create constant
        for(Function term : products){ //iterate over each term
            if(term.isConstant()){ //if the term is a constant, multiply its evaluation to the constant
                constant.multiply(new Constant(term.evaluate()));
            }else{ //if it isn't a constant, add the term to the product
                simplified_product.add(term);
            }
        }
        double evaluation = constant.evaluate();
        if(evaluation == 0){ //if the constant is 0 then clear the product and set the product to 0
            simplified_product.clear();
            simplified_product.add(new Constant(0));
        } else if(simplified_product.size() == 0 || evaluation != 1){
            //if the size of the product or the evaluation is not 1, add the constant to the product
            simplified_product.add(constant);
        }
        products = simplified_product;
    }

    /**toString returns a string representation of the product
     *
     * @return the string representation of the product
     */
    @Override
    public String toString(){
        if(products.size() == 1) return products.get(0).toString(); //if the product size is 1, return the only term's toString
        StringBuilder result = new StringBuilder("( "); //Initialize the string
        for (int i = 0; i < products.size(); i++) { //for each term in the product
            result.append(products.get(i).toString()); //append the terms to string
            if(i != products.size() - 1) result.append(" * ");  //if not the last term then add the * symbol
        }
        return result + " )";
    }
}
