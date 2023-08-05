package com.nse.historicaldata.constants;

import java.util.function.ToIntFunction;

public class FunctionConstants {

	public static final ToIntFunction<String> PARSE_INT = input -> Integer
			.valueOf(input.replace(NSEConstants.DOUBLE_QUOTES, NSEConstants.EMPTY_STRING)
					.replace(NSEConstants.COMMA, NSEConstants.EMPTY_STRING).split(NSEConstants.DOT)[0]);

}
