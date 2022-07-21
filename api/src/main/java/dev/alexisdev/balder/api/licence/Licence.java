package dev.alexisdev.balder.api.licence;

import dev.alexisdev.balder.api.model.Model;
import dev.alexisdev.balder.api.util.Validate;
import org.jetbrains.annotations.Nullable;

/**
 * This class represents the Model for creating licenses.
 */
public class Licence
        implements Model {

    private final String id;
    protected String address;

    protected Licence(
            String id,

            String address
    ) {
        this.id = Validate.notNull(id, "id");
        this.address = address;
    }

    /**
     * @return The licence id.
     */

    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the address of the person, or null if the person has no address.
     *
     * @return A String
     */
    public @Nullable String getAddress() {
        return address;
    }

    /**
     * This function sets the address of the user to the given address.
     *
     * @param address The address of the server.
     */
    public void setAddress(String address) {
        this.address = Validate.notNull(
                address, "address"
        );
    }

    /**
     * Create a new Licence object with the given id, key and address.
     *
     * @param id The id of the licence.
     * @param address The address of the licence holder.
     * @return A new instance of the Licence class.
     */
    public static Licence create(
            String id,
            String address
    ) {
        return new Licence(
                id,
                address
        );
    }
}
