package top.hitotright.demo.spdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping(value = "/async")
public class AsyncPageController {

    private static final Logger logger = LoggerFactory.getLogger(AsyncPageController.class);

    @RequestMapping("")
    public String index(){
        logger.info(Thread.currentThread().getName() + " 访问index方法");
        return "async";
    }

    @RequestMapping("/test")
    @ResponseBody
    public Callable<String> test() {
        // 这么做的好处避免web server的连接池被长期占用而引起性能问题，
        // 调用后生成一个非web的服务线程来处理，增加web服务器的吞吐量。
        logger.info(Thread.currentThread().getName() + " 进入test方法");
        Callable<String> re = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info(Thread.currentThread().getName() + " 进入call方法");
                Thread.sleep(3 * 1000L);
                logger.info(Thread.currentThread().getName() + " 从call方法返回");
                return "小单 - " + System.currentTimeMillis();
            }
        };
        logger.info(Thread.currentThread().getName() + " test方法返回");
        return re;
    }

    @RequestMapping("/test2")
    @ResponseBody
    public WebAsyncTask<String> test2() {
        logger.info(Thread.currentThread().getName() + " 进入test2方法");

        // 3s钟没返回，则认为超时
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info(Thread.currentThread().getName() + " 进入call2方法");
                Thread.sleep(2 * 1000L);
                logger.info(Thread.currentThread().getName() + " 从call2方法返回");
                return "小单 - " + System.currentTimeMillis();
            }
        });
        logger.info(Thread.currentThread().getName() + " 从test2方法返回");

        webAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                logger.info(Thread.currentThread().getName() + " test2执行完毕");
            }
        });

        webAsyncTask.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info(Thread.currentThread().getName() + " test2onTimeout");
                // 超时的时候，直接抛异常，让外层统一处理超时异常
                throw new TimeoutException(" 调用超时");
            }
        });
        return webAsyncTask;
    }

}
