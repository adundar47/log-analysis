mongo:
	docker-compose -f docker-compose.yml up -d mongo

central-server:
	docker-compose -f docker-compose.yml up -d central-server

log-handler:
	docker-compose -f docker-compose.yml up -d log-handler

kafka:
	docker-compose -f docker-compose.yml up -d zookeeper
	docker-compose -f docker-compose.yml up -d kafka

log-listener:
	docker-compose -f docker-compose.yml up -d log-listener

log-creator:
	docker-compose -f docker-compose.yml up -d log-creator

serve:
	docker-compose -f docker-compose.yml up -d mongo
	docker-compose -f docker-compose.yml up -d zookeeper
	docker-compose -f docker-compose.yml up -d kafka
	docker-compose -f docker-compose.yml up -d central-server
	docker-compose -f docker-compose.yml up -d log-handler
	docker-compose -f docker-compose.yml up -d log-listener
	docker-compose -f docker-compose.yml up -d log-creator

stop-and-remove-non-stateful:
	docker-compose stop central-server
	docker-compose stop log-handler
	docker-compose stop zookeeper
	docker-compose stop kafka
	docker-compose stop log-listener
	docker-compose stop log-creator
	docker-compose rm -f

build:
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build

clean:
	docker-compose down --rmi local -v --remove-orphans

clean-deep:
	$(MAKE) clean
	sudo rm -rf /mongodb-data
	sudo rm -rf /zookeeper-data
	sudo rm -rf /kafka-data

ps:
	@docker-compose ps

new-version: new-central-server-version new-log-handler-version new-log-listener-version new-log-creator-version

new-central-server-version:
	docker-compose rm -f central-server
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build central-server

new-log-handler-version:
	docker-compose rm -f log-handler
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build log-handler

new-log-listener-version:
	docker-compose rm -f log-listener
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build log-listener

new-log-creator-version:
	docker-compose rm -f log-creator
	docker-compose -f docker-compose.yml -f docker-compose.build.yml build log-creator

logs-central-server:
	docker-compose logs -f central-server

logs-log-handler:
	docker-compose logs -f log-handler

logs-log-listener:
	docker-compose logs -f log-listener	

logs-log-creator:
	docker-compose logs -f log-creator		

logs-kafka:
	docker-compose logs -f kafka		