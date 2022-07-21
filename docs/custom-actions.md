# Custom Actions

## How to handle Used licence action.

To customize this action we must extend the class called
[`DefaultLicenceRegister`](../api/src/main/java/dev/alexisdev/balder/api/registry/DefaultLicenceRegistry.java) and extend the method called
`DefaultLicenceRegistry#licenceUsedAction(Licence)`

### Example

```java
public class CustomLicenceRegistry
        extends DefaultLicenceRegistry {

    public CustomLicenceRegistry(
            LicenceFinder licenceFinder,
            LicenceUpdater licenceUpdater
    ) {
        super(licenceFinder, licenceUpdater);
    }

    @Override
    protected void licenceUsedAction(Licence licence) {
        try {
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            String licenceAddress = licence.getAddress();

            if (licenceAddress != null && licenceAddress.equals(serverIp)) {
                return;
            }

            throw new IllegalArgumentException("Licence is used");
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unknown host", e);
        }
    }
}
```