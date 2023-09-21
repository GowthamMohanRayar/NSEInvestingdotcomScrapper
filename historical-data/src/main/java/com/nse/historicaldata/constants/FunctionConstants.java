package com.nse.historicaldata.constants;

import java.util.function.ToDoubleFunction;

public class FunctionConstants {

	public static final ToDoubleFunction<String> PARSE_DOUBLE = input -> Double
			.valueOf(input.replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING)
					.replace(NSEConstants.COMMA, NSEConstants.EMPTY_STRING));

}
