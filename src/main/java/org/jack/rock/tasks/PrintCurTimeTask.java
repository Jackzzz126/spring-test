package org.jack.rock.tasks;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PrintCurTimeTask
 *
 * @author zhengzhe17
 * @date 2020/7/13
 */
@Slf4j
@Component
public class PrintCurTimeTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 3000)
    //public  void printCurTime(){
    //    log.info("Cur time is {}", dateFormat.format(new Date()));
    //}
}
