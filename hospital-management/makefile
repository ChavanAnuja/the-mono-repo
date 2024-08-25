
SERVICES := api-gateway appointment-service case-service directory-service patient-service
LIB := hospital-management-console
REPOSITORY := https://github.com/ChavanAnuja/the-mono-repo.git
DOCKER_USERNAME := anujachavan


DEFAULT: clone status pull stash build_lib build_services build_images push_images

clone:
	@echo "Started cloning services"
	@for service in $(SERVICES); do \
		git clone $(REPOSITORY) $$service; \
	done
	@echo "Cloning the project"
	@git clone $(REPOSITORY) $(LIB)


status:
	@echo "Checking Git status "
	@for project in $(SERVICES) $(LIB); do \
		echo "Status for $$project:"; \
		(cd $$project && git status); \
	done

pull:
	@echo "Fetching the latest changes"
	@for project in $(SERVICES) $(LIB); do \
		echo "Pulling updates for $$project"; \
		(cd $$project && git pull); \
	done

stash:
	@echo "Stashing local changes"
	@for project in $(SERVICES) $(LIB); do \
		echo "Stashing changes for $$project"; \
		(cd $$project && git stash); \
	done

build_lib:
	@echo "Compiling the project"
	@(cd $(LIB) && mvn clean install)


build_services:
	@echo "Building all service projects"
	@for service in $(SERVICES); do \
		echo "Building $$service..."; \
		(cd $$service && mvn clean install); \
	done

build_images:
	@echo "Creating Docker images"
	@for service in $(SERVICES); do \
		echo "Building Docker image for $$service"; \
		(cd $$service && docker build -t $(DOCKER_USERNAME)/$$service:latest .); \
	done

push_images:
	@echo "Pushing Docker images"
	@for service in $(SERVICES); do \
		echo "Pushing Docker image for $$service"; \
		docker push $(DOCKER_USERNAME)/$$service:latest; \
	done
