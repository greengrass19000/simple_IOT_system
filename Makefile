infra:
	@docker compose up -d

app:
	@./mvnw spring-boot:run
