package com.microservice.controller;

import com.microservice.domain.City;
import com.sun.org.apache.xml.internal.utils.MutableAttrListImpl;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.functions.Action1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author songabao
 * @date 2019/8/5 13:49
 */
@RestController
@RequestMapping(value = "rxjava")
public class RxjavaStudyController {

    private static  final Logger log= LoggerFactory.getLogger(RxjavaStudyController.class);

    /**
     * <b>说明：简单实用Rxjava</b>
     * @return
     */
    @GetMapping(value = "make")
    public String simpleMake(){
        Flowable.just("学习Rxjava").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                    log.info(s);
            }
        });
        return  null;
    }
    /**
     * <b>说明：1-10数字自乘 map的使用</b>
     */
    @GetMapping(value = "map")
    public List mapSimple() {
        ArrayList<Object> list = new ArrayList<>();
        Flowable.range(1, 10).observeOn(Schedulers.computation())
                .map(new Function<Integer, Integer>() {

                    @Override
                    public Integer apply(Integer i) throws Exception {
                        return i * i;
                    }
                }).blockingSubscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                log.info("-----"+integer);
                list.add(integer);
            }
        });
        return list;
    }

    /**
     * <b>说明：flatMap的使用</b>
     * @return
     */
    @GetMapping(value = "flatmap")
    public List flatmapSimple(){
        ArrayList<Object> list = new ArrayList<>();
        Flowable.range(1,10).flatMap(new Function<Integer, Publisher<Integer>>() {
            @Override
            public Publisher<Integer> apply(Integer integer) throws Exception {
                return  Flowable.just(integer).observeOn(Schedulers.computation())
                        .map(new Function<Integer, Integer>() {
                            @Override
                            public Integer apply(Integer integer) throws Exception {
                                return integer*integer;
                            }
                        });
            }
        }).blockingSubscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                log.info("flatmap: "+integer);
                list.add(integer);
            }
        });
        return list;
    }

    /**
     * <b>interval 隔一段时间发射整数序列的Observable，可以实现定时器</b>
     * @return
     */
    @GetMapping(value = "observable")
    public  void observableSimple(){
        Observable.interval(3, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                log.info("observable: "+aLong);
            }
        });
    }

    /**
     * <b>说明：指定范围每隔一段时间发送</b>
     */
    @GetMapping(value = "range")
    public void observableRange(){
        Observable.range(0,6).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                log.info("Scope: " + integer);
            }
        });
    }

    /**
     * <b>说明：创建一个N次重复发射特定数据的Observable</b>
     */
    @GetMapping(value = "specail")
    public  void observableSpecial(){
        //repeat(2)是循环二遍
        Observable.range(0,20).repeat(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                log.info("Special: " + integer);
            }
        });
    }

    /**
     * <b>说明：buff的使用</b>
     */
    @GetMapping(value = "buff")
    public  void buffSimple(){
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        Flowable.fromIterable(list).flatMapIterable(new Function<String, List<String>>() {
            @Override
            public List<String> apply(String s) throws Exception {
                List list1 = new ArrayList<>();
                list1.add(s+"学习Rxjava中buffer");
                return list1;
            }
        }) .buffer(4).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                log.info(" "+strings);
            }
        });

    }

    /**
     * <b>说明：groudBy的使用</b>
     */
    @GetMapping(value = "groupBy")
    public void groupBySimple(){
        ArrayList<Object> list = new ArrayList<>();
        Flowable.range(0,10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                City city=  new City();
                city.setName("学习Rxjava中groudBy");
                city.setId(integer.longValue());
                list.add(city);
            }
        });
        log.info("List添加的city是："+list);
//        Flowable.fromIterable(list).groupBy(new Function<City, City>() {
//            @Override
//            public City apply(City o) throws Exception {
//               // return ;
//            }
//        });

    }
}
