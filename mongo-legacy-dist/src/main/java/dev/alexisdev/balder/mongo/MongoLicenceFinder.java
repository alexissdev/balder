package dev.alexisdev.balder.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dev.alexisdev.balder.api.finder.LicenceFinder;
import dev.alexisdev.balder.api.licence.Licence;
import org.bson.Document;
import org.jetbrains.annotations.Nullable;

public class MongoLicenceFinder
        implements LicenceFinder {

    protected final MongoCollection<Document> mongoCollection;

    protected MongoLicenceFinder(
            MongoCollection<Document> mongoCollection
    ) {
        this.mongoCollection = mongoCollection;
    }

    @Override
    public Licence find(String licence) {
        Document document = findDocument(
                licence
        );

        if (document == null) {
            return null;
        }

        return Licence.create(
                licence,
                document.getString("address")
        );
    }

    protected @Nullable Document findDocument(String id) {
        return mongoCollection
                .find(Filters.eq("_id", id))
                .first();
    }
}
