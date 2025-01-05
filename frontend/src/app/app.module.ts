import { APP_INITIALIZER, NgModule, PLATFORM_ID, Inject } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { isPlatformBrowser } from '@angular/common'; // Importez isPlatformBrowser
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomersComponent } from './ui/customers/customers.component';
import { ProductsComponent } from './ui/products/products.component';
import { NgForOf } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { KeycloakAngularModule, KeycloakService } from "keycloak-angular";

export function initializeKeycloak(keycloak: KeycloakService, platformId: Object) {
  return () => {
    if (isPlatformBrowser(platformId)) { // Vérifiez si le code est exécuté dans un navigateur
      const origin = window.location.origin; // Utilisez window uniquement côté navigateur
      return keycloak.init({
        config: {
          url: 'http://localhost:8080',
          realm: 'ecom-realm',
          clientId: 'ecom-client-angular'
        },
        initOptions: {
          onLoad: 'check-sso',
          silentCheckSsoRedirectUri: `${origin}/assets/silent-check-sso.html`
        }
      });
    } else {
      return Promise.resolve(); // Ne rien faire côté serveur
    }
  };
}

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    ProductsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    KeycloakAngularModule
  ],
  providers: [
    provideClientHydration(),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService, PLATFORM_ID] // Injectez PLATFORM_ID
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
