package com.graduation.education.user.service.api;

import com.graduation.education.user.common.req.StudentSignREQ;
import com.graduation.education.user.common.req.TaskREQ;
import com.graduation.education.user.common.resq.TaskRESQ;
import com.graduation.education.user.service.api.biz.ApiCommonBiz;
import com.graduation.education.user.service.dao.impl.mapper.entity.Advice;
import com.graduation.education.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/1 15:45
 */
@RestController
@RequestMapping("/user/api")
public class ApiCommonController {

    @Autowired
    private ApiCommonBiz apiCommonBiz;

    @RequestMapping(value = "/advices", method = RequestMethod.GET)
    public Result<ArrayList<Advice>> list(@RequestParam(value = "classNo") String classNo) {
        List<Advice> adviceList = apiCommonBiz.list(classNo);
        return Result.success(new ArrayList<>(adviceList));
    }

    @RequestMapping(value = "/sign/student", method = RequestMethod.POST)
    public Result<String> signStudent(@RequestBody StudentSignREQ studentSignREQ) {
        if (StringUtils.isBlank(studentSignREQ.getPhoneBrand()) || StringUtils.isBlank(studentSignREQ.getPhoneModel())) {
            return Result.error("parameter phoneBrand、phoneModel is missing");
        }
        return apiCommonBiz.signStudent(studentSignREQ);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public Result<TaskRESQ> getAllTask(TaskREQ taskREQ) {
        return apiCommonBiz.getAllTask(taskREQ);
    }


    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public Result<String> getContent() {
        String content = "<h2>加锁和释放锁的原理</h2>\n" +
                "\t\t\t\t\t<p>在同一jvm，并发情况下，jvm会自动通过使用monitor来加锁和解锁，保证在同一时间最多只有一个线程执行指定代码，以达到保证并发安全的效果，具有可重入和不可中断的性质</p>\n" +
                "\t\t\t\t\t<h4>1.加锁和释放锁的原理</h4>\n" +
                "\t\t\t\t\t<p>当方法执行完后或者抛出异常后，都会释放锁</p>\n" +
                "\t\t\t\t\t<p><img src=\"https://coursehelper.oss-cn-beijing.aliyuncs.com/graduation/md/one.png\" referrerpolicy=\"no-referrer\"></p>\n" +
                "\t\t\t\t\t<p>method1()等价于method2(),method1()正常执行完毕或抛出异常后自动释放锁，method2()是通过lock.unlock();释放锁</p>\n" +
                "\t\t\t\t\t<p>被synchronized修饰的方法和代码块，就是monitor机制的临界区，同步方法和代码块的加锁和释放锁就是基于monitor机制来实现的，monitor有两个指令，monitorenter和monitorexit,monitorenter会插入同步代码块的开始位置，monitorexit会插入同步代码块结束和退出位置，每个monitor必有一个monitorexit与之对应，也许有多个monitorexit与一个monitorenter对应，因为方法结束的时机，可以是方法正常退出或抛出异常</p>\n" +
                "\t\t\t\t\t<p>每个类的实例对象都有一个monitor对象与之关联</p>\n" +
                "\t\t\t\t\t<p>为什么反编译的字节码文件中monitorenter个数和monitorexit个数不一样 ， monitorexit好像会多一点？</p>\n" +
                "\t\t\t\t\t<p>因为程序可能是正常退出或抛出异常退出，但是为了保证两种情况都释放锁，所以会存在多个monitorexit对应一个monitorenter。</p>\n" +
                "\t\t\t\t\t<p>&nbsp;</p>\n" +
                "\t\t\t\t\t<h4>2.monitorenter和monitorexit指令</h4>\n" +
                "\t\t\t\t\t<p>实际上每个类的实例对象都和一个monitor对象关联，一个monitor的lock锁只能被一个线程在同一时间获得。</p>\n" +
                "\t\t\t\t\t<h5>monitorenter的三种情况:</h5>\n" +
                "\t\t\t\t\t<p>1.当monitor对象的计数器为0时，代表当前的lock锁没有被获得，然后线程获得该lock锁，并把monitor的计数器加1，当其它线程获取该lock锁时，会判断monitor对象的计数器的值，大于0就阻塞，等于与其他线程竞争该lock锁</p>\n" +
                "\t\t\t\t\t<p>2.如果线程已经拿到了monitor对象的lock锁的所有权，又重入了锁，就计数器累加</p>\n" +
                "\t\t\t\t\t<p>3.monitor的对象的锁已经被其他线程拥有，那么当前线程只能处于阻塞状态，直到monitor对象的计数器为0时，才能去获取该lock锁。</p>\n" +
                "\t\t\t\t\t<h5>monitorexit:</h5>\n" +
                "\t\t\t\t\t<p>monitorexit的作用是释放锁，前提是线程拥有锁，原理是计数器减一，如果当前计数器为0，表示线程已释放该锁的所有权，如果不为0，则是重入进来的，线程继续拥有该锁，每遇到一个monitorexit计数器就会减一</p>\n" +
                "\t\t\t\t\t<p>&nbsp;</p>\n" +
                "\t\t\t\t\t<h4>3.可重入的原理</h4>\n" +
                "\t\t\t\t\t<p>可重入 :  同一线程外层函数获得锁之后，内层函数可以直接再次获得该锁。</p>\n" +
                "\t\t\t\t\t<p>实现 :   加锁计数器累加(计数器属于monitor对象)</p>\n" +
                "\t\t\t\t\t<p>1.jvm会跟踪对象被加锁的次数</p>\n" +
                "\t\t\t\t\t<p>2.线程第一次给对象加锁时，将对应monitor对象的计数器加一。每当相同线程再获取该对象的锁时，计数器会累加</p>\n" +
                "\t\t\t\t\t<p>3.代码块、方法执行完毕或抛出异常，计数递减，当计数为0时，表示当前对象锁已经被完全释放</p>\n" +
                "\t\t\t\t\t<p>&nbsp;</p>\n" +
                "\t\t\t\t\t<h4>4.可见性原理</h4>\n" +
                "\t\t\t\t\t<p>&nbsp;</p>\n" +
                "\t\t\t\t\t<p>线程间如何通信?</p>\n" +
                "\t\t\t\t\t<p>每个线程都有自己独立的内存空间，都保存了主内存中共享变量的副本，私有内存只有自己可以访问，主内存是公共的，之所以这样设计，是为了提高程序运行，因为线程本地内存运行速度比主内存运行速度快</p>\n" +
                "\t\t\t\t\t<p>线程A和线程B通信:</p>\n" +
                "\t\t\t\t\t<p>1.线程A将本地共享变量的副本同步到主内存中</p>\n" +
                "\t\t\t\t\t<p>2.线程B从主内存中获取最新的共享变量，并将其同步到本地内存,更新完成后，再同步到主内存</p>\n" +
                "\t\t\t\t\t<p>3.线程A再从主内存中共享变量的最新值</p>\n" +
                "\t\t\t\t\t<p>&nbsp;</p>\n" +
                "\t\t\t\t\t<p>synchronized如何实现可见性？</p>\n" +
                "\t\t\t\t\t<p>被synchronized修饰的方法、代码块，在执行的时候，共享变量的值都是直接从主内存中获取的，执行完更新操作之后，再同步到主内存中去，这样就保证了线程之间共享变量的可见性。</p>\n" +
                "\t\t\t\t\t<p>&nbsp;</p>\n" +
                "\t\t\t\t\t<h4>1.七种情况</h4>\n" +
                "\t\t\t\t\t<p>&nbsp;</p>\n" +
                "\t\t\t\t\t<p><img src=\"https://coursehelper.oss-cn-beijing.aliyuncs.com/graduation/md/image-20200105154901088.png\" referrerpolicy=\"no-referrer\" alt=\"image-20200105154901088\"></p>";

        return Result.success(content);
    }
}
