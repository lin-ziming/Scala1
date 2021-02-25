package cn.tedu.douban;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * Jsoup标签选择器语法
 * 1.根据标签名来选择,比如:
 * Elements els=doc.select("a");
 * Elements els=doc.select("span");
 * Elements els=doc.select("div");
 *
 * 2.根据标签中的id属性来查找。用#来表示id。比如:
 * <div id='main'></div>
 * Elements els=doc.select("#main");
 * 
 * 3.根据标签中的class属性来查找。用.来表示class。比如:
 * <div class='main'></div>
 * Elements els=doc.select(".main");
 * 
 * 4.根据标签内的所有属性来查找(比较万能的),比如:
 * <span class="class1" id="id1">36</span>
 * Elements elements=doc.select("span[class=class1]span[id=id1]");
 * 
 * 5.根据标签内的属性名前缀查找。比如:
 * <a href="//www.chinahr.com/home"></a>
 * Elements els=doc.select("a[^hr]");
 * 
 * 6.根据标签层级选择。用>或空格来表示层级,比如:
 * <div>
 *   <span></span>
 * </div>
 * 
 * Elements els=doc.select("div>span")
 * 
 * 7.通过get(index)操作Elements标签集合,获取对应下标的标签
 * Element el=doc.select(".job_item_2>.job_tag").get(0);
 * 
 * 8.如果属性值中有空格,则需要特殊处理。因为空格表示层级的含义。可以使用万能选择语法
 * 错误写法:
 * Elements els=doc.select(".price J-p-100007958748");
 * 处理方法1:
 * 如果属性值中有空格,则需要特殊处理。因为空格表示层级的含义。可以使用万能选择语法
 * Elements els=doc.select("span[class=price J-p-100007958748]");
 * 处理方法2:
 * 使用多个select分拆选择
 * Elements els=doc.select(".price").select(".J-p-100007958748");
 * 
 * 9.很多网站为了反爬，一种很常见的处理方式就是通过ajax请求后台数据，异步加载在网页中。
 * 即数据不是直接嵌入到网页的标签内容中的。
 * 比如豆瓣网的热门电影，京东的商品价格等。
 * 
 * ①遇到这种情况,需要找到对应的后台数据地址。直接去访问这个地址获取数据。
 * ②然后通过jsoup访问此后台数据地址
 * ③因为后台返回的不是网页标签数据,而是普通的文本数据,所以不能使用Document解析
 * String result=conn.ignoreContentType(true).execute().body();
 * ④处理文本数据
 * 
 */

public class TestDemo {

	@Test
	public void demo01() throws Exception{
		String url="https://www.chinahr.com/detail/bj/tech/45307053966621x.shtml?psid=126228060211580886304266074&from=list";
		//向目标网页发起请求
		Connection conn=Jsoup.connect(url);
		//获取网页内容对象
		Document doc=conn.get();
		
		//返回标签集合
		Elements els=doc.select("a[^hr]");
		
		for(Element el:els){
			//el.text()获取标签里的数据
			System.out.println(el);
		}
	}
	/*
	 * 爬取招聘职位
	 */
	@Test
	public void fetchJobName() throws Exception{
		String url="https://www.chinahr.com/detail/bj/tech/45307053966621x.shtml?psid=126228060211580886304266074&from=list";
		Connection conn=Jsoup.connect(url);
		Document doc=conn.get();
		
		//Elements els=doc.select("h1");
		
		//层级选择语法。用>表示层级
		Elements els=doc.select(".job_content .job_left h1");
		
		for(Element el:els){
			//el.text()获取标签里的数据
			System.out.println(el);
		}
		
	}
	/*
	 * 爬取薪资数据
	 */
	@Test
	public void fetchSalary() throws Exception{
		String url="https://www.chinahr.com/detail/bj/tech/45307053966621x.shtml?psid=126228060211580886304266074&from=list";
		Connection conn=Jsoup.connect(url);
		Document doc=conn.get();
		
		//Elements els=doc.select(".job_salary");
		Elements els=doc.select("span[class=job_salary]");
		
		for(Element el:els){
			//el.text()获取标签里的数据
			System.out.println(el.text());
		}
	}
	/*
	 * 爬取任职福利
	 */
	@Test
	public void work() throws Exception{
		String url="https://www.chinahr.com/detail/bj/tech/45307053966621x.shtml?psid=126228060211580886304266074&from=list";
		Connection conn=Jsoup.connect(url);
		Document doc=conn.get();
		
		//通过get(index)操作Elements标签集合,获取对应下标的标签
		Element el=doc.select(".job_item_2>.job_tag").get(0);
		
		System.out.println(el);
		
		
	}
	/*爬取 招聘页中的:
	 * 1.招聘岗位
	 * 2.薪资
	 * 3.工作地点
	 * 4.学历
	 * 
	 * 以上的信息打印控制台即可
	 * 打印格式:   招聘岗位:薪资-工作地点-学历
	 * 
	 * 
	 */
	@Test
	public void fetchJobs() throws Exception{
		String url="https://search.chinahr.com/bj/job/?key=Java%E5%BC%80%E5%8F%91%E5%B7%A5%E7%A8%8B%E5%B8%88";
		
		Connection conn=Jsoup.connect(url);
		Document doc=conn.get();
		
		//获取所有招聘岗位
		Elements jobnames=doc.select("li[class=job-name]");
		//获取所有薪资
		Elements salarys=doc.select(".salary");
		
		for(int i=0;i<jobnames.size();i++){
			String jobname=jobnames.get(i).text();
			String salary=salarys.get(i).text();
		}
	
		
	}
	@Test
	public void fetchSunNing() throws Exception{
		String url="https://list.suning.com/0-20006-0-0-0-0-0-0-0-0-12121.html?safp=d488778a.phone2018.103327226421.3&safc=cate.0.0&safpn=10003.00006";
		Connection conn=Jsoup.connect(url);
		
		Document doc=conn.get();
		
		Elements els=doc.select(".title-selling-point>a");
		
		for(Element el:els){
			System.out.println(el.text());
		}
		
		
	}
	@Test
	public void fetchJdPrice() throws Exception{
		String url="https://item.jd.com/100007958748.html";
		
		Connection conn=Jsoup.connect(url);
		
		Document doc=conn.get();
		
		//Elements els=doc.select(".price J-p-100007958748");
		//如果属性值中有空格,则需要特殊处理。因为空格表示层级的含义。可以使用万能选择语法
		//Elements els=doc.select("span[class=price J-p-100007958748]");
		//使用多个select分拆选择
		Elements els=doc.select(".price").select(".J-p-100007958748");
		
		for(Element el:els){
			System.out.println(el);
		}
	}
	@Test
	public void fetchBackPrice() throws Exception{
		//获取后台数据地址
		String url="https://p.3.cn/prices/mgets?callback=jQuery5957633&type=1&area=1_72_55653_0&pdtk=&pduid=1484243697&pdpin=&pin=null&pdbp=0&skuIds=J_100011386554%2CJ_10024511092590%2CJ_10024508315867%2CJ_10023552869717%2CJ_10021257683582%2CJ_10021299172407%2CJ_10023517593839%2CJ_10021299155641%2CJ_100013455568%2CJ_6772447%2CJ_10021257804569&ext=11100000&source=item-pc";
		
		Connection conn=Jsoup.connect(url);
		
		//因为后台返回的不是网页标签数据,而是普通的文本数据,所以不能使用Document解析
		//Document doc=conn.get();
		String result=conn.ignoreContentType(true).execute().body();
		
		System.out.println(result);
		
	}
	
}
