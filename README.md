# This is a sample application

## Steps to build

### Clone the repository
```sh
git clone https://github.com/abhishekjawali/StepFunctionECSTasks.git
cd StepFunctionECSTasks/
```

### Build and push the pre-processing-service image to docker repository
Note: Replace DOCKER-REPOSITORY-URL with the actual URL of docker image repository
```
cd pre-processing-service/
docker build -t pre-processing-service .
docker push <<DOCKER-REPOSITORY-URL>>
```

### Build and push the main-processing-service image to docker repository
Note: Replace DOCKER-REPOSITORY-URL with the actual URL of docker image repository
```
cd ../main-processing-service/
docker build -t main-processing-service .
docker push <<DOCKER-REPOSITORY-URL>>
```