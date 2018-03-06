package com.jun.util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartAddDataListener  implements ApplicationListener<ContextRefreshedEvent> {
      public void onApplicationEvent(ContextRefreshedEvent event)  {
        if(event.getApplicationContext().getParent() == null) {
            long a=System.currentTimeMillis();
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            System.out.println("\n\n\n\n\n______\n\n\n加载了\n\n______\n\n");
            System.out.println("\r执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 "+(System.currentTimeMillis()-a)+"毫秒");
        }
        //或者下面这种方式
        if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
            System.out.println("\n\n\n_____\n\n加载一次的 \n\n _____\n\n\n\n");
        }
    }
}
