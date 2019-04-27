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
 * 2019-04-27T11:00:53.099+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "ProjectEndPointService",
                  wsdlLocation = "http://localhost:8080/ProjectEndpoint?wsdl",
                  targetNamespace = "http://endpoint.tm.girfanov.ru/")
public class ProjectEndPointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.girfanov.ru/", "ProjectEndPointService");
    public final static QName ProjectEndPointPort = new QName("http://endpoint.tm.girfanov.ru/", "ProjectEndPointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/ProjectEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ProjectEndPointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/ProjectEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ProjectEndPointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ProjectEndPointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProjectEndPointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ProjectEndPointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ProjectEndPointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ProjectEndPointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns ProjectEndPoint
     */
    @WebEndpoint(name = "ProjectEndPointPort")
    public ProjectEndPoint getProjectEndPointPort() {
        return super.getPort(ProjectEndPointPort, ProjectEndPoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ProjectEndPoint
     */
    @WebEndpoint(name = "ProjectEndPointPort")
    public ProjectEndPoint getProjectEndPointPort(WebServiceFeature... features) {
        return super.getPort(ProjectEndPointPort, ProjectEndPoint.class, features);
    }

}
