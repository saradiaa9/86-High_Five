# Use OpenJDK base image
FROM openjdk:25-ea-4-jdk-oraclelinux9

# Set working directory inside the container
WORKDIR /app

# Copy only the necessary files
COPY . /app

COPY target/ target/

# Ensure Maven Wrapper is executable
RUN chmod +x mvnw

# Expose port 8080
EXPOSE 8080

# Set environment variables for data.json file paths
ENV USER_DATA_PATH=/app/data/users.json
ENV PRODUCT_DATA_PATH=/app/data/products.json
ENV ORDER_DATA_PATH=/app/data/orders.json
ENV CART_DATA_PATH=/app/data/carts.json

# Run the application
CMD ["java", "-jar", "/app/target/mini1.jar"]
