
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
