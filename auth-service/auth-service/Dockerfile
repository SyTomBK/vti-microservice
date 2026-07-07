FROM eclipse-temurin:17-jre-alpine

#Tạo thư mục ứng dụng trong container
WORKDIR /app

COPY target/auth-service-0.0.1-SNAPSHOT.jar /app/auth-service-0.0.1-SNAPSHOT.jar

EXPOSE 8082

# Chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "/app/auth-service-0.0.1-SNAPSHOT.jar"]
