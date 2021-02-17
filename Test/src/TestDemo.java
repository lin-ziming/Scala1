import java.util.BitSet;

import org.junit.Test;

/**
 * 思考题：
 * 有一个文件,文件中存储了40亿个整型数字,没有重复的。要求找出哪些数字没有出现过

附加要求:
1.使用java基础来实现，不能使用大数据框架技术
2.使用内存不能超过1GB

思路:
整型能够表达的数字近43亿,有近3亿个数字是没有出现过，即找出这些数字。

如果使用整型数组存储40亿数字然后进行处理，不行，因为内存大约占用15GB,不满足要求。
 * @author Administrator
 */
public class TestDemo {

	@Test
	public void testBt(){
		//可以使用比特数组。BitSet
		BitSet bs=new BitSet(Integer.MAX_VALUE);
		//1000 1101
		//文件中出现的数字：0 2 3 7
		//出现过的数字都Set过
		bs.set(0);
		bs.set(2);
		bs.set(3);
		bs.set(7);
		
		for(int i=0;i<bs.length();i++){
			System.out.println(i+":"+bs.get(i));
		}
		
	}
	
}
