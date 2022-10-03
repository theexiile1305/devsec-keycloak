package dev.mfuchs.devseckeycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LastLoginEventListenerProvider implements EventListenerProvider {

  private final KeycloakSession session;

  public LastLoginEventListenerProvider(KeycloakSession session) {
    this.session = session;
  }

  @Override
  public void onEvent(Event event) {
    if (event != null && EventType.LOGIN == event.getType()) {
      final RealmModel realmModel = session.realms().getRealm(event.getRealmId());
      final UserModel user = session.users().getUserById(realmModel, event.getUserId());
      setCurrentTimeAsLastLogin(user);
    }
  }

  @Override
  public void onEvent(AdminEvent event, boolean includeRepresentation) {}

  @Override
  public void close() {}

  private void setCurrentTimeAsLastLogin(UserModel user) {
    final String loginTime = DateTimeFormatter.ISO_DATE_TIME.format(OffsetDateTime.now(ZoneOffset.UTC));
    final String lastLoginUserAttribute = "lastLogin";
    user.setSingleAttribute(lastLoginUserAttribute, loginTime);
  }
}
