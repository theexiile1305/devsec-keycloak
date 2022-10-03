FROM quay.io/keycloak/keycloak:19.0.1@sha256:fb6be1665f2939ca123b5fee0d6d8e06a3d99ba1e5224b5b1cf0eb48168f1ef6 as builder
ENV KC_HTTP_RELATIVE_PATH=/auth
ENV KC_DB=postgres
ENV KC_FEATURES=authorization,account2,account-api,docker,impersonation,client-policies,ciba,par
COPY build/libs/keycloak-last-login-event-listener.jar /opt/keycloak/providers
COPY quarkus.properties /opt/keycloak/conf/quarkus.properties
RUN /opt/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:19.0.1@sha256:fb6be1665f2939ca123b5fee0d6d8e06a3d99ba1e5224b5b1cf0eb48168f1ef6
USER keycloak
COPY --from=builder /opt/keycloak/lib/quarkus/ /opt/keycloak/lib/quarkus/
COPY --from=builder /opt/keycloak/providers/ /opt/keycloak/providers/
COPY --from=builder /opt/keycloak/conf/quarkus.properties /opt/keycloak/conf/quarkus.properties
CMD [ " start --optimized" ]
