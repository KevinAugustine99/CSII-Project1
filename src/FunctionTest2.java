import functions.*;
import java.util.*;

/**FunctionTest2 tests all functions
 * @author Kevin Augustine, kea4437@rit.edu
 * @date 3/7/2019
 **/
public class FunctionTest2 {

    public static void main(String[] args) {
        if ( args.length != 0 ) {
            System.err.println( "FunctionTest1 takes no command line arguments." );
            System.exit( 1 );
        }

        //Create functions
        Function x = Variable.X;
        Function function1 = new Product(new Constant(5), new Constant(5));
        Function function2 = new Product(new Constant(25), x );
        Function function3 = new Product(function1, x);
        Function function4 = new Product(x , x);
        Function function5 = new Product(x, x, x);
        Function function6 = new Product(function4, x);
        Function function7 = new Product(new Sum(x, new Constant(1)), new Sum(x, new Constant(1)));
        Function function8 = new Sine(x);
        Function function9 = new Cosine(x);
        Function function10 = new Product(function8 , function8);
        Function function11 = new Sum(function8, function8);
        Function function12 = new Sine(function4);
        Function function13 = new Sine(new Sum(x,x));
        Function function14 = new Sine(function1);
        Function function15 = new Sine(new Sum(new Constant(1), new Constant(24)));
        Function function16 = new Cosine(new Sine(x));
        Function function17 = new Sum(new Product(new Constant(3),x,x,x), new Product(new Constant(2),x,x),new Product(new Constant(1), x), new Constant(1));

        //Create list of functions
        List<Function> functions = Arrays.asList(
                function1,
                function2,
                function3,
                function4,
                function5,
                function6,
                function7,
                function8,
                function9,
                function10,
                function11,
                function12,
                function13,
                function14,
                function15,
                function16,
                function17
        );

        //Create list of whether the function is a constant
        List<Boolean> constants = Arrays.asList(
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                false,
                false
        );

        //Create list of toStrings
        List<String> toStrings = Arrays.asList(
                "25.0",
                "( x * 25.0 )",
                "( x * 25.0 )",
                "( x * x )",
                "( x * x * x )",
                "( ( x * x ) * x )",
                "( ( x + 1.0 ) * ( x + 1.0 ) )",
                "sin( x )",
                "cos( x )",
                "( sin( x ) * sin( x ) )",
                "( sin( x ) + sin( x ) )",
                "sin( ( x * x ) )",
                "sin( ( x + x ) )",
                "sin( 25.0 )",
                "sin( 25.0 )",
                "cos( sin( x ) )",
                "( ( x * x * x * 3.0 ) + ( x * x * 2.0 ) + x + 1.0 )"
        );

        //Create list of evaluations
        List<Double> evaluations = Arrays.asList(
                25.0,
                250.0,
                250.0,
                100.0,
                1000.0,
                1000.0,
                121.0,
                0.0,
                -1.0,
                0.0,
                0.0,
                -0.4303012170000917,
                0.0,
                -0.13235175009777303,
                -0.13235175009777303,
                1.0,
                3211.0
        );

        //Create list of evaluation parameters
        List<Double> parameters = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            parameters.add(10.0);
        }
        for (int i = 8; i < 17; i++) {
            parameters.add(Math.PI);
        }
        parameters.add(10.0);

        //Create list of derivative strings
        List<String> derivatives = Arrays.asList(
                "0.0",
                "25.0",
                "25.0",
                "( x + x )",
                "( ( x * x ) + ( x * x ) + ( x * x ) )",
                "( ( x * ( x + x ) ) + ( x * x ) )",
                "( ( x + 1.0 ) + ( x + 1.0 ) )",
                "cos( x )",
                "( sin( x ) * -1.0 )",
                "( ( sin( x ) * cos( x ) ) + ( sin( x ) * cos( x ) ) )",
                "( cos( x ) + cos( x ) )",
                "( ( x + x ) * cos( ( x * x ) ) )",
                "( cos( ( x + x ) ) * 2.0 )",
                "0.0",
                "0.0",
                "( cos( x ) * sin( sin( x ) ) * -1.0 )",
                "( ( ( x * x * 3.0 ) + ( x * x * 3.0 ) + ( x * x * 3.0 ) ) + ( ( x * 2.0 ) + ( x * 2.0 ) ) + 1.0 )"
        );

        //Create list of integral values
        List<Double> integrals_1_5 = Arrays.asList(
                100.0,
                300.0,
                300.0,
                124.0/3,
                156.0,
                156.0,
                208.0/3,
                0.2566,
                -1.8003,
                2.3633,
                0.5132,
                0.2176,
                0.2112,
                -0.5294,
                -0.5294,
                2.8958,
                1700.0/3
        );

