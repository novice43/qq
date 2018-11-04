package PO63.Kotikov.wdad.data.managers;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the generated package.
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Server }
     *
     */
    public Server createServer() {
        return new Server();
    }

    /**
     * Create an instance of {@link Registry }
     *
     */
    public Registry createRegistry() {
        return new Registry();
    }

    /**
     * Create an instance of {@link Bindedobject }
     *
     */
    public Bindedobject createBindedobject() {
        return new Bindedobject();
    }

    /**
     * Create an instance of {@link Appconfig }
     *
     */
    public Appconfig createAppconfig() {
        return new Appconfig();
    }

    /**
     * Create an instance of {@link Rmi }
     *
     */
    public Rmi createRmi() {
        return new Rmi();
    }

    /**
     * Create an instance of {@link Datasource }
     *
     */
    public Datasource createDatasource() {
        return new Datasource();
    }

    /**
     * Create an instance of {@link Client }
     *
     */
    public Client createClient() {
        return new Client();
    }

}
