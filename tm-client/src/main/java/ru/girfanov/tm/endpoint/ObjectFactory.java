
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

    private final static QName _FindAllUsers_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "findAllUsers");
    private final static QName _FindAllUsersResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "findAllUsersResponse");
    private final static QName _FindOneUser_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "findOneUser");
    private final static QName _FindOneUserByLoginAndPassword_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "findOneUserByLoginAndPassword");
    private final static QName _FindOneUserByLoginAndPasswordResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "findOneUserByLoginAndPasswordResponse");
    private final static QName _FindOneUserResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "findOneUserResponse");
    private final static QName _MergeUser_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "mergeUser");
    private final static QName _MergeUserPassword_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "mergeUserPassword");
    private final static QName _MergeUserPasswordResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "mergeUserPasswordResponse");
    private final static QName _MergeUserResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "mergeUserResponse");
    private final static QName _PersistUser_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "persistUser");
    private final static QName _PersistUserResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "persistUserResponse");
    private final static QName _RemoveAllUsers_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "removeAllUsers");
    private final static QName _RemoveAllUsersResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "removeAllUsersResponse");
    private final static QName _RemoveUser_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "removeUser");
    private final static QName _RemoveUserResponse_QNAME = new QName("http://endpoint.tm.girfanov.ru/", "removeUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.girfanov.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindAllUsers }
     * 
     */
    public FindAllUsers createFindAllUsers() {
        return new FindAllUsers();
    }

    /**
     * Create an instance of {@link FindAllUsersResponse }
     * 
     */
    public FindAllUsersResponse createFindAllUsersResponse() {
        return new FindAllUsersResponse();
    }

    /**
     * Create an instance of {@link FindOneUser }
     * 
     */
    public FindOneUser createFindOneUser() {
        return new FindOneUser();
    }

    /**
     * Create an instance of {@link FindOneUserByLoginAndPassword }
     * 
     */
    public FindOneUserByLoginAndPassword createFindOneUserByLoginAndPassword() {
        return new FindOneUserByLoginAndPassword();
    }

    /**
     * Create an instance of {@link FindOneUserByLoginAndPasswordResponse }
     * 
     */
    public FindOneUserByLoginAndPasswordResponse createFindOneUserByLoginAndPasswordResponse() {
        return new FindOneUserByLoginAndPasswordResponse();
    }

    /**
     * Create an instance of {@link FindOneUserResponse }
     * 
     */
    public FindOneUserResponse createFindOneUserResponse() {
        return new FindOneUserResponse();
    }

    /**
     * Create an instance of {@link MergeUser }
     * 
     */
    public MergeUser createMergeUser() {
        return new MergeUser();
    }

    /**
     * Create an instance of {@link MergeUserPassword }
     * 
     */
    public MergeUserPassword createMergeUserPassword() {
        return new MergeUserPassword();
    }

    /**
     * Create an instance of {@link MergeUserPasswordResponse }
     * 
     */
    public MergeUserPasswordResponse createMergeUserPasswordResponse() {
        return new MergeUserPasswordResponse();
    }

    /**
     * Create an instance of {@link MergeUserResponse }
     * 
     */
    public MergeUserResponse createMergeUserResponse() {
        return new MergeUserResponse();
    }

    /**
     * Create an instance of {@link PersistUser }
     * 
     */
    public PersistUser createPersistUser() {
        return new PersistUser();
    }

    /**
     * Create an instance of {@link PersistUserResponse }
     * 
     */
    public PersistUserResponse createPersistUserResponse() {
        return new PersistUserResponse();
    }

    /**
     * Create an instance of {@link RemoveAllUsers }
     * 
     */
    public RemoveAllUsers createRemoveAllUsers() {
        return new RemoveAllUsers();
    }

    /**
     * Create an instance of {@link RemoveAllUsersResponse }
     * 
     */
    public RemoveAllUsersResponse createRemoveAllUsersResponse() {
        return new RemoveAllUsersResponse();
    }

    /**
     * Create an instance of {@link RemoveUser }
     * 
     */
    public RemoveUser createRemoveUser() {
        return new RemoveUser();
    }

    /**
     * Create an instance of {@link RemoveUserResponse }
     * 
     */
    public RemoveUserResponse createRemoveUserResponse() {
        return new RemoveUserResponse();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link AbstractEntity }
     * 
     */
    public AbstractEntity createAbstractEntity() {
        return new AbstractEntity();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "findAllUsers")
    public JAXBElement<FindAllUsers> createFindAllUsers(FindAllUsers value) {
        return new JAXBElement<FindAllUsers>(_FindAllUsers_QNAME, FindAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "findAllUsersResponse")
    public JAXBElement<FindAllUsersResponse> createFindAllUsersResponse(FindAllUsersResponse value) {
        return new JAXBElement<FindAllUsersResponse>(_FindAllUsersResponse_QNAME, FindAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "findOneUser")
    public JAXBElement<FindOneUser> createFindOneUser(FindOneUser value) {
        return new JAXBElement<FindOneUser>(_FindOneUser_QNAME, FindOneUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUserByLoginAndPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "findOneUserByLoginAndPassword")
    public JAXBElement<FindOneUserByLoginAndPassword> createFindOneUserByLoginAndPassword(FindOneUserByLoginAndPassword value) {
        return new JAXBElement<FindOneUserByLoginAndPassword>(_FindOneUserByLoginAndPassword_QNAME, FindOneUserByLoginAndPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUserByLoginAndPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "findOneUserByLoginAndPasswordResponse")
    public JAXBElement<FindOneUserByLoginAndPasswordResponse> createFindOneUserByLoginAndPasswordResponse(FindOneUserByLoginAndPasswordResponse value) {
        return new JAXBElement<FindOneUserByLoginAndPasswordResponse>(_FindOneUserByLoginAndPasswordResponse_QNAME, FindOneUserByLoginAndPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "findOneUserResponse")
    public JAXBElement<FindOneUserResponse> createFindOneUserResponse(FindOneUserResponse value) {
        return new JAXBElement<FindOneUserResponse>(_FindOneUserResponse_QNAME, FindOneUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "mergeUser")
    public JAXBElement<MergeUser> createMergeUser(MergeUser value) {
        return new JAXBElement<MergeUser>(_MergeUser_QNAME, MergeUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeUserPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "mergeUserPassword")
    public JAXBElement<MergeUserPassword> createMergeUserPassword(MergeUserPassword value) {
        return new JAXBElement<MergeUserPassword>(_MergeUserPassword_QNAME, MergeUserPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeUserPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "mergeUserPasswordResponse")
    public JAXBElement<MergeUserPasswordResponse> createMergeUserPasswordResponse(MergeUserPasswordResponse value) {
        return new JAXBElement<MergeUserPasswordResponse>(_MergeUserPasswordResponse_QNAME, MergeUserPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "mergeUserResponse")
    public JAXBElement<MergeUserResponse> createMergeUserResponse(MergeUserResponse value) {
        return new JAXBElement<MergeUserResponse>(_MergeUserResponse_QNAME, MergeUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "persistUser")
    public JAXBElement<PersistUser> createPersistUser(PersistUser value) {
        return new JAXBElement<PersistUser>(_PersistUser_QNAME, PersistUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "persistUserResponse")
    public JAXBElement<PersistUserResponse> createPersistUserResponse(PersistUserResponse value) {
        return new JAXBElement<PersistUserResponse>(_PersistUserResponse_QNAME, PersistUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "removeAllUsers")
    public JAXBElement<RemoveAllUsers> createRemoveAllUsers(RemoveAllUsers value) {
        return new JAXBElement<RemoveAllUsers>(_RemoveAllUsers_QNAME, RemoveAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "removeAllUsersResponse")
    public JAXBElement<RemoveAllUsersResponse> createRemoveAllUsersResponse(RemoveAllUsersResponse value) {
        return new JAXBElement<RemoveAllUsersResponse>(_RemoveAllUsersResponse_QNAME, RemoveAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "removeUser")
    public JAXBElement<RemoveUser> createRemoveUser(RemoveUser value) {
        return new JAXBElement<RemoveUser>(_RemoveUser_QNAME, RemoveUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.girfanov.ru/", name = "removeUserResponse")
    public JAXBElement<RemoveUserResponse> createRemoveUserResponse(RemoveUserResponse value) {
        return new JAXBElement<RemoveUserResponse>(_RemoveUserResponse_QNAME, RemoveUserResponse.class, null, value);
    }

}
