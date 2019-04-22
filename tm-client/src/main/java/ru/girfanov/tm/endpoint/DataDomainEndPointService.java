package ru.girfanov.tm.endpoint;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-22T11:14:42.056+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "DataDomainEndPointService",
                  wsdlLocation = "http://localhost:8080/DataDomainEndpoint?wsdl",
                  targetNamespace = "http://endpoint.tm.girfanov.ru/")
public class DataDomainEndPointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.girfanov.ru/", "DataDomainEndPointService");
    public final static QName DataDomainEndPointPort = new QName("http://endpoint.tm.girfanov.ru/", "DataDomainEndPointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/DataDomainEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(DataDomainEndPointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/DataDomainEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public DataDomainEndPointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DataDomainEndPointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DataDomainEndPointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public DataDomainEndPointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public DataDomainEndPointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public DataDomainEndPointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns DataDomainEndPoint
     */
    @WebEndpoint(name = "DataDomainEndPointPort")
    public DataDomainEndPoint getDataDomainEndPointPort() {
        return super.getPort(DataDomainEndPointPort, DataDomainEndPoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DataDomainEndPoint
     */
    @WebEndpoint(name = "DataDomainEndPointPort")
    public DataDomainEndPoint getDataDomainEndPointPort(WebServiceFeature... features) {
        return super.getPort(DataDomainEndPointPort, DataDomainEndPoint.class, features);
    }

}
