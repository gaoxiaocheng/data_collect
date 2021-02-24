package com.machine.record.util;

import com.machine.record.entity.CubeWebServiceConfig;
import com.machine.record.entity.MonitorConfig;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;

@Component
public class WebServiceUtil {

    /**
     * @Description 方法描述：执行mdx
     * @date 2019年4月9日
     */
    public String executeMdx(String mdx, MonitorConfig monitorConfig) {
        String catalogName = monitorConfig.getDatabase();
        CubeWebServiceConfig cubeWebServiceConfig = monitorConfig.getCubeWebServiceConfig();
        // 设置调用webservice地址
        String endpoint = cubeWebServiceConfig.getEndpoint();
        String targetNamespace = cubeWebServiceConfig.getNameSpace();
        String cubeMethod = cubeWebServiceConfig.getWebMethod();
        String result = null;
        try {
            // 创建一个服务(service)调用(call)
            Service service = new Service();
            Call call = (Call) service.createCall();// 通过service创建call对象
            call.setTimeout(new Integer(600000));// 设置60S超时

            // 设置service所在URL
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new QName(targetNamespace, cubeMethod));// 调用方法
            //添加参数,有几个参数就传几个参数值，设置参数名 state  第二个参数表示String类型,第三个参数表示入参
            //call.addParameter(new QName(targetNamespace, "UserID"), org.apache.axis.encoding.XMLType.XSD_STRING,
            //javax.xml.rpc.ParameterMode.IN);// 参数及类型

            call.setUseSOAPAction(true);
            call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);// 设置返回类型
            call.setSOAPActionURI(targetNamespace + cubeMethod);
            call.addParameter(new QName(targetNamespace, "MDX"), org.apache.axis.encoding.XMLType.SOAP_STRING,
                    javax.xml.rpc.ParameterMode.IN);// 参数及类型
            call.addParameter(new QName(targetNamespace, "CATALOG"), org.apache.axis.encoding.XMLType.SOAP_STRING,
                    javax.xml.rpc.ParameterMode.IN);// 参数及类型

            //上面添加了几个addParameter，这里数组就加几个变量值
            result = (String) call.invoke(new Object[]{mdx, catalogName});//此处为数组，有几个变量传几个变量
            System.out.println(result);
            System.out.println(MdxUtil.dealWXDimFieldValueJson(result, true, true));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            monitorConfig.setMessage(monitorConfig.getMessage() + ",连接模型服务器失败");
        }
        return result;
    }


}
