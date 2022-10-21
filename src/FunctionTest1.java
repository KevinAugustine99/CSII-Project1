import functions.*;
import java.util.*;

/** FunctionTest1 tested part one, and holds the test function
 * @author Kevin Augustine, kea4437@rit.edu
 * @date 3/3/2019
 **/
public class FunctionTest1 {

    /**test tests all of the functions
     *
     * @param functions list of functions
     * @param toStrings list of toStrings
     * @param evaluations list of evaluation values
     * @param derivatives list of derivative values
     * @param integrals list of integral values
     * @param constants list of boolean values indicating if the respective function is a constant
     * @param evaluation_parameters list of parameters for the evaluation
     */
    public static void test(List<Function> functions, List<String> toStrings,
                            List<Double> evaluations, List<String> derivatives,
                            List<Double> integrals, List<Boolean> constants,
                            List<Double> evaluation_parameters) {

        for (int i = 0; i < functions.size(); i++) { //iterate over each function
            String name = "function" + (i + 1); //create name
            Function function = functions.get(i); //get function
            String toString = toStrings.get(i); //get toString
            Double evaluation = evaluations.get(i); //get evaluation
            String derivative = derivatives.get(i); //get derivative string
            Double integral = integrals.get(i); //get integral value
            Boolean constant = constants.get(i); // get boolean value for is Constant
            Double parameter = evaluation_parameters.get(i); //get evaluation parameter

            //Test toString
            System.out.println(name + ": verify toString = " + toString + " ? " + (function.toString().equals(toString) ?
                    "OK" : "FAIL, got: " + function.toString()));

            //Test isConstant
            System.out.println(name + ": verify isConstant = " + constant + " ? " + (function.isConstant() == constant ?
                    "OK" : "FAIL, got: " + function.isConstant()));

            //Test evaluation
            double difference = function.evaluate(parameter) - evaluation; //use difference to take into account of double variety
            System.out.println(name + ": verify evaluation where the parameter is " + parameter + " = " + evaluation + " ? " + (-0.00005 < difference && difference < 0.00005 ?
                    "OK" : "FAIL, got: " + function.evaluate(parameter)));

            //Test derivative
            System.out.println(name + ": verify derivative = " + derivative + " ? " + (function.derivative().toString().equals(derivative) ?
                    "OK" : "FAIL, got: " + function.derivative()));

            //Test integral, use difference to take into account the variety of doubles
            double integral_difference = function.integral(1, 5, 10000) - (integral);
            System.out.println(name + ": verify integral from 1 to 5 = " + integral + " ? " + (-0.5 < integral_difference && integral_difference < 0.5 ?
                    "OK" : "FAIL, got: " + function.integral(1, 5, 10000)));

            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        if ( args.length != 0 ) {
            System.err.println( "FunctionTest1 takes no command line arguments." );
            System.exit( 1 );
        }

        //create functions
        Function constant_0 = new Constant(0);
        Function constant_1 = new Constant(1);
        Function constant_24 = new Constant(24);
        Function x = Variable.X;
        Function function4 = new Sum( constant_0, constant_0, constant_0);
        Function function5 = new Sum(constant_1,constant_1, constant_1);
        Function function6 = new Sum(constant_0, constant_1, new Constant(2), new Constant(3));
        Function function7 = new Sum(x);
        Function function8 = new Sum(x,constant_24,constant_1);
        Function function9 = new Sum(x, constant_1, x, constant_24);
        Function function10 = new Sum(new Sum(x,constant_1), new Sum(x ,constant_24));

        //create list of functions
        List<Function> functions = Arrays.asList(
                constant_1,
                constant_24,
                x,
                function4,
                function5,
                function6,
                function7,
                function8,
                function9,
                function10
        );

        //Create list of whether the function is a constant
        List<Boolean> constants = Arrays.asList(
                true,
                true,
                false,
                true,
                true,
                true,
                false,
                false,
                false,
                false
        );

        //Create list of toStrings
        List<String> toStrings = Arrays.asList(
                "1.0",
                "24.0",
                "x",
                "0.0",
                "3.0",
                "6.0",
                "x",
                "( x + 25.0 )",
                "( x + x + 25.0 )",
                "( ( x + 1.0 ) + ( x + 24.0 ) )"
        );

        //Create list of evaluations
        List<Double> evaluations = Arrays.asList(
                1.0,
                24.0,
                20.0,
                0.0,
                3.0,
                6.0,
                20.0,
                45.0,
                65.0,
                65.0
        );

        //Create list of evaluation parameters
        List<Double> evaluation_parameter = new ArrayList<>(Collections.nCopies(10, 20.0));

        //Create list of derivative strings
        List<String> derivatives = Arrays.asList(
                "0.0",
                "0.0",
                "1.0",
                "0.0",
                "0.0",
                "0.0",
                "1.0",
                "1.0",
                "2.0",
                "2.0"
        );

        //Create list of integral values
        List<Double> integrals_1_5 = Arrays.asList(
                (5.0-1.0),
                (24.0*(5.0-1.0)),
                (12.0),
                0.0,
                (3.0*(5.0-1.0)),
                (6.0*(5.0-1.0)),
                (12.0),
                112.0,
                124.0,
                124.0
        );

        //Test
        test(functions,toStrings,evaluations,derivatives,integrals_1_5, constants, evaluation_parameter);

    }
}
// OUTPUT
/*
function1: verify toString = 1.0 ? OK
function1: verify isConstant = true ? OK
function1: verify evaluation where the parameter is 20.0 = 1.0 ? OK
function1: verify derivative = 0.0 ? OK
function1: verify integral from 1 to 5 = 4.0 ? OK


function2: verify toString = 24.0 ? OK
function2: verify isConstant = true ? OK
function2: verify evaluation where the parameter is 20.0 = 24.0 ? OK
function2: verify derivative = 0.0 ? OK
function2: verify integral from 1 to 5 = 96.0 ? OK


function3: verify toString = x ? OK
function3: verify isConstant = false ? OK
function3: verify evaluation where the parameter is 20.0 = 20.0 ? OK
function3: verify derivative = 1.0 ? OK
function3: verify integral from 1 to 5 = 12.0 ? OK


function4: verify toString = 0.0 ? OK
function4: verify isConstant = true ? OK
function4: verify evaluation where the parameter is 20.0 = 0.0 ? OK
function4: verify derivative = 0.0 ? OK
function4: verify integral from 1 to 5 = 0.0 ? OK


function5: verify toString = 3.0 ? OK
function5: verify isConstant = true ? OK
function5: verify evaluation where the parameter is 20.0 = 3.0 ? OK
function5: verify derivative = 0.0 ? OK
function5: verify integral from 1 to 5 = 12.0 ? OK


function6: verify toString = 6.0 ? OK
function6: verify isConstant = true ? OK
function6: verify evaluation where the parameter is 20.0 = 6.0 ? OK
function6: verify derivative = 0.0 ? OK
function6: verify integral from 1 to 5 = 24.0 ? OK


function7: verify toString = x ? OK
function7: verify isConstant = false ? OK
function7: verify evaluation where the parameter is 20.0 = 20.0 ? OK
function7: verify derivative = 1.0 ? OK
function7: verify integral from 1 to 5 = 12.0 ? OK


function8: verify toString = ( x + 25.0 ) ? OK
function8: verify isConstant = false ? OK
function8: verify evaluation where the parameter is 20.0 = 45.0 ? OK
function8: verify derivative = 1.0 ? OK
function8: verify integral from 1 to 5 = 112.0 ? OK


function9: verify toString = ( x + x + 25.0 ) ? OK
function9: verify isConstant = false ? OK
function9: verify evaluation where the parameter is 20.0 = 65.0 ? OK
function9: verify derivative = 2.0 ? OK
function9: verify integral from 1 to 5 = 124.0 ? OK


function10: verify toString = ( ( x + 1.0 ) + ( x + 24.0 ) ) ? OK
function10: verify isConstant = false ? OK
function10: verify evaluation where the parameter is 20.0 = 65.0 ? OK
function10: verify derivative = 2.0 ? OK
function10: verify integral from 1 to 5 = 124.0 ? OK
 */