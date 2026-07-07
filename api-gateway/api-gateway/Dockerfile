# Sử dụng image JDK 17 chính thức
FROM eclipse-temurin:17-jre-alpine

LABEL authors = "sytom"

# Tạo thư mục ứng dụng trong container
WORKDIR /app

# Sao chép file JAR vào thư mục làm việc trong container
COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway-0.0.1-SNAPSHOT.jar

EXPOSE 8762

# Chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "/app/api-gateway-0.0.1-SNAPSHOT.jar"]