FROM eclipse-temurin:17-jre-alpine

#Tạo thư mục ứng dụng trong container
WORKDIR /app

COPY target/config-service-0.0.1-SNAPSHOT.jar /app/config-service-0.0.1-SNAPSHOT.jar

EXPOSE 8763

# Chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "/app/config-service-0.0.1-SNAPSHOT.jar"]
