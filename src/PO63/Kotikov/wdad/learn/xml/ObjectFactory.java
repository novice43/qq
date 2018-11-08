
package PO63.Kotikov.wdad.learn.xml;

import javax.xml.bind.annotation.XmlRegistry;
import java.io.Serializable;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the PO63.Kotikov.wdad.learn.xml package. 
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
public class ObjectFactory implements Serializable
{


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: PO63.Kotikov.wdad.learn.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Date }
     * 
     */
    public Date createDate() {
        return new Date();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link Restaurant }
     * 
     */
    public Restaurant createRestaurant() {
        return new Restaurant();
    }

    /**
     * Create an instance of {@link Officiant }
     * 
     */
    public Officiant createOfficiant() {
        return new Officiant();
    }

}
