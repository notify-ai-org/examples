# E-commerce example app (storefront + API). Build from the root of this repository:
#   docker build -f deploy/ecommerce/Dockerfile --build-arg VITE_BASE_PATH=/ecommerce .

# Stage 1: build the React storefront with the path prefix it will be served under.
FROM node:20-alpine AS ui

WORKDIR /app/ecommerce-app/ui

ARG VITE_BASE_PATH=/ecommerce
ENV VITE_BASE_PATH=${VITE_BASE_PATH}

COPY ecommerce-app/ui/package.json ecommerce-app/ui/package-lock.json ./
RUN npm ci

COPY ecommerce-app/ui/ ./
# Writes to ../src/main/resources/static/portals/shop (see vite.config.ts).
RUN npm run build

# Stage 2: build the Spring Boot jar. The app is a standalone Maven project that
# pulls the Notify client SDK from Maven Central.
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

ARG MAVEN_RETRY_OPTS="-Dmaven.wagon.http.retryHandler.count=5 -Dmaven.wagon.http.retryHandler.requestSentEnabled=true -Dmaven.wagon.httpconnectionManager.ttlSeconds=120"

COPY ecommerce-app/pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline -B ${MAVEN_RETRY_OPTS}

COPY ecommerce-app/src/ src/
COPY --from=ui /app/ecommerce-app/src/main/resources/static/portals/shop/ \
    src/main/resources/static/portals/shop/

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -B -DskipTests ${MAVEN_RETRY_OPTS}

# Stage 3: runtime.
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=builder /app/target/ecommerce-app-1.0.0.jar app.jar

# The H2 database file lives in ./data (mount a volume at /app/data to keep it).
EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
