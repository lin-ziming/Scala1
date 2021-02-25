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
 * @author Administrator
 *
 */
public class TestDemoMy {

	@Test
	public void demo1() throws IOException{
		String url="https://www.chinahr.com/detail/bj/tech/45307053966621x.shtml?psid=180669207211580880799525228&from=list";
		//向目标网页发起请求
		Connection conn=Jsoup.connect(url);
		//获取网页内容对象
		Document doc=conn.get();
		
		//返回标签集合
		Elements els=doc.select(".desc_title");
		
		for (Element el : els) {
			System.out.println(el.text());
		}
	}
	/**
	 * @throws IOException 
	 * 
	 */
	@Test
	public void fetchJobName() throws IOException{
		String url="https://www.chinahr.com/detail/bj/tech/45307053966621x.shtml?psid=180669207211580880799525228&from=list";
		
		Connection conn=Jsoup.connect(url);
		
		Document doc=conn.get();
		
		Elements els=doc.select(".job_content>.job_left>h1");
		
		for (Element el : els) {
			System.out.println(el.text());
		}
	}
	/**
	 * 爬取薪资数据
	 * @throws IOException 
	 */
	@Test
	public void fetchSalary() throws IOException{
		String url="https://www.chinahr.com/detail/bj/tech/45307053966621x.shtml?psid=180669207211580880799525228&from=list";
		
		Connection conn=Jsoup.connect(url);
		
		Document doc=conn.get();
		
		Elements els=doc.select("span[class=job_salary]");
		
		for (Element el : els) {
			System.out.println(el.text());
		}
		
	}
	/**
	 * 爬取“任职福利”
	 * @throws IOException 
	 * 
	 */
	@Test
	public void work() throws IOException{
		String url="https://www.chinahr.com/detail/bj/tech/45307053966621x.shtml?psid=180669207211580880799525228&from=list";
		
		Connection conn=Jsoup.connect(url);
		
		Document doc=conn.get();
		
		Element el=doc.select(".job_item_2>.job_tag").get(0);
		
		System.out.println(el);
	}
	/**
	 * 爬取 招聘页中的：
	 * 1.招聘岗位
	 * 2.薪资
	 * 3.工作地点
	 * 4.学历
	 * @throws IOException 
	 */
	@Test
	public void fetchJobs() throws IOException{
		String url="https://search.chinahr.com/bj/job/?key=Java%25E5%25BC%2580%25E5%258F%2591%25E5%25B7%25A5%25E7%25A8%258B%25E5%25B8%2588&utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT";
		
		Connection conn=Jsoup.connect(url);
		
		Document doc=conn.get();
		
		//>.jobList pc_search_listclick>.l1>.job-name
		Elements els=doc.select(".job-list-box");
		
		for (Element el : els) {
			System.out.println(el);
		}
	}
	
	
}
