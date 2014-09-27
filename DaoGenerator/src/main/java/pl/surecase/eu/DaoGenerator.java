package pl.surecase.eu;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGenerator {

  public static void main(String args[]) throws Exception {

    //Create Schema object with represents database version and package
    Schema schema = new Schema(3, "greendao");

    //We create Entity - database object which will be named Box.
    Entity box = schema.addEntity("Box");

    //Now we can add properties/fields to our database Entity/Object
    box.addIdProperty();
    box.addStringProperty("name");
    box.addIntProperty("slots");
    box.addStringProperty("description");

    //Finaly we can invoke DaoGenerator object which is avaliable by default in DaoGenerator due to Maven tool
    //Check External Libraries/Dao Generator
    new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, args[0]);
  }
}
