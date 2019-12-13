import { ExternalLogin } from '../models/login';
import { BaseOidcProvider } from './base-oidc-provider';

export class GoogleProvider extends BaseOidcProvider {
    /**
     * Format user data per provider claims:
     * https://developers.google.com/identity/protocols/OpenIDConnect
     * https://developers.google.com/+/web/api/rest/openidconnect/getOpenIdConnect
     */
    protected formatUserData(userData): ExternalLogin {
        const login: ExternalLogin = {
            id: userData.sub,
            name: userData.name,
            email: userData.email,
            firstName: userData.firstName,
            lastName: userData.lastName,
            picture: userData.picture,
            externalToken: this.oidcSecurityService.getToken()

        };
        return login;
    }
}
