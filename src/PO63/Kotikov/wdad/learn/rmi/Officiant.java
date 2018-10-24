package PO63.Kotikov.wdad.learn.rmi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Officiant implements Serializable
{
    private static List<Officiant> officiantList = new ArrayList<>();
    private String firstname;
    private String secondname;

    public Officiant(String firstname, String secondname) throws Exception
    {
        this.firstname = firstname;
        this.secondname = secondname;
        for(Officiant officiant : officiantList)
            if(officiant.firstname.equals(firstname) && officiant.secondname.equals(secondname)) throw new Exception("Attempt to add the same officiant. " + officiant.toString() + " is in list again");
        officiantList.add(this);
    }

    public String getFirstname()
    {
        return firstname;
    }

    public static boolean isOfficiant(Officiant officiant)
    {
        for(Officiant o : Officiant.getOfficiantList())
            if(o.equals(officiant)) return true;
        return false;
    }

    public static Officiant findOfficiant(Officiant officiant)
    {
        for(Officiant o : Officiant.getOfficiantList())
            if(o.equals(officiant)) return o;
        return null;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getSecondname()
    {
        return secondname;
    }

    public void setSecondname(String secondname)
    {
        this.secondname = secondname;
    }

    public static List<Officiant> getOfficiantList()
    {
        return officiantList;
    }

    public void changeOfficiantFullname(Officiant newOfficiant) throws Exception
    {
        if(newOfficiant == null) throw new Exception("Trying to change officiant's name to 'null' name");
        this.firstname = newOfficiant.firstname;
        this.secondname = newOfficiant.secondname;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Officiant && ((Officiant) obj).firstname.equals(this.firstname) &&
                ((Officiant) obj).secondname.equals(this.secondname);
    }

    @Override
    public int hashCode()
    {
        return firstname.hashCode() ^ secondname.hashCode();
    }

    @Override
    public String toString()
    {
        return "Officiant: " + firstname + " " + secondname;
    }
}
