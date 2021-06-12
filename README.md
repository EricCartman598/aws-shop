# aws-shop
Internet shop using AWS

To create and push docker image:
1. Create docker image:
sudo docker build -t aws-shop:<version> .

2. Rename docker image:
sudo docker tag aws-shop:v3 ericcartman598/aws-shop:<version>

3. Push the image to Docker Hub
sudo docker push ericcartman598/aws-shop:<version>

To redeploy the same version on cluster: 
kubectl rollout restart deployment/aws-shop-deployment

To set up a cluster and run your app there:
1. Create a cluster:
eksctl create cluster -f cluster.yaml

2. Add your credentials to access to your private repo on Docker Hub:
kubectl create secret docker-registry regcred --docker-server=https://index.docker.io/v2/ --docker-username=<your 
dockerhub username> --docker-password=<your dockerhub password> --docker-email=<your email linked to your dockerhub account>

3. Create a deployment:
kubectl apply -f deployment.yaml

4. OPTIONAL: You can Сheck how you pods work by creatmg port-forwarding and access to a pod:
kubectl port-forward <your pod name> 1234:8080
to get pods list run the command "kubectl get pods"

5. Create service of LoadBalancer type
kubectl apply -f service.yaml
