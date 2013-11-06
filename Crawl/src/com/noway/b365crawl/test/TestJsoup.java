package com.noway.b365crawl.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.solr.common.SolrInputDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.noway.b365crawl.WeiboBlock;

public class TestJsoup {
	private final String blockRegex = "<script>STK\\s&&\\sSTK\\.pageletM\\s&&\\sSTK\\.pageletM\\.view\\(.*\\)";
	private Pattern pattern = Pattern.compile(blockRegex);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestJsoup testJsoup = new TestJsoup();

		String url = "http://s.weibo.com/";
		// String url =
		// "http://s.weibo.com/weibo/%25E4%25B8%25AD%25E5%259B%25BD%25E7%25A7%25BB%25E5%258A%25A8&nodup=1&page=2";
		Document doc = Jsoup.parse(getHtmlByUrl(url));
		testGetLinkSample(doc);

	}

	public static String getHtmlByUrl(String url) {
		HttpClient httpClient = new HttpClient();
		//httpClient.getHostConfiguration().setProxy("localhost", 8087);
		GetMethod method = new GetMethod(url);

		String html = null;
		try {
			// 设置http头
						List<Header> headers = new ArrayList<Header>();
						headers.add(new Header(
								"User-Agent",
								"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.57 Safari/537.36"));
						headers.add(new Header("Connection", "keep-alive"));
						headers.add(new Header("Accept-Language", "zh-cn;zh;q=0.8"));
						headers.add(new Header(
								"Cookie",
								"SINAGLOBAL=3418965872842.8184.1377842380942; U_TRS1=00000035.1c426032.525e41dc.d904f9e1; _s_tentry=-; Apache=219133645296.0968.1382318796794; ULV=1382318796799:6:4:1:219133645296.0968.1382318796794:1382067734895; myuid=1265813057; user_active=201311011154; user_unver=2797a80b444b4c671d23369bb8ebc3ee; appkey=; UOR=server.51cto.com,widget.weibo.com,login.sina.com.cn; SUE=es%3D0a97e2caccf83889c4ebe97a290287bc%26ev%3Dv1%26es2%3D479788f2c83de8fd2d9f69c03a937b62%26rs0%3DvhDXGS9ZWMLwlyEz6KiGkd1RYc9cnMynZ%252FBBzMlHxUJp09DjcwBFKILazWnn8ijPXHoAcO%252BniOed4LdEhHbcLcLCBmco2i6CWzfd%252BEv8Cm7Z5Px0ZYTLapvtK0mzUtq4EWuPDspp3vPm82SpLrSXFVMoxP6uQAI3uYjFLLPUOno%253D%26rv%3D0; SUP=cv%3D1%26bt%3D1383526700%26et%3D1383613100%26d%3Dc909%26i%3D06ce%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D27%26st%3D0%26uid%3D3880734526%26name%3Dnoway0_1%2540163.com%26nick%3DHelloWorld23%25E5%259B%25B4%25E8%2584%2596%26fmp%3D%26lcp%3D; SUS=SID-3880734526-1383526700-JA-q152z-e3943199ff0686e1e7cbb8441eef38b6; ALF=1386118700; SSOLoginState=1383526700; un=noway0_1@163.com; wvr=5"								
								));
						httpClient.getHostConfiguration().getParams()
								.setParameter("http.default-headers", headers);

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			int statusCode = httpClient.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.out.println("Method failed code=" + statusCode + ": "
						+ method.getStatusLine());

			} else {
System.out.println(new String(method.getResponseBody()));
				html = method.getResponseBodyAsString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}

		return html;
	}


	/**
	 * 获取所有的结果正文节点
	 * 
	 * @param doc
	 * @return
	 */
	private List<Element> getAllElement(Document doc) {

		List<Element> resultList = new ArrayList<Element>();

		Elements elems = doc.select(".search_feed .feed_list");

		for (Element element : elems) {
			resultList.add(element);
		}

		return resultList;
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}

	private static void testGetLinkSample(Document doc) {
		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");

		print("\nMedia: (%d)", media.size());
		for (Element src : media) {
			if (src.tagName().equals("img"))
				print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("src"),
						src.attr("width"), src.attr("height"),
						trim(src.attr("alt"), 20));
			else
				print(" * %s: <%s>", src.tagName(), src.attr("src"));
		}

		print("\nImports: (%d)", imports.size());
		for (Element link : imports) {
			print(" * %s <%s> (%s)", link.tagName(), link.attr("href"),
					link.attr("rel"));
		}

		print("\nLinks: (%d)", links.size());
		for (Element link : links) {
			print(" * a: <%s>  (%s)", link.attr("href"), trim(link.text(), 35));
		}
	}
}
