package com.jun.util;

import com.alibaba.fastjson.JSON;
import com.jun.dao.BizLogRepo;
import com.jun.entity.BizLogEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class InterfaceRecord {

    @Autowired
    private BizLogRepo logRepo;
    // 初始化日志类
    private static final Log logger = LogFactory.getLog(InterfaceRecord.class);
    private static String operId = "";
    // Service层切点
    // TODO: 2017-05-05  biz层全部包括
//    @Pointcut("execution(* com.jun.controller.*.insert(..))")
    //  @Around("within(@org.springframework.stereotype.Controller *) && @annotation(is)")
    @Pointcut("@annotation(com.jun.util.LogService)")
    public void serviceAspect() {   }

    @Before(value = "serviceAspect()")
    public void doBefore(JoinPoint joinPoint) {
        long now = System.currentTimeMillis();
        logger.info("接口拦截开始时间：" + now);
    }

    @AfterReturning(value = "serviceAspect()", argNames = "retVal", returning = "retVal")
    public void doAfterReturning(JoinPoint joinPoint, Object retVal) {
        long now = System.currentTimeMillis();
        logger.info("接口拦截结束时间：" + now);
        BizLogEntity log = new BizLogEntity();
        try {
            // 补充数据
            log = supplementEntity(log, joinPoint);
            // 返回参数
            String responseStr = "";
            if (retVal != null && "".equals(operId)) {
                responseStr = JSON.toJSONString(retVal);
                operId = JSON.parseObject(responseStr).getString("id");
                log.setObjId(operId);
            }
            logRepo.save(log);
        } catch (Exception e) {
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", e);
        }
    }

    //异常统一处理
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        long now = System.currentTimeMillis();
        logger.error(e);
        logger.error("接口异常拦截时间：" + now);
        BizLogEntity log = new BizLogEntity();
        try {
            // 补充数据
            log = supplementEntity(log, joinPoint);
            String responseStr = "";
            if (e != null) {
                responseStr = JSON.toJSONString(e);
            }
            // 响应信息
//            logger.error("接口异常信息：" + responseStr);
        } catch (Exception ee) {
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ee);
        }
    }

    // 匹配com.owenapp.service.impl包下所有类的、
    // 所有方法的执行作为切入点
    @AfterThrowing(throwing = "ex"
            , pointcut = "execution(* com.jun.service.impl.*.*(..))")
    // 声明ex时指定的类型会限制目标方法必须抛出指定类型的异常
    // 此处将ex的类型声明为Throwable，意味着对目标方法抛出的异常不加限制
    public void doRecoveryActions(Throwable ex) {

        System.out.println("目标方法中抛出的异常:" + ex);
        System.out.println("模拟Advice对异常的修复...");
    }

    /**
     * TODO 填充数据
     */
    private BizLogEntity supplementEntity(BizLogEntity log, JoinPoint joinPoint) {
        // 请求的IP
//        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//                    .getRequestAttributes()).getRequest();
//            String ip = request.getRemoteAddr();
//        } catch (Exception ee) {
//            logger.error("获取不到httprequest：" + ee);
//        }
        // 注解
        Map<String, String> annos = getServiceMthodAnnotatin(joinPoint);
        log.setBizTime(System.currentTimeMillis());
        log.setBizType(annos.get("bizType"));
        log.setRemark(annos.get("remark"));
        log.setObjId(annos.get("objId"));
        log.setObjType(annos.get("objType"));
        log.setOperatorId("操作人Id");
        log.setOperatorName("操作人");
        return log;
    }

    public static String getParms(JoinPoint joinPoint) {
        StringBuffer requestStr = new StringBuffer();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                if (i != joinPoint.getArgs().length - 1) {
                    requestStr.append(JSON.toJSONString(joinPoint.getArgs()[i]));
                    requestStr.append(",");
                } else {
                    requestStr.append(JSON.toJSONString(joinPoint.getArgs()[i]));
                }
            }
        }
//    System.out.println(joinPoint.getSignature().getName());
        return "[" + requestStr.toString() + "]";

    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> getServiceMthodAnnotatin(JoinPoint joinPoint) {
        Map<String, String> result = new HashMap<String, String>();
        // 获取target class名称
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取target method名称
        String methodName = joinPoint.getSignature().getName();
        // 获取请求参数
        Object[] arguments = joinPoint.getArgs();
        // 注解类
        try {
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            // 注解方法
            String description = "";
            String oprateType = "";
            String objId = "";
            String objType = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        description = method.getAnnotation(LogService.class).desc();
                        oprateType = method.getAnnotation(LogService.class).oprateType();
                        objType = method.getAnnotation(LogService.class).objType();
                        objId = method.getAnnotation(LogService.class).objId();
                        // 接口描述
                        if ("".equals(description)) {
//  String requestMethod = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
                            result.put("remark", targetName + "." + methodName + ":" + getParms(joinPoint));
                        } else {
                            result.put("remark", description + ":" + getParms(joinPoint));
                        }
                        result.put("bizType", oprateType);
//                        System.out.println(joinPoint.getArgs()[0].getClass().getName());
                        if (!"".equals(objId) && joinPoint.getArgs().length > 0 && joinPoint.getArgs()[0] != null) {
                            operId = JSON.parseObject(JSON.toJSONString(joinPoint.getArgs()[0])).getString(objId);
                        }
                        result.put("objId", operId);
                        result.put("objType", objType);
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}