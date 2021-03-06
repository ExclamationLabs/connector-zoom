# Zoom Connector

Open source Identity Management connector for [Zoom](https://zoom.us)

Leverages the [Connector Base Framework](https://github.com/ExclamationLabs/connector-base)

Developed and tested in [Midpoint](https://evolveum.com/midpoint/), but also could be utilized in any [ConnId](https://connid.tirasa.net/) framework. 

## Introductory Notes

- This software is Copyright 2020 Exclamation Labs.  Licensed under the Apache License, Version 2.0.

- As of this writing, there is no public Java API for Zoom User and Group management.

- Limitations:
 
    - You can create a Zoom developer account to access the API, but you won't be successful
    in creating Groups of users unless you have paid for a non-Basic (non-free)
     account with Zoom.  See <https://zoom.us/pricing> for more information.
     
    - This connector has been developed to work with Zoom JWT authentication, since JWT
    is most ideal for application-level authentication.  Zoom also supports OAuth2 for authentication
    but that authentication strategy is not implemented in this connector.
 
    - When creating a user, the user must verify his email address and move from 'pending' to 'active'
 status before he will actually be returned in a list of Zoom users or be assigned to groups.
 
    - The connector has only been tested with 'Basic' users in Zoom and normal user
    creation (other types are autoCreate, custCreate and ssoCreate).  Some of these
    types cannot be used unless you have a particular type of Zoom account.

## Getting started
See <https://marketplace.zoom.us/develop> for information on setting up a Zoom developer
 account. Once you are authenticated, create a new Application with JWT authentication 
 (Develop -> Create App -> JWT).  Copy App credentials (API key, API secret)
 to a clipboard/text file for later use.

Information on the JWT authentication method with Zoom is available at
<https://marketplace.zoom.us/docs/guides/build/jwt-app> .

Once this is all done, you can manage Users and Groups in Zoom's web UI by going to
<https://zoom.us/meeting> and using the Admin -> User Management links.

## Midpoint configuration

See XML files in src/test/resources folder for Midpoint examples.  resourceOverlay.xml is an example
resource configuration setup for Midpoint.

## Configuration properties 

- See src/test/resources/__bcon__development__exclamation_labs__zoom.properties for an example

- CONNECTOR_BASE_CONFIGURATION_ACTIVE - Set this flag to Y to enable these configuration properties.  If this
flag is set to N, integration tests will be ignored, and the connector configuration will not be able to be used
in MidPoint.

- CONNECTOR_BASE_AUTH_JWT_ISSUER - Use the API key saved from the 'Getting started' instructions.

- CONNECTOR_BASE_AUTH_JWT_SECRET - Use the API secret saved from the 'Getting started' instructions.

- CONNECTOR_BASE_AUTH_JWT_EXPIRATION_PERIOD - Expiration period in milliseconds of JWT token.