        //Test
        FunctionTest1.test(functions,toStrings,evaluations,derivatives,integrals_1_5, constants,parameters);
    }
}
// OUTPUT
/*
function1: verify toString = 25.0 ? OK
function1: verify isConstant = true ? OK
function1: verify evaluation where the parameter is 10.0 = 25.0 ? OK
function1: verify derivative = 0.0 ? OK
function1: verify integral from 1 to 5 = 100.0 ? OK


function2: verify toString = ( x * 25.0 ) ? OK
function2: verify isConstant = false ? OK
function2: verify evaluation where the parameter is 10.0 = 250.0 ? OK
function2: verify derivative = 25.0 ? OK
function2: verify integral from 1 to 5 = 300.0 ? OK


function3: verify toString = ( x * 25.0 ) ? OK
function3: verify isConstant = false ? OK
function3: verify evaluation where the parameter is 10.0 = 250.0 ? OK
function3: verify derivative = 25.0 ? OK
function3: verify integral from 1 to 5 = 300.0 ? OK


function4: verify toString = ( x * x ) ? OK
function4: verify isConstant = false ? OK
function4: verify evaluation where the parameter is 10.0 = 100.0 ? OK
function4: verify derivative = ( x + x ) ? OK
function4: verify integral from 1 to 5 = 41.333333333333336 ? OK


function5: verify toString = ( x * x * x ) ? OK
function5: verify isConstant = false ? OK
function5: verify evaluation where the parameter is 10.0 = 1000.0 ? OK
function5: verify derivative = ( ( x * x ) + ( x * x ) + ( x * x ) ) ? OK
function5: verify integral from 1 to 5 = 156.0 ? OK


function6: verify toString = ( ( x * x ) * x ) ? OK
function6: verify isConstant = false ? OK
function6: verify evaluation where the parameter is 10.0 = 1000.0 ? OK
function6: verify derivative = ( ( x * ( x + x ) ) + ( x * x ) ) ? OK
function6: verify integral from 1 to 5 = 156.0 ? OK


function7: verify toString = ( ( x + 1.0 ) * ( x + 1.0 ) ) ? OK
function7: verify isConstant = false ? OK
function7: verify evaluation where the parameter is 10.0 = 121.0 ? OK
function7: verify derivative = ( ( x + 1.0 ) + ( x + 1.0 ) ) ? OK
function7: verify integral from 1 to 5 = 69.33333333333333 ? OK


function8: verify toString = sin( x ) ? OK
function8: verify isConstant = false ? OK
function8: verify evaluation where the parameter is 3.141592653589793 = 0.0 ? OK
function8: verify derivative = cos( x ) ? OK
function8: verify integral from 1 to 5 = 0.2566 ? OK


function9: verify toString = cos( x ) ? OK
function9: verify isConstant = false ? OK
function9: verify evaluation where the parameter is 3.141592653589793 = -1.0 ? OK
function9: verify derivative = ( sin( x ) * -1.0 ) ? OK
function9: verify integral from 1 to 5 = -1.8003 ? OK


function10: verify toString = ( sin( x ) * sin( x ) ) ? OK
function10: verify isConstant = false ? OK
function10: verify evaluation where the parameter is 3.141592653589793 = 0.0 ? OK
function10: verify derivative = ( ( sin( x ) * cos( x ) ) + ( sin( x ) * cos( x ) ) ) ? OK
function10: verify integral from 1 to 5 = 2.3633 ? OK


function11: verify toString = ( sin( x ) + sin( x ) ) ? OK
function11: verify isConstant = false ? OK
function11: verify evaluation where the parameter is 3.141592653589793 = 0.0 ? OK
function11: verify derivative = ( cos( x ) + cos( x ) ) ? OK
function11: verify integral from 1 to 5 = 0.5132 ? OK


function12: verify toString = sin( ( x * x ) ) ? OK
function12: verify isConstant = false ? OK
function12: verify evaluation where the parameter is 3.141592653589793 = -0.4303012170000917 ? OK
function12: verify derivative = ( ( x + x ) * cos( ( x * x ) ) ) ? OK
function12: verify integral from 1 to 5 = 0.2176 ? OK


function13: verify toString = sin( ( x + x ) ) ? OK
function13: verify isConstant = false ? OK
function13: verify evaluation where the parameter is 3.141592653589793 = 0.0 ? OK
function13: verify derivative = ( cos( ( x + x ) ) * 2.0 ) ? OK
function13: verify integral from 1 to 5 = 0.2112 ? OK


function14: verify toString = sin( 25.0 ) ? OK
function14: verify isConstant = true ? OK
function14: verify evaluation where the parameter is 3.141592653589793 = -0.13235175009777303 ? OK
function14: verify derivative = 0.0 ? OK
function14: verify integral from 1 to 5 = -0.5294 ? OK


function15: verify toString = sin( 25.0 ) ? OK
function15: verify isConstant = true ? OK
function15: verify evaluation where the parameter is 3.141592653589793 = -0.13235175009777303 ? OK
function15: verify derivative = 0.0 ? OK
function15: verify integral from 1 to 5 = -0.5294 ? OK


function16: verify toString = cos( sin( x ) ) ? OK
function16: verify isConstant = false ? OK
function16: verify evaluation where the parameter is 3.141592653589793 = 1.0 ? OK
function16: verify derivative = ( cos( x ) * sin( sin( x ) ) * -1.0 ) ? OK
function16: verify integral from 1 to 5 = 2.8958 ? OK


function17: verify toString = ( ( x * x * x * 3.0 ) + ( x * x * 2.0 ) + x + 1.0 ) ? OK
function17: verify isConstant = false ? OK
function17: verify evaluation where the parameter is 10.0 = 3211.0 ? OK
function17: verify derivative = ( ( ( x * x * 3.0 ) + ( x * x * 3.0 ) + ( x * x * 3.0 ) ) + ( ( x * 2.0 ) + ( x * 2.0 ) ) + 1.0 ) ? OK
function17: verify integral from 1 to 5 = 566.6666666666666 ? OK
 */