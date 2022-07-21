package dev.alexisdev.balder.mongo;

import com.mongodb.client.MongoDatabase;
import dev.alexisdev.balder.api.buildable.Buildable;
import dev.alexisdev.balder.api.util.Validate;

public class MongoLicenceUpdaterBuilder
        implements Buildable<MongoLicenceUpdater> {

    private MongoDatabase database;
    private String collectionName;


    public MongoLicenceUpdaterBuilder database(
            MongoDatabase database
    ) {
        this.database = Validate.notNull(
                database, "database"
        );
        return this;
    }

    public MongoLicenceUpdaterBuilder collection(
            String collection
    ) {
        this.collectionName = Validate.notNull(
                collection, "collection"
        );
        return this;
    }

    @Override
    public MongoLicenceUpdater build() {
        return new MongoLicenceUpdater(
                database.getCollection(collectionName)
        );
    }
}
