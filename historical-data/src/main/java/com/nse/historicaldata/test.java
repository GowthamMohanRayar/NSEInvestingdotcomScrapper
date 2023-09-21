package com.nse.historicaldata;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.historicaldata.constants.ConsumerConstants;
import com.nse.historicaldata.utils.NSEFileUtils;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class test {
	public static void main(String[] args) {

		for (String ipo : NSEFileUtils.readFromFilePutContentToList("", Arrays.asList("ipoNames.txt"), null,
				ConsumerConstants.GENERIC_FILE_EXIT_OPERATION)) {
			String response = callBackend(ipo);
			if ("" != response && response.contains("suggest1([") && response.contains("])")) {
//				System.out.println(response.replace("suggest1([", "{\"" + ipo.toUpperCase() + "\":").replace("])", "}"));
				try {
					String jsonString = response.replace("suggest1(", "{\"" + ipo.toUpperCase() + "\":").replace(")", "}");
					JSONObject obj = new JSONObject(jsonString);
					JSONArray pageName = obj.getJSONArray(ipo.toUpperCase());
					obj = (JSONObject) pageName.get(0);
					System.out.println(ipo.toUpperCase() + " " + obj.getString("link_src"));
				} catch (JSONException e) {
					System.out.println(ipo.toUpperCase());
				}
			}
		}

	}

	private static String callBackend(String ipo) {
		String response = "";
		Mono<String> customer;
		try {
			customer = getWebClient(WebClient.builder()).get()
					.uri("https://www.moneycontrol.com/mccode/common/autosuggestion_solr.php?classic=true&query=" + ipo
							+ "&type=1&format=json&callback=suggest1")
					.header(HttpHeaders.ACCEPT, "application/json").retrieve().bodyToMono(String.class);
			response = customer.block();
		} catch (Exception e) {
		}
		return response;
	}

	public static WebClient getWebClient(WebClient.Builder webClientBuilder) {

		final int size = 16 * 1024 * 1024;
		final ExchangeStrategies strategies = ExchangeStrategies.builder()
				.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size)).build();

		return webClientBuilder.clientConnector(new ReactorClientHttpConnector(getHttpClient()))
				.exchangeStrategies(strategies).build();
	}

	private static HttpClient getHttpClient() {
		return HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000).doOnConnected(
				conn -> conn.addHandlerLast(new ReadTimeoutHandler(10)).addHandlerLast(new WriteTimeoutHandler(10)));
	}
}
