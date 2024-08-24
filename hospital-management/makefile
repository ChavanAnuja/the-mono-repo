
SERVICES := api-gateway appointment-service case-service directory-service patient-service
LIB := hospital-management-console
REPOSITORY := https://github.com/ChavanAnuja/the-mono-repo.git
DOCKER_USERNAME := anujachavan


DEFAULT: clone status pull stash build_lib build_services build_images push_images

clone:
     @echo "Starting to clone Services"
     @for service in $(SERVICES); do \
          git clone $(REPOSITORY) $$service; \
     done
     @echo "Cloning Javafx project"
     @git clone $(REPO) $(LIBRARY)



