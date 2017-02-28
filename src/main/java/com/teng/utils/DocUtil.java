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
 * ���߳����󶹰���վ����ץȡ��Ҫ������
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
					break;//ֹͣ�����߳�
				}
			}
			System.out.println("command have commited.");
			threadPool.shutdown();//�����߳�ִ����֮�󣬽��̳߳��е��߳�ȫ���ɵ�
			
			while (true) {  
	            if (threadPool.isTerminated()) { 
	    			System.out.println(bookList.size() + "*******************");
	    			//�����֣��ɸߵ�������
	    			SortUtil.sortByRating(bookList);
	    			//��ȡ�ź�����б�
	    			List<BookEntity> subList = bookList.subList(0, 40);
	    			//����excel���
	    			ExportExcelUtil.ExportExcel(subList);     
	    			long endTime = System.currentTimeMillis();
	                System.out.println("����ˣ���ʱ:"+((endTime - startTime)/1000)+"s *******");  
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
	 * ��������
	 * @param num
	 * @param bookList
	 */
	public static void resolveData(int num,final List<BookEntity> bookList){
		Document doc =null;
		try {
		        doc = Jsoup.connect("https://book.douban.com/tag/���")   
					 .data("start", String.valueOf(num*20))   // �������  
					 .data("type","T")
					 .userAgent("I �� m jsoup") // ���� User-Agent   
					 .cookie("auth", "token") // ���� cookie   
					 .timeout(10000)           // �������ӳ�ʱʱ��  
					 .get();	// ʹ�� GET �������� URL   
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
					//����
					String subject = obj.select(".info h2 a").text();
					//����
					String rating = obj.select(".star .rating_nums").text();
					//��������
					String raterNumText = obj.select(".star .pl").text();
					String raterNum = raterNumText.substring(raterNumText.indexOf("(")+1,raterNumText.indexOf("��"));
					
					if(raterNum.startsWith("����")|| (Integer.valueOf(raterNum)<1000)){
						continue;
					}
					
					String infoText = obj.select(".info .pub").text();//��ѧ� / ��е��ҵ������ / 2013-11-18 / 89.00 
					//�۸�
					String price = infoText.substring(infoText.lastIndexOf("/")+1);
					String publishDateText = infoText.substring(0, infoText.lastIndexOf("/"));//��ѧ� / ��е��ҵ������ / 2013-11-18
					//����ʱ��
					String publishDate = publishDateText.substring(publishDateText.lastIndexOf("/")+1);
					//����������
					String publishText = publishDateText.substring(0, publishDateText.lastIndexOf("/"));//��ѧ� / ��е��ҵ������
					String publisher = publishText.substring(publishText.lastIndexOf("/")+1);
					//����
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
					//System.out.println("��������"+subject+"��    ���֣�"+rating +"   ���������� "+raterNum +"  ���ߣ�"+author + "  ����������:"+publisher+"  ����ʱ�䣺"+publishDate+"   �۸�"+price);
				}
			}else{
				flag = true;
			}
		}
	}
	
}
