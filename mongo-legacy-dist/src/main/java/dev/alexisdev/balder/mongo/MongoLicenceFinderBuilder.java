package dev.alexisdev.balder.mongo;

import com.mongodb.client.MongoDatabase;
import dev.alexisdev.balder.api.buildable.Buildable;
import dev.alexisdev.balder.api.util.Validate;

public class MongoLicenceFinderBuilder
        implements Buildable<MongoLicenceFinder> {

    private MongoDatabase database;
    private String collectionName;


    public MongoLicenceFinderBuilder database(
            MongoDatabase database
    ) {
        this.database = Validate.notNull(
                database, "database"
        );
        return this;
    }

    public MongoLicenceFinderBuilder collection(
            String collection
    ) {
        this.collectionName = Validate.notNull(
                collection, "collection"
        );
        return this;
    }

    @Override
    public MongoLicenceFinder build() {
        return new MongoLicenceFinder(
                database.getCollection(collectionName)
        );
    }
}
