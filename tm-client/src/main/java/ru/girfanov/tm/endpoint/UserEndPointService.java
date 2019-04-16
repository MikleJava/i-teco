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
 * 2019-04-15T18:21:04.797+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "UserEndPointService",
                  wsdlLocation = "http://localhost:8080/UserEndpoint?wsdl",
                  targetNamespace = "http://endpoint.tm.girfanov.ru/")
public class UserEndPointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.girfanov.ru/", "UserEndPointService");
    public final static QName UserEndPointPort = new QName("http://endpoint.tm.girfanov.ru/", "UserEndPointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/UserEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(UserEndPointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/UserEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public UserEndPointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserEndPointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserEndPointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public UserEndPointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public UserEndPointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public UserEndPointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns UserEndPoint
     */
    @WebEndpoint(name = "UserEndPointPort")
    public UserEndPoint getUserEndPointPort() {
        return super.getPort(UserEndPointPort, UserEndPoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserEndPoint
     */
    @WebEndpoint(name = "UserEndPointPort")
    public UserEndPoint getUserEndPointPort(WebServiceFeature... features) {
        return super.getPort(UserEndPointPort, UserEndPoint.class, features);
    }

}
