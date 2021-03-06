
package ru.girfanov.tm.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.girfanov.tm.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateSession_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "createSession");
    private final static QName _CreateSessionResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "createSessionResponse");
    private final static QName _GetServerInfo_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "getServerInfo");
    private final static QName _GetServerInfoResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "getServerInfoResponse");
    private final static QName _RemoveSession_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "removeSession");
    private final static QName _RemoveSessionResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "removeSessionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.girfanov.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateSession }
     * 
     */
    public CreateSession createCreateSession() {
        return new CreateSession();
    }

    /**
     * Create an instance of {@link CreateSessionResponse }
     * 
     */
    public CreateSessionResponse createCreateSessionResponse() {
        return new CreateSessionResponse();
    }

    /**
     * Create an instance of {@link GetServerInfo }
     * 
     */
    public GetServerInfo createGetServerInfo() {
        return new GetServerInfo();
    }

    /**
     * Create an instance of {@link GetServerInfoResponse }
     * 
     */
    public GetServerInfoResponse createGetServerInfoResponse() {
        return new GetServerInfoResponse();
    }

    /**
     * Create an instance of {@link RemoveSession }
     * 
     */
    public RemoveSession createRemoveSession() {
        return new RemoveSession();
    }

    /**
     * Create an instance of {@link RemoveSessionResponse }
     * 
     */
    public RemoveSessionResponse createRemoveSessionResponse() {
        return new RemoveSessionResponse();
    }

    /**
     * Create an instance of {@link SessionDto }
     * 
     */
    public SessionDto createSessionDto() {
        return new SessionDto();
    }

    /**
     * Create an instance of {@link AbstractEntityDto }
     * 
     */
    public AbstractEntityDto createAbstractEntityDto() {
        return new AbstractEntityDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "createSession")
    public JAXBElement<CreateSession> createCreateSession(CreateSession value) {
        return new JAXBElement<CreateSession>(_CreateSession_QNAME, CreateSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "createSessionResponse")
    public JAXBElement<CreateSessionResponse> createCreateSessionResponse(CreateSessionResponse value) {
        return new JAXBElement<CreateSessionResponse>(_CreateSessionResponse_QNAME, CreateSessionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "getServerInfo")
    public JAXBElement<GetServerInfo> createGetServerInfo(GetServerInfo value) {
        return new JAXBElement<GetServerInfo>(_GetServerInfo_QNAME, GetServerInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "getServerInfoResponse")
    public JAXBElement<GetServerInfoResponse> createGetServerInfoResponse(GetServerInfoResponse value) {
        return new JAXBElement<GetServerInfoResponse>(_GetServerInfoResponse_QNAME, GetServerInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "removeSession")
    public JAXBElement<RemoveSession> createRemoveSession(RemoveSession value) {
        return new JAXBElement<RemoveSession>(_RemoveSession_QNAME, RemoveSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "removeSessionResponse")
    public JAXBElement<RemoveSessionResponse> createRemoveSessionResponse(RemoveSessionResponse value) {
        return new JAXBElement<RemoveSessionResponse>(_RemoveSessionResponse_QNAME, RemoveSessionResponse.class, null, value);
    }

}
