package com.example.sharding.dubbo;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.monitor.MonitorService;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.util.StopWatch;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * dubbo服务调用日志过滤器
 **/
@Slf4j
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER}, order = 1)
public class InvokerLogFilter implements Filter {


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (invoker.getInterface().isAssignableFrom(MonitorService.class)) {
            return invoker.invoke(invocation);
        }
        RpcContext rpcContext = RpcContext.getContext();
        if (rpcContext.isProviderSide()) {
            Object[] arguments = invocation.getArguments();
            log.debug("开始接收请求:[" + invoker.getInterface() + "." + invocation.getMethodName() + "], 请求参数: " +
                    JSON.toJSONString(arguments));
            StopWatch stopwatch = new StopWatch();
            stopwatch.start();
            Result result = invoker.invoke(invocation);
            stopwatch.stop();
            if (result.hasException()) {
                log.debug("返回请求结果:服务报错[" + invoker.getInterface() + "." + invocation.getMethodName() + "], 耗时:[" +
                        stopwatch.getLastTaskTimeMillis() + " ms]", result.getException());
            } else {
                log.debug("返回请求结果:[" + invoker.getInterface() + "." + invocation.getMethodName() + "], 耗时:[" +
                        stopwatch.getLastTaskTimeMillis() + " ms]");
            }
            return result;
        } else {
            Object[] arguments = invocation.getArguments();
            Result result;
            log.debug("开始调用接口:[" + invoker.getInterface() + "." + invocation.getMethodName() + "], 请求参数:" +
                            JSON.toJSONString(arguments));
            StopWatch stopwatch = new StopWatch();
            stopwatch.start();
            result = invoker.invoke(invocation);
            stopwatch.stop();

            log.debug("结束调用接口:[" + invoker.getInterface() + "." + invocation.getMethodName() + "], 耗时:[" +
                    stopwatch.getLastTaskTimeMillis() + " ms]");
            return result;
        }
    }

}
