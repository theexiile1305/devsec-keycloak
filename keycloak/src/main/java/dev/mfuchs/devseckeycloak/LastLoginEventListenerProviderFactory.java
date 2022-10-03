package dev.mfuchs.devseckeycloak;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class LastLoginEventListenerProviderFactory implements EventListenerProviderFactory {

  @Override
  public EventListenerProvider create(KeycloakSession session) {
    return new LastLoginEventListenerProvider(session);
  }

  @Override
  public void init(Config.Scope config) {}

  @Override
  public void postInit(KeycloakSessionFactory factory) {}

  @Override
  public void close() {}

  @Override
  public String getId() {
    return "keycloak_last_login_listener";
  }
}
