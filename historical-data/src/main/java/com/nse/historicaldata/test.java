package com.nse.historicaldata;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class test {
	public static void main(String[] args) {

			 Mono<String> customer = getWebClient(WebClient.builder()).get()
			     .uri("https://in.investing.com/equities/zensar-technologies")
			     .header(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
			     .header("cookie", "page_equity_viewed=0; adBlockerNewUserDomains=1683446183; udid=c2229206868b40893601f4f695b8355e; protectedMedia=2; g_state={\"i_l\":0}; reg_to_wl=yes; _fbp=fb.1.1683446223657.619931346; _cc_id=63c054f3bb0c4f729327ec6cd530fbd1; _hjSessionUser_174945=eyJpZCI6IjA3OTRiZDY2LWRkMzQtNTBkOC1iZTA3LTA2OGVhNjM5OTliNyIsImNyZWF0ZWQiOjE2ODM0NjMwMjU2OTksImV4aXN0aW5nIjp0cnVlfQ==; welcomePopup=1; _pbjs_userid_consent_data=3524755945110770; _lr_env_src_ats=false; _ga_C4NDLGKVMK=deleted; isUserNoticedNewAlertPopup=1; finbox-visitor-id=v-YSEpM4R0uw6j3rXHJ4VRv; im_sharedid=f6d8e75b-a866-478b-a379-34ba1a593282; _parrable_id=filteredUntil%253A1690476003%252CfilterHits%253A0; r_p_s_n=1; pms={\"f\":2,\"s\":2}; upa=eyJpbnZfcHJvX2Z1bm5lbCI6IjMiLCJtYWluX2FjIjoiNCIsIm1haW5fc2VnbWVudCI6IjIiLCJkaXNwbGF5X3JmbSI6IjExMiIsImFmZmluaXR5X3Njb3JlX2FjX2VxdWl0aWVzIjoiOSIsImFmZmluaXR5X3Njb3JlX2FjX2NyeXB0b2N1cnJlbmNpZXMiOiIxIiwiYWZmaW5pdHlfc2NvcmVfYWNfY3VycmVuY2llcyI6IjEiLCJhY3RpdmVfb25faW9zX2FwcCI6IjAiLCJhY3RpdmVfb25fYW5kcm9pZF9hcHAiOiIxIiwiYWN0aXZlX29uX3dlYiI6IjEiLCJpbnZfcHJvX3VzZXJfc2NvcmUiOiIxMDAifQ%3D%3D; comment_notification_249393870=1; Adsfree_conversion_score=2; adsFreeSalePopUp5ffce54ff05a2ee95a83801e3446cf1b=1; gtmFired=OK; _gid=GA1.2.1816694462.1690943964; _imhb_exp_udid=a; panoramaId_expiry=1691030369860; cto_bundle=qnbsm19salFXUjFiY1l5VEs2OWhzUHFDVmRxUHNCS0hEMnJ1eEtnVTlFenVXYVhiVjJwN0w0SUhjUTBxRkFtOXloJTJCNmdXZjdpVVVTWkhjRVNSSzNMNzhPZmxoeVMxY2FEYVpwTkEzOWVLWTdDZGVNSGtwOVBBdEYlMkJ0YkVmaTFtZmI4YWdpNEV5Y3RSYmRlSXZ4TXN6WmtYNFFRJTNEJTNE; _ga_9XQG87E8PF=GS1.2.1690948037.12.0.1690948037.0.0.0; PHPSESSID=f34u0v999rumamo0lutk9d6cpu; smd=c2229206868b40893601f4f695b8355e-1690981787; __cflb=0H28vS6A9ipBeEbgf2sHmZKfA5h8RM4f4xed84T5yV5; geoC=IN; browser-session-counted=true; user-browser-sessions=28; page_view_count=1; lifetime_page_view_count=30; _tz_id=b9fe01f8ea06018a048b30c850107daf; _lr_retry_request=true; pbjs-unifiedid=%7B%22TDID%22%3A%220043b4a0-0e60-4e1f-b41a-7f6423d9c5eb%22%2C%22TDID_LOOKUP%22%3A%22TRUE%22%2C%22TDID_CREATED_AT%22%3A%222023-07-02T13%3A11%3A04%22%7D; pbjs-unifiedid_last=Wed%2C%2002%20Aug%202023%2013%3A11%3A04%20GMT; __cf_bm=AcRD5pV7SFZ52cBhXXchtk1WRDFrWFtYm_rPjYwzMlQ-1690983056-0-AaQ3C6ZAaXolOiAoBvsDsxzybJXwerSYROGyIJ46PpZFH5Uku9uj1H/VAi1ZTD2tvbLXXo+sWpEDqvJKErbmm7o=; __gads=ID=9912411033be79ac-22b2366e9fe0009d:T=1683462968:RT=1690983386:S=ALNI_MYo7hklWYcCwW132q2ooYLEJb3-sA; __gpi=UID=00000c030a9be6fb:T=1683462968:RT=1690983386:S=ALNI_MYVsraMVPvotAqVejw5C0dclX1reQ; invpc=4; _ga_C4NDLGKVMK=GS1.1.1690981794.92.1.1690983395.60.0.0; _ga=GA1.2.713615349.1683446186; outbrain_cid_fetch=true; ses_id=ZykwcTE%2BYWk1cWttZTRlYzJqYjpjbTAxYWhgZGBnZ3EyJmRqYjVjJTI9O3VhYmF9P2s1YDdpMmRhamJnZ2Y1Ymc3MGYxNGFvNTZrMWU3ZWQyMGJoY2QwNmExYGRgNmc5MjBkYGIxY2YyMDtiYTlhaz8tNSk3czIjYTNiMmcmNXJnaDBxMWFhOTUwazZlM2U0MmBiOmM3MGNhZmAxYGJnfzJ5; nyxDorf=OT5iNTJkNG03az4wZzQ1NmU%2BNjhkYzU2N2dlbGYyZTxkajZhNDg%2FbmY0YWs3OGFlY2oyZ2QzZWFgaTduMWVjYDk7YjsyZTQ6N2A%2BNQ%3D%3D")
			     .retrieve()
			     .bodyToMono(String.class);
			 
			 System.out.println(customer.block());
			 
	}
	
	public static WebClient getWebClient(WebClient.Builder webClientBuilder) {
		
		final int size = 16 * 1024 * 1024;
	    final ExchangeStrategies strategies = ExchangeStrategies.builder()
	        .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
	        .build();

        return webClientBuilder
            .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
            .baseUrl("https://in.investing.com/equities/zensar-technologies")
            .exchangeStrategies(strategies)
            .build();
    }
	
	private static HttpClient getHttpClient() {
        return HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
            .doOnConnected(conn -> conn
                .addHandlerLast(new ReadTimeoutHandler(10))
                .addHandlerLast(new WriteTimeoutHandler(10)));
    }
}
