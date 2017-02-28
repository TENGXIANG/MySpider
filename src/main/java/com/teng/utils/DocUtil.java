package com.teng.utils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.teng.entity.BookEntity;

/**
 * 多线程请求豆瓣网站，并抓取需要的数据
 * @author TENG
 *
 */
public class DocUtil {
	static List<Element> list = new LinkedList<Element>();
	static Element ulObj = null;
	static boolean flag = false;
	static long startTime = 0l;
	public static void getAllBooks(){
			startTime = System.currentTimeMillis();
			ExecutorService threadPool = Executors.newFixedThreadPool(60);
			final List<BookEntity> bookList = new LinkedList<BookEntity>();
			for(int i = 0 ; i < 1000 ; i++){
				final int num = i;
				threadPool.execute(new Runnable(){
					@Override
					public void run() {
						resolveData(num,bookList);
					}
				});
				if(flag){
					break;//停止创建线程
				}
			}
			System.out.println("command have commited.");
			threadPool.shutdown();//所有线程执行完之后，将线程池中的线程全部干掉
			
			while (true) {  
	            if (threadPool.isTerminated()) { 
	    			System.out.println(bookList.size() + "*******************");
	    			//按评分，由高到低排序
	    			SortUtil.sortByRating(bookList);
	    			//截取排好序的列表
	    			List<BookEntity> subList = bookList.subList(0, 40);
	    			//导入excel表格
	    			ExportExcelUtil.ExportExcel(subList);     
	    			long endTime = System.currentTimeMillis();
	                System.out.println("完成了，用时:"+((endTime - startTime)/1000)+"s *******");  
	                break;  
	            }  
	            try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  
			}
	}
	
	/**
	 * 解析数据
	 * @param num
	 * @param bookList
	 */
	public static void resolveData(int num,final List<BookEntity> bookList){
		Document doc =null;
		try {
		        doc = Jsoup.connect("https://book.douban.com/tag/编程")   
					 .data("start", String.valueOf(num*20))   // 请求参数  
					 .data("type","T")
					 .userAgent("I ’ m jsoup") // 设置 User-Agent   
					 .cookie("auth", "token") // 设置 cookie   
					 .timeout(10000)           // 设置连接超时时间  
					 .get();	// 使用 GET 方法访问 URL   
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(doc != null){
			Elements lists = doc.getElementsByClass("subject-list");
			ulObj = lists.get(0);
			Elements items  = ulObj.getElementsByClass("subject-item");
			if(items != null && items.size()>0){
				System.out.println(Thread.currentThread().getName() +"  **********  "+num);
				for(Element obj : items){
					//书名
					String subject = obj.select(".info h2 a").text();
					//评分
					String rating = obj.select(".star .rating_nums").text();
					//评分人数
					String raterNumText = obj.select(".star .pl").text();
					String raterNum = raterNumText.substring(raterNumText.indexOf("(")+1,raterNumText.indexOf("人"));
					
					if(raterNum.startsWith("少于")|| (Integer.valueOf(raterNum)<1000)){
						continue;
					}
					
					String infoText = obj.select(".info .pub").text();//唐学韬 / 机械工业出版社 / 2013-11-18 / 89.00 
					//价格
					String price = infoText.substring(infoText.lastIndexOf("/")+1);
					String publishDateText = infoText.substring(0, infoText.lastIndexOf("/"));//唐学韬 / 机械工业出版社 / 2013-11-18
					//出版时间
					String publishDate = publishDateText.substring(publishDateText.lastIndexOf("/")+1);
					//出版社名称
					String publishText = publishDateText.substring(0, publishDateText.lastIndexOf("/"));//唐学韬 / 机械工业出版社
					String publisher = publishText.substring(publishText.lastIndexOf("/")+1);
					//作者
					String author = publishText.substring(0,publishText.lastIndexOf("/"));
					
					BookEntity book = new BookEntity();
					book.setSubject(subject);
					book.setRating(rating);
					book.setRaterNum(raterNum);
					book.setAuthor(author);
					book.setPublisher(publisher);
					book.setPublishDate(publishDate);
					book.setPrice(price);
					bookList.add(book);
					//System.out.println("书名：《"+subject+"》    评分："+rating +"   评分人数： "+raterNum +"  作者："+author + "  出版社名称:"+publisher+"  出版时间："+publishDate+"   价格："+price);
				}
			}else{
				flag = true;
			}
		}
	}
	
}
