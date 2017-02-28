package com.teng.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.teng.entity.BookEntity;

/**
 * List集合排序
 * @author TENG
 *
 */
public class SortUtil {
	public static void sortByRating(List<BookEntity> bookList){
	  Collections.sort(bookList, new Comparator<BookEntity>(){
          /**
           * 按“评分”排序，由高到低
           */
          @Override
          public int compare(BookEntity o1, BookEntity o2) {
              Double rating1 = Double.valueOf(o1.getRating())==null?0:Double.valueOf(o1.getRating());
              Double rating2 = Double.valueOf(o2.getRating())==null?0:Double.valueOf(o2.getRating());       
              if(rating1.compareTo(rating2) > 0){
                  return -1;
              }else if(rating1.compareTo(rating2) < 0){
                  return 1;
              }else{
            	  return 0;
              }
          }
      });
	}
}
