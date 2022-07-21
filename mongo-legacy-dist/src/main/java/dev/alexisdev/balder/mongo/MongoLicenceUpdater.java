package dev.alexisdev.balder.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import dev.alexisdev.balder.api.licence.Licence;
import dev.alexisdev.balder.api.updater.LicenceUpdater;
import org.bson.Document;

public class MongoLicenceUpdater
        implements LicenceUpdater {

    protected final MongoCollection<Document> mongoCollection;

    protected MongoLicenceUpdater(
            MongoCollection<Document> mongoCollection
    ) {
        this.mongoCollection = mongoCollection;
    }

    /**
     * This function returns a new instance of the MongoLicenceUpdaterBuilder class.
     *
     * @return A new instance of the MongoLicenceUpdaterBuilder class.
     */
    public static MongoLicenceUpdaterBuilder builder() {
        return new MongoLicenceUpdaterBuilder();
    }

    @Override
    public void update(
            Licence licence
    ) {
        Document document = new Document();
        document.append(
                "_id", licence.getId()
        );
        document.append(
                "address", licence.getAddress()
        );

        mongoCollection.replaceOne(
                Filters.eq("_id", licence.getId()),
                document,
                new ReplaceOptions().upsert(true)
        );
    }
}
