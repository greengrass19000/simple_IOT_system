infra:
	@docker compose up -d

server:
	@mvnw spring-boot:run
# 	@./mvnw spring-boot:run

sensor:
	@mvnw exec:java -Dexec.mainClass="btl.demo2.sensor.Sensor"
# 	@./mvnw exec:java -Dexec.mainClass="btl.demo2.sensor.Sensor"
