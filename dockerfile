# Utiliser une image de Tomcat avec JDK 17
FROM tomcat:9.0-jdk17-openjdk-slim

# Supprimer les applications par défaut (facultatif)
RUN rm -rf /usr/local/tomcat/webapps/*

# Définir le répertoire de travail
WORKDIR /usr/local/tomcat/webapps/

# Copier le fichier .war dans le répertoire approprié
COPY target/demo-0.0.1-SNAPSHOT.war ./demo-0.0.1-SNAPSHOT.war

# Exposer le port par défaut de Tomcat (généralement 8080)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.war"]